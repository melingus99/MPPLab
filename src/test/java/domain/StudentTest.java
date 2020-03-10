package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.Student;
import static org.junit.Assert.*;

public class StudentTest {

    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final Long GROUP = new Long(100);
    private static final Long NEW_GROUP = new Long(101);;
    private static final String NAME = "studentName";
    private static final String NEW_NAME = "studentName2";
    private static Student student;

    @Before
    public void setUp() throws Exception {
        student = new Student(NAME,GROUP);
        student.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        student=null;
    }

    @Test
    public void getName() {
        assertEquals(NAME,student.getName());
    }

    @Test
    public void setName() {
        student.setName(NEW_NAME);
        assertEquals(NEW_NAME,student.getName());
    }

    @Test
    public void getGroup() {
        assertEquals(student.getGroup(),GROUP);
    }

    @Test
    public void setGroup() {
        student.setGroup(NEW_GROUP);
        assertEquals(NEW_GROUP,student.getGroup());
    }
}