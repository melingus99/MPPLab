package domain.validators;

public interface Validator<T> {
    boolean validate(T entity) throws ValidatorException;
}
