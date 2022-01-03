package pages;

import helpers.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignedInPage {
    public SignedInPage() {PageFactory.initElements(Driver.getDriver(),this);}


    @FindBy(id = "nav-link-accountList-nav-line-1")
    public WebElement userName;

    @FindBy(xpath = "(//*[contains(text(),'Ships to Turkey')])[1]")
    public WebElement firstShippableProduct;

    @FindBy(xpath="//select[@aria-describedby='searchDropdownDescription']")
    public WebElement AllDropdown;

    @FindBy(className = "a-price-whole")
    public WebElement product;

    @FindBy(id="add-to-cart-button")
    public WebElement addToChart;

    @FindBy(xpath = "//*[contains(text(),'Added to Cart')]")
    public WebElement addedToChart;
}
