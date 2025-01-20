package org.example.calibreWeb;

import com.sun.source.tree.BreakTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class searchPage {
    private WebDriver driver;

    @FindBy(css = ".discover h2")
    private WebElement titleWithNumber;



    public searchPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);

        if (!driver.getCurrentUrl().contains("/search/stored/")) {  //
            throw new IllegalStateException("This is not the search Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public int countResults(){


    String text = titleWithNumber.getText();
    int number_of_results =0;

       for(char c :text.toCharArray()){
           if(c<'0'||c>'9'){
               break;
           }
           number_of_results=number_of_results*10+ (int) c-'0';
       }

       return number_of_results;

    }

    public boolean bookFound(String title){


        return !driver.findElements(By.cssSelector("p[title='" + title + "']")).isEmpty();
    }



    public Boolean returnTrue (){


        return true;

    }


}
