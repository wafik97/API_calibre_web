package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MainPage {
    private WebDriver driver;


    @FindBy(id = "have_read_cb")
    private WebElement readCheckBox;

    @FindBy(css = "a[href='/read/stored/']")
    private WebElement readPage;

    @FindBy(css = "a[href='/unread/stored/']")
    private WebElement unreadPage;

    @FindBy(id = "query")
    private WebElement searchBar;

    @FindBy(id = "query_submit")
    private WebElement submitSearch;

    @FindBy(id = "btnGroupDrop1pdf")
    private WebElement downloadB;





    static String projectPath = Paths.get("").toAbsolutePath().toString();
    static String filePath =  "\\src\\main\\downloadTest";
    private static final String DOWNLOAD_PATH = projectPath+filePath;
    private static final String EXPECTED_FILE_NAME = "L3 - wafi.pdf";




    public MainPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    public void checkReadCheckbox(String title){

        String elementName = "div.discover.load-more p[title='" + title + "']";
        WebElement element = driver.findElement(By.cssSelector(elementName));
        element.click();
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(readCheckBox));
        if(!checkbox.isSelected()) {
            checkbox.click();
        }
        driver.navigate().refresh();

    }

    public void uncheckReadCheckbox(String title){

        String elementName = "div.discover.load-more p[title='" + title + "']";
        WebElement element = driver.findElement(By.cssSelector(elementName));
        element.click();
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(readCheckBox));
        if(checkbox.isSelected()) {
            checkbox.click();
        }
        driver.navigate().refresh();

    }



    public boolean markBookRead(String title) {

        checkReadCheckbox(title);

        return !driver.findElements(By.cssSelector("span.img[title='"+title+"'] > span.badge.read.glyphicon.glyphicon-ok")).isEmpty();

    }

    public readPage bookWentToRead(String title) {


        checkReadCheckbox(title);
        readPage.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new readPage(driver);

    }

    public unreadPage bookMovedFromUnread(String title) {


        checkReadCheckbox(title);
        unreadPage.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new unreadPage(driver);

    }


    public searchPage searchInWeb(String text){

        searchBar.sendKeys(text);
        submitSearch.click();


        return new searchPage(driver);

    }


    public boolean downloadBook(String title) throws InterruptedException {


        String elementName = "div.discover.load-more p[title='" + title + "']";
        WebElement element = driver.findElement(By.cssSelector(elementName));
        element.click();
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(downloadB));
        button.click();




        boolean isDownloaded = isFileDownloaded(DOWNLOAD_PATH, EXPECTED_FILE_NAME);

        deleteDownloadedFile(DOWNLOAD_PATH, EXPECTED_FILE_NAME);

        return isDownloaded ;


    }


    private boolean isFileDownloaded(String downloadDir, String fileName) throws InterruptedException {
        Thread.sleep(4000);

        File file = new File(downloadDir, fileName); // Combine folder path and file name

        return file.exists();
    }

    public void deleteDownloadedFile(String downloadDir, String fileName) {
        File file = new File(downloadDir, fileName);

        if (file.exists()) {
            boolean delete = file.delete();
        }
    }


    public boolean checkBookInPage(String title) {

        return !driver.findElements(By.cssSelector("div.discover.load-more p[title='" + title + "']")).isEmpty();

    }


    public Boolean returnTrue (){


        return true;

    }


}
