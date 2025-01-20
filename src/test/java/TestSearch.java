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

public class TestSearch {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;


    @BeforeEach
    public void setUp() {



        driver = getDriver();
        driver.get("https://8afd-5-28-174-93.ngrok-free.app/login");
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
    public void testNumberOfResults(){

        int result = mainPage.searchInWeb("k").countResults();
        assertEquals(5,result);

    }


    @Test
    public void testSearchForName()   {

        boolean check = mainPage.searchInWeb("L3").bookFound("L3");
        assertTrue(check);

    }

    @Test
    public void testSearchForPartialName()   {

        boolean check = mainPage.searchInWeb("L").bookFound("L3");
        assertTrue(check);

    }

    @Test
    public void testSearchForAutherName()   {

        boolean check = mainPage.searchInWeb("wafi").bookFound("L3");
        assertTrue(check);

    }

    @Test
    public void testSearchForAutherNameOtherLanguage()   {

        boolean check = mainPage.searchInWeb("ופי").bookFound("L1");
        assertTrue(check);

    }

    @Test
    public void testNumberOfResultsOfAutherNameOtherLanguage()   {

        int check = mainPage.searchInWeb("ופי").countResults();
        assertEquals(2,check);

    }

    @Test
    public void testSearchForPublisherName()   {

        boolean check = mainPage.searchInWeb("unknown").bookFound("Killer Game Programming in Java ( PDFDrive )");
        assertTrue(check);

    }

    @Test
    public void testNumberOfResultsWithPublisheName()   {

        int check = mainPage.searchInWeb("unknown").countResults();
        assertEquals(2,check);

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
