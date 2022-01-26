package Classes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testGetLogin() {
        User user = new User("Marek", "koparek");
        Assert.assertEquals("Marek", user.getLogin());
    }

    public void testSetLogin() {
        User user = new User();
        user.setLogin("Marek");
        Assert.assertEquals("Marek", user.getLogin());
    }

    public void testTestGetSetName() {
        User user = new User();
        user.setName("Marek");
        Assert.assertEquals("Marek", user.getName());
    }


    public void testGetSetSurname() {
        User user = new User();
        user.setSurname("Ibisz");
        Assert.assertEquals("Ibisz", user.getSurname());
    }
}
