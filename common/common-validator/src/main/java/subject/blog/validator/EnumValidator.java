package subject.blog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import subject.blog.annotation.EnumCheck;

public class EnumValidator implements ConstraintValidator<EnumCheck, String> {

    private EnumCheck annotation;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (Enum enumValue : annotation.enumClass().getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(EnumCheck constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }
}
