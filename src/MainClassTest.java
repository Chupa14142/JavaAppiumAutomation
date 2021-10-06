import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassString() {
        String expected_string = "hello";

        Assert.assertTrue("ClassString doesn't contains Hello or hello substring"
                , mainClass.getClassString().toLowerCase().contains(expected_string.toLowerCase()));
    }

}


