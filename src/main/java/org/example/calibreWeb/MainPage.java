package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MainPage {
    private WebDriver driver;



    static String projectPath = Paths.get("").toAbsolutePath().toString();
    static String filePath =  "\\src\\main\\downloadTest";
    private static final String DOWNLOAD_PATH = projectPath+filePath;
    private static final String EXPECTED_FILE_NAME = "L3 - wafi.pdf";




    public MainPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


        if (!driver.getCurrentUrl().equals("http://localhost:8083/")) {  //
            throw new IllegalStateException("This is not the main Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public void checkReacCheckbox(String title){

        String elementName = "p[title='"+title+"']";
        WebElement element = driver.findElement(By.cssSelector(elementName));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("have_read_cb"))));
        if(!checkbox.isSelected()) {
            checkbox.click();
        }
        driver.navigate().refresh();

    }

    public void uncheckReacCheckbox(String title){

        String elementName = "p[title='"+title+"']";
        WebElement element = driver.findElement(By.cssSelector(elementName));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("have_read_cb"))));
        if(checkbox.isSelected()) {
            checkbox.click();
        }
        driver.navigate().refresh();

    }

    public boolean markBookRead(String title) {

        checkReacCheckbox(title);

        return !driver.findElements(By.cssSelector("span.img[title='"+title+"'] > span.badge.read.glyphicon.glyphicon-ok")).isEmpty();

    }

    public readPage bookWentToRead(String title) {


        checkReacCheckbox(title);
        driver.findElement(By.cssSelector("a[href='/read/stored/']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new readPage(driver);

    }

    public unreadPage bookMovedFromUnread(String title) {


        checkReacCheckbox(title);
        driver.findElement(By.cssSelector("a[href='/unread/stored/']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new unreadPage(driver);

    }

    public unreadPage bookMovedBackToUnread(String title) {

        checkReacCheckbox(title);
        uncheckReacCheckbox(title);
        driver.findElement(By.cssSelector("a[href='/unread/stored/']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new unreadPage(driver);


    }

    public readPage bookWentBackFromRead(String title) {

        checkReacCheckbox(title);
        uncheckReacCheckbox(title);
        driver.findElement(By.cssSelector("a[href='/read/stored/']")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return new readPage(driver);

    }


    public searchPage searchInWeb(String text){

        WebElement element = driver.findElement(By.id("query"));
        element.sendKeys(text);
        element = driver.findElement(By.id("query_submit"));
        element.click();


        return new searchPage(driver);

    }


    public boolean downloadBook(String title) throws InterruptedException {
        String elementName = "p[title='" + title + "']";
        WebElement element = driver.findElement(By.cssSelector(elementName));
        element.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("btnGroupDrop1pdf"))));
        button.click();


        boolean isDownloaded = isFileDownloaded(DOWNLOAD_PATH, EXPECTED_FILE_NAME);

        deleteDownloadedFile(DOWNLOAD_PATH, EXPECTED_FILE_NAME);

        return isDownloaded ;


    }


    private boolean isFileDownloaded(String downloadDir, String fileName) throws InterruptedException {
        Thread.sleep(2000);

        File file = new File(downloadDir, fileName); // Combine folder path and file name

        return file.exists();
    }

    public void deleteDownloadedFile(String downloadDir, String fileName) {
        File file = new File(downloadDir, fileName);

        if (file.exists()) {
            boolean delete = file.delete();
        }
    }


    public Boolean returnTrue (){


        return true;

    }


}
