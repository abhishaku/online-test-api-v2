package org.online.test.api.service.assumption;

import java.util.List;

import org.online.test.api.annotation.LogExecutionTime;
import org.online.test.api.domain.Assumption;
import org.online.test.api.domain.Organization;
import org.online.test.api.event.EntityCreatedEvent;
import org.online.test.api.exception.ApplicationException;
import org.online.test.api.exception.ErrorResponseEnum;
import org.online.test.api.exception.ValidationError;
import org.online.test.api.exception.ValidationException;
import org.online.test.api.helper.PaginationHelper;
import org.online.test.api.repository.AssumptionRepository;
import org.online.test.api.repository.OrganizationRepository;
import org.online.test.api.utils.DateUtil;
import org.online.test.api.validator.AssumptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AssumptionServiceImpl implements AssumptionService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(AssumptionServiceImpl.class);

  @Value("${pagination.assumption.default}")
  private int defaultPageSize;

  @Value("${pagination.assumption.max}")
  private int maxPageSize;

  @Autowired
  private PaginationHelper paginationHelper;

  @Autowired
  private AssumptionRepository assumptionRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  @LogExecutionTime
  public List<Assumption> findOrganizationAssumptions(String orgId, Integer page, Integer limit) {
    page = paginationHelper.refinePageNumber(page);
    limit = paginationHelper.validateResponseLimit(limit, defaultPageSize, maxPageSize);
    LOG.info("Get assumptions for organization {}, page {}, limit {}", orgId, page, limit);

    Page<Assumption> assumptions = assumptionRepository
        .findByOrgIdAndActive(orgId, true, new PageRequest(page, limit));
    return assumptions.getContent();
  }

  @Override
  @LogExecutionTime
  public Assumption create(Assumption assumption) {
    Assert.notNull(assumption, "Assumption data can not be null.");

    List<ValidationError> validationErrorList = AssumptionValidator.validateAssumption(assumption);
    if (!validationErrorList.isEmpty()) {
      LOG.error("Could not create an assumption due to insufficient data.");
      throw new ValidationException(validationErrorList, ErrorResponseEnum.VALIDATION_ERROR);
    }

    Organization organization = organizationRepository.findOne(assumption.getOrgId());
    if (organization == null || !organization.isActive()) {
      LOG.error("Organization is inactive or not found. Could not create assumption.");
      throw new ApplicationException(ErrorResponseEnum.ORGANIZATION_INACTIVE_ERROR);
    }

    Assumption existingAssumption = assumptionRepository
        .findByReferenceNameAndOrgId(assumption.getReferenceName(), assumption.getOrgId());
    if (existingAssumption != null) {
      LOG.error("Reference name is already in use. Could not create assumption.");
      throw new ApplicationException(ErrorResponseEnum.DUPLICATE_REFERENCE_NAME_ERROR);
    }

    assumption.setCreatedAt(DateUtil.now());
    assumption.setActive(true);
    assumptionRepository.save(assumption);

    if (assumption.getId() != null) {
      publishAssumptionCreatedEvent(assumption);
    }

    return assumption;
  }

  void publishAssumptionCreatedEvent(final Assumption assumption) {
    EntityCreatedEvent entityCreatedEvent = new EntityCreatedEvent(this, assumption);
    applicationEventPublisher.publishEvent(entityCreatedEvent);
  }
}
