package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;
    String username =  "admin";
    String password =  "admin123";




    public LoginPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String url = driver.getCurrentUrl();

        if (!driver.getCurrentUrl().equals("http://localhost:8083/login?next=%2F")) {  //
            throw new IllegalStateException("This is not the login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public MainPage loginAdmin() {


        WebElement element = driver.findElement(By.id("username"));
        element.sendKeys(username);
        element = (WebElement) driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = (WebElement) driver.findElement(By.name("submit"));
        element.click();

        return new MainPage(driver);
    }




    public Boolean returnTrue (){


        return true;

    }


}
