
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import domain.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StudentTest {
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String SERIAL_NUMBER = "sn01";
    private static final String NEW_SERIAL_NUMBER = "sn02";
    private static final String NAME = "studentName";
    private static final String NEW_NAME = "studentName2";

    private Student student;

    @Before
    public void setUp() throws Exception {
        student = new Student(SERIAL_NUMBER, NAME);
        student.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        student=null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, student.getId());
    }

    @Test
    public void testSetId() throws Exception {
        student.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, student.getId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(NAME,student.getName());
    }

    @Test
    public void testSetName() throws Exception {
        student.setName(NEW_NAME);
        assertEquals(NEW_NAME,student.getName());
    }

}