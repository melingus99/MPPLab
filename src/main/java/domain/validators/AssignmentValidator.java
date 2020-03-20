package domain.validators;

import domain.Assignment;

public class AssignmentValidator implements Validator<Assignment> {

    @Override
    public boolean validate(Assignment assignment) throws ValidatorException {
        if(assignment.getGrade()<0)
            throw new ValidatorException("invalid grade");
        else
            return true;
    }
}
