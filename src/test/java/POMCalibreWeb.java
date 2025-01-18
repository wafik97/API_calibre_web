import org.example.calibreWeb.MainPage;
import org.example.calibreWeb.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class POMCalibreWeb {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    static String projectPath = Paths.get("").toAbsolutePath().toString();
    static String filePath =  "\\src\\main\\downloadTest";
    private static final String DOWNLOAD_PATH = projectPath+filePath;
    private static final String EXPECTED_FILE_NAME = "L3 - wafi.pdf";


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking"); // Disable popups
        options.addArguments("--start-maximized");
        options.setExperimentalOption("prefs", Map.of(
                "download.default_directory", DOWNLOAD_PATH,
                "download.prompt_for_download", false,
                "download.directory_upgrade", true,
                "safebrowsing.enabled", true
        ));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost:8083/");

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
    public void testbookWentBackFromRead() {

        boolean check = mainPage.bookWentBackFromRead("L3").checkBookInRead("L3");
        assertFalse(check);


    }

    @Test
    public void testBookMovedBackToUnread() {


        boolean check = mainPage.bookMovedBackToUnread("L3").checkBookInUnread("L3");
        assertTrue(check);


    }

    @Test
    public void testNumberOfResults(){

        int result = mainPage.searchInWeb("k").countResults();
        assertEquals(5,result);

    }

    @Test
    public void testDownload() throws InterruptedException {

        boolean check = mainPage.downloadBook("L3");
        assertTrue(check);

    }




    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
