package pages;

import helpers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(){PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(xpath = "//span[@class='nav-line-2 ']")
    public WebElement accountAndLists;

    @FindBy(xpath = "//span[@class='nav-action-inner']")
    public WebElement signIn;
}
