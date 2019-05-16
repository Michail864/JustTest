import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestHomePage extends HomePage{
    @Parameters({"user-name", "pass"})
    @Test
        public void logIn(String user, String password){
            clickButton();
            writeUserName(user);
            writePass(password);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
