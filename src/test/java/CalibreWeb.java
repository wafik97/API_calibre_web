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

public class CalibreWeb {
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
    public void testMarkingBook() {

        boolean check = mainPage.markBookRead("L3");
        assertTrue(check);


    }

    @Test
    public void testBookInRead() {

        boolean check = mainPage.bookWentToRead("L3").checkBookInRead("L3");
        assertTrue(check);


    }


    @Test
    public void testBookNotInUnread() {

        boolean check = mainPage.bookMovedFromUnread("L3").checkBookInUnread("L3");
        assertFalse(check);

    }



    @Test
    public void testNumberOfResults(){

        int result = mainPage.searchInWeb("k").countResults();
        assertEquals(5,result);

    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    public void testDownload() throws InterruptedException {


        boolean check = mainPage.downloadBook("L3");
        assertTrue(check);

    }

    @Test
    public void testSearch()   {

        boolean check = mainPage.searchInWeb("L3").bookFound("L3");
        assertTrue(check);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
