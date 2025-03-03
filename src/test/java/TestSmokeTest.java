import org.example.calibreWeb.MainPage;
import org.example.calibreWeb.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.nio.file.Paths;
import java.time.Duration;

import static org.example.calibreWeb.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.*;

public class TestSmokeTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    static String projectPath = Paths.get("").toAbsolutePath().toString();
    static String filePath =  "\\src\\main\\downloadTest";
    private static final String DOWNLOAD_PATH = projectPath+filePath;
    private static final String EXPECTED_FILE_NAME = "L3 - wafi.pdf";


    @BeforeEach
    public void setUp() {



//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();


        driver = getDriver();
        driver.get("https://ffe4-5-28-174-93.ngrok-free.app/login");
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
        loginPage = new LoginPage(driver);
        mainPage = loginPage.loginAdmin();
    }



    @Test
    public void testSearchForPartialName()   {

        boolean check = mainPage.searchInWeb("L").bookFound("L3");
        assertTrue(check);

    }


    @Test
    public void testSearchForName()   {

        boolean check = mainPage.searchInWeb("L3").bookFound("L3");
        assertTrue(check);

    }




    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
