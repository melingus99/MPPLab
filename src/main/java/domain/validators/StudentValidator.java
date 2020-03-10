package domain.validators;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    @Override
    public boolean validate(Student student) throws ValidatorException {
        //(student.getGroup()>99 && student.getGroup()<1000) ? return; : throw ValidatorException("group number invalid");
        if(student.getGroup()>1000 || student.getGroup()<99)
            throw new ValidatorException("invalid group name");
        else
            return true;
    }
}
