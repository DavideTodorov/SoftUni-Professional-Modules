package softuni.exam.instagraphlite.util;

import org.springframework.stereotype.Component;

import javax.validation.Validator;


@Component
public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;


    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> boolean isValid(T element) {
        return validator.validate(element).isEmpty();
    }
}
