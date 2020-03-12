package domain.validators;

import domain.LabProblem;
import domain.Student;

public class LabProblemValidator implements Validator<LabProblem> {

    @Override
    public boolean validate(LabProblem labProblem) throws ValidatorException {
            return true;
    }
}
