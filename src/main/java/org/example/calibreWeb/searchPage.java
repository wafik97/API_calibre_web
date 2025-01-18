package org.example.calibreWeb;

import com.sun.source.tree.BreakTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;



public class searchPage {
    private WebDriver driver;


    public searchPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String url = driver.getCurrentUrl();

        if (!driver.getCurrentUrl().contains("localhost:8083/search/stored/")) {  //
            throw new IllegalStateException("This is not the search Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public int countResults(){

    WebElement element = driver.findElement(By.cssSelector(".discover h2"));
    String text = element.getText();
    int number_of_results =0;

       for(char c :text.toCharArray()){
           if(c<'0'||c>'9'){
               break;
           }
           number_of_results=number_of_results*10+ (int) c-'0';
       }

       return number_of_results;

    }



    public Boolean returnTrue (){


        return true;

    }


}
