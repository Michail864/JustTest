import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class HomePage extends CommonAPI {
    @FindBy(xpath = "//*[@id=\"navFooter\"]/div[1]/div/div[1]/ul/li[1]/a")
    public static WebElement careers;
    @FindBy(xpath = "//*[@id=\"nav-xshop\"]/a[5]")
    public static WebElement wholeFoods;

    @FindBy(xpath = "//*[@id=\"nav-signin-tooltip\"]/a/span")
    public static WebElement signIn;

    @FindBy(xpath = "//*[@id=\"ap_email\"]")
    public static WebElement email;

    @FindBy(xpath = "//*[@id=\"ap_password\"]")
    public static WebElement passW;

    public  static void clickButton(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        signIn.click();

    }

    public static void writeUserName(String userName){
        String userName1 = userName;
        email.sendKeys(userName1);

    }


    public static void writePass(String pass){
        String pass1 = pass;
        passW.sendKeys(pass1);

    }

    public void careersClick(){
        careers.click();
    }
}
