package org.online.test.api.validator;

import java.util.ArrayList;
import java.util.List;

import org.online.test.api.domain.Organization;
import org.online.test.api.exception.ValidationError;
import org.online.test.api.exception.ValidationErrorType;
import org.online.test.api.utils.AppConstants;
import org.springframework.util.StringUtils;

/**
 * Created by Abhishek on 10/6/17.
 */
public final class OrganizationValidator {

  private OrganizationValidator() {
  }

  public static List<ValidationError> validateOrganizationData(final Organization organization) {
    List<ValidationError> validationErrorList = new ArrayList<>();

    if (StringUtils.isEmpty(organization.getName())) {
      validationErrorList.add(new ValidationError(AppConstants.NAME,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(organization.getContact())) {
      validationErrorList.add(new ValidationError(AppConstants.CONTACT,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(organization.getEmail())) {
      validationErrorList.add(new ValidationError(AppConstants.EMAIL,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }
    return validationErrorList;
  }
}
