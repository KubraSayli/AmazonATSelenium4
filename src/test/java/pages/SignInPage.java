package pages;

import helpers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    public SignInPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id= "ap_email")
    public WebElement email;

    @FindBy(xpath = "//input[@type = 'submit']")
    public WebElement continueButton;

    @FindBy(css = "#ap_password")
    public WebElement password;

    @FindBy(className = "a-button-input")
    public WebElement signInButton;

    @FindBy(xpath = "//*[contains(text(),'Your password is incorrect')]")
    public WebElement wrongPasswordError;

    @FindBy(xpath = "//*[contains(text(),'cannot find an account with that email')]")
    public WebElement wrongEmailError;

}
