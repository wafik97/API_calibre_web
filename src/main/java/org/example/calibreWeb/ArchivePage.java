package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class ArchivePage {
    private WebDriver driver;


    public ArchivePage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String url = driver.getCurrentUrl();

        if (!driver.getCurrentUrl().contains("archived")) {  //
            throw new IllegalStateException("This is not the login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public boolean checkBookInArchivePage(String title) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return !driver.findElements(By.cssSelector("div.discover.load-more p[title='" + title + "']")).isEmpty();

    }




    public Boolean returnTrue (){


        return true;

    }


}
