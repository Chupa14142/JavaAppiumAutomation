import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;


public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassString() {
        Pattern MY_PATTERN = Pattern.compile("\\b^[hH]ello\\b");

        Assert.assertTrue("ClassString doesn't contains Hello or hello substring"
                ,         MY_PATTERN.matcher(mainClass.getClassString()).find());
    }

}


