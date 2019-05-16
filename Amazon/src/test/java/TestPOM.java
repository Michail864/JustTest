import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPOM extends CommonAPI {
    public HomePage homepage;

    @BeforeMethod
    public void init(){
        homepage = PageFactory.initElements(driver, HomePage.class);
    }
    @Test
    public void careers(){
        homepage.careersClick();
        sleepFor(5);

    }
}
