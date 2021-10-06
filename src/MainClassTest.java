import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void checkLocalNumber() {
        Assert.assertTrue("getLocalNumber != 14", MainClass.getLocalNumber() == 14);
    }

}
