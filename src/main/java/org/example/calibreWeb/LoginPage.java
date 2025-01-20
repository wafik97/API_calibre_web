package org.example.calibreWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;





public class LoginPage {
    private WebDriver driver;
    String username =  "admin";
    String password =  "admin123";


    @FindBy(id = "username")
    private WebElement usernameElement;

    @FindBy(id = "password")
    private WebElement passwordElement;

    @FindBy(name = "submit")
    private WebElement submitElement;







    public LoginPage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        PageFactory.initElements(driver, this);



        if (!driver.getCurrentUrl().contains("login")) {  //
            throw new IllegalStateException("This is not the login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public MainPage loginAdmin() {



        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        submitElement.click();

        return new MainPage(driver);
    }




    public Boolean returnTrue (){


        return true;

    }


}
