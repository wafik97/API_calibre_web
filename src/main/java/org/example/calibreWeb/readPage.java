package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class readPage {
    private WebDriver driver;


    public readPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String url = driver.getCurrentUrl();

        if (!driver.getCurrentUrl().contains("/read/stored/")) {  //
            throw new IllegalStateException("This is not the read Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public boolean checkBookInRead(String title) {


        return !driver.findElements(By.cssSelector("div.discover.load-more p[title='" + title + "']")).isEmpty();
    }


    public Boolean returnTrue (){


        return true;

    }


}
