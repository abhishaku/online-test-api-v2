package org.online.test.api.validator;

import java.util.ArrayList;
import java.util.List;

import org.online.test.api.domain.Project;
import org.online.test.api.exception.ValidationError;
import org.online.test.api.exception.ValidationErrorType;
import org.online.test.api.utils.AppConstants;
import org.springframework.util.StringUtils;

public class ProjectValidator {

  private ProjectValidator() {
  }

  public static List<ValidationError> validateProjectData(final Project project) {
    List<ValidationError> validationErrorList = new ArrayList<>();

    if (StringUtils.isEmpty(project.getName())) {
      validationErrorList.add(new ValidationError(AppConstants.NAME,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(project.getOrganizationId())) {
      validationErrorList.add(new ValidationError(AppConstants.ORGANIZATION_ID,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    return validationErrorList;
  }
}
