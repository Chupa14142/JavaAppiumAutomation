import org.junit.Assert;
import org.junit.Test;


public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassString() {
        Assert.assertTrue("ClassString doesn't contains Hello or hello substring"
                , (mainClass.getClassString().contains("hello") | mainClass.getClassString().contains("Hello")));
    }

}


