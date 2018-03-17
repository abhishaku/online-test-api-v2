package org.online.test.api.validator;

import java.util.ArrayList;
import java.util.List;

import org.online.test.api.domain.Assumption;
import org.online.test.api.exception.ValidationError;
import org.online.test.api.exception.ValidationErrorType;
import org.online.test.api.utils.AppConstants;
import org.springframework.util.StringUtils;

public final class AssumptionValidator {

  private AssumptionValidator() {
  }

  public static List<ValidationError> validateAssumption(final Assumption assumption) {
    List<ValidationError> validationErrorList = new ArrayList<>();

    if (StringUtils.isEmpty(assumption.getLabel())) {
      validationErrorList.add(new ValidationError("label",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(assumption.getOrgId())) {
      validationErrorList.add(new ValidationError("orgId",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(assumption.getFieldType())) {
      validationErrorList.add(new ValidationError("fieldType",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    } else if (!AppConstants.fieldTypes.contains(assumption.getFieldType())) {
      validationErrorList.add(new ValidationError("fieldType",
          "Field type should be one of these " + String.join(",", AppConstants.fieldTypes)));
    }

    if (StringUtils.isEmpty(assumption.getReferenceName())) {
      validationErrorList.add(new ValidationError("referenceName",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }
    return validationErrorList;
  }
}
