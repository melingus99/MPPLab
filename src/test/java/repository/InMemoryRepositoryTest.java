/*
package repository;

import org.junit.Ignore;
import org.junit.Test;
import domain.validators.ValidatorException;
import domain.validators.StudentValidator;
import domain.Student;
import repository.InMemoryRepository;
import repository.Repository;
import domain.BaseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InMemoryRepoTest {

    private Student testStudent1 = new Student("1","Popescu");
    testStudent1.setId(1)
    private Student testStudent2 = new Student("2","Ionescu");
    testStudent2.setId(1);
    private Repository<Long, Student> testRepository = new InMemoryRepository<>(studentValidator);
    private Set<> ;


    @Ignore
    @Test
    public void testFindOne() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test
    public void testFindAll() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test
    public void testAdd() throws Exception {
        assertEquals("should return empty","Optional.empty",testRepository.add(testStudent1).toString());
    }

    @Ignore
    @Test(expected = ValidatorException.class)
    public void testAddException() throws Exception {
        assertEquals("should return empty","Optional.[1,1,Popescu]",testRepository.add(testStudent2).toString());
    }

    @Ignore
    @Test
    public void testDelete() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test
    public void testUpdate() throws Exception {
        fail("Not yet tested");
    }

    @Ignore
    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws Exception {
        fail("Not yet tested");
    }
}
    */

