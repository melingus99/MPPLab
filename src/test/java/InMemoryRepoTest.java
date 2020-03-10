import org.junit.Ignore;
import org.junit.Test;
import domain.validators.ValidatorException;

import static org.junit.Assert.fail;

public class InMemoryRepoTest {

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

    }

    @Ignore
    @Test(expected = ValidatorException.class)
    public void testAddException() throws Exception {
        fail("Not yet tested");
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
