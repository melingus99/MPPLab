package domain;


import jdk.javadoc.internal.doclets.toolkit.util.Utils;

public class Assignment extends BaseEntity<Long> {

    private Long studentId;
    private Long labProblemId;
    private float grade;


    public Assignment(Long studentId,Long labProblemId){
        this.studentId=studentId;
        this.labProblemId=labProblemId;
        this.grade=0;
    }


    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLabProblemId() {
        return labProblemId;
    }

    public void setLabProblemId(Long labProblemId) {
        this.labProblemId = labProblemId;
    }

    @Override
    public String toString(){
        return "Assignment for student:"+this.getStudentId()+" with lab problem: "+this.getLabProblemId()+" has grade: "+this.getGrade();
    }
}
