import org.example.calibreWeb.MainPage;
import org.example.calibreWeb.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

public class TestCalibreWeb {
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


    void prepareTest(){

        mainPage.searchInWeb("L1").uncheckArchiveCheckbox("L1");


        driver.navigate().back();
        driver.navigate().refresh();


     //   mainPage = new MainPage(driver);


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

//    @Test
//    public void testbookWentBackFromRead() {
//
//        boolean check = mainPage.bookWentBackFromRead("L3").checkBookInRead("L3");
//        assertFalse(check);
//
//
//    }
//
//    @Test
//    public void testBookMovedBackToUnread() {
//
//
//        boolean check = mainPage.bookMovedBackToUnread("L3").checkBookInUnread("L3");
//        assertTrue(check);
//
//
//    }

    @Test
    public void testNumberOfResults(){

        int result = mainPage.searchInWeb("k").countResults();
        assertEquals(5,result);

    }

    @Test
    public void testDownload() throws InterruptedException {

        System.out.println("check download test");

        boolean check = mainPage.downloadBook("L3");
        assertTrue(check);

    }

    @Test
    public void testSearch()   {

        boolean check = mainPage.searchInWeb("L3").bookFound("L3");
        assertTrue(check);

    }


//    @Test
//    public void testArchiveBook(){
//
//        prepareTest();
//
//        boolean check1= mainPage.bookWentToArchivePage("L1").checkBookInArchivePage("L1") ;
//        boolean check2= mainPage.checkBookInPage("L1");
//
//        assertTrue(check1 & check2);
//
//    }




    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
