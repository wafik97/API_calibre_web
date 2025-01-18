package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class unreadPage {
    private WebDriver driver;



    public unreadPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String url = driver.getCurrentUrl();

        if (!driver.getCurrentUrl().contains("/unread/stored/")) {  //
            throw new IllegalStateException("This is not the unread Page. Current page: " + driver.getCurrentUrl());
        }
    }


    public boolean checkBookInUnread(String title) {

        return !driver.findElements(By.cssSelector("div.discover.load-more p[title='" + title + "']")).isEmpty();

    }



    public Boolean returnTrue (){


        return true;

    }


}
