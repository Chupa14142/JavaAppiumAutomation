import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Class Number <= 45",mainClass.getClassNumber() > 45);
    }

    }


