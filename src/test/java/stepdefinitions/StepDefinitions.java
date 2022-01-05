package stepdefinitions;

import helpers.CommonMethods;
import helpers.ConfigReader;
import helpers.Driver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.HomePage;
import pages.SignInPage;
import pages.SignedInPage;

import java.util.List;

public class StepDefinitions {
    HomePage homePage = new HomePage();
    SignInPage signInPage = new SignInPage();
    SignedInPage signedInPage = new SignedInPage();
    WebElement searchBox;

    @Given("user navigates to home page")
    public void user_navigates_to_home_page() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @Given("verify if user is on the home page")
    public void verify_if_user_is_on_the_home_page() {
        Assert.assertEquals(Driver.getDriver().getTitle(), "Amazon.com. Spend less. Smile more.");
    }

    @When("user hover over on account&lists arrow")
    public void user_hover_over_on_account_lists_arrow() {
        CommonMethods.hoverOver(homePage.accountAndLists);
    }

    @When("user clicks on Sign In button")
    public void user_clicks_on_Sign_In_button() {
        CommonMethods.click(homePage.signIn);

    }

    @When("user enters their {string}")
    public void user_enters_their(String value) {
        if (value.contains("@")) {
            CommonMethods.sendKeys(signInPage.email, value);
        } else {
            CommonMethods.sendKeys(signInPage.password, value);
        }
    }

    @When("user clicks on Continue button")
    public void user_clicks_on_Continue_button() {
        CommonMethods.click(signInPage.continueButton);
    }

    @When("user clicks on SignIn button")
    public void user_clicks_on_SignIn_button() {
        CommonMethods.click(signInPage.signInButton);
    }

    @Then("verify if they are signed in")
    public void verify_if_they_are_signed_in() {
        Assert.assertTrue(signedInPage.userName.getText().contains("KUBRA"));
    }

    @When("user clicks on All in search box verify if first option is {string}")
    public void user_clicks_on_All_in_search_box_verify_if_first_option_is(String string) {
        System.out.println(CommonMethods.getDropdownValues(signedInPage.AllDropdown));
        signedInPage.AllDropdown.click();
        String firstSelectedOption = CommonMethods.getDropdownValues(signedInPage.AllDropdown).get(0);
        Assert.assertEquals(firstSelectedOption, string);
    }

    @When("user clicks on the {string} from dropdown")
    public void user_clicks_on_the_from_dropdown(String expectedValue) {
        Select dropDown = new Select(signedInPage.AllDropdown);
        List<WebElement> dropDownList = dropDown.getOptions();
        WebElement expectedValueWebElement = null;
        for (int i = 0; i < dropDownList.size(); i++) {
            if (dropDownList.get(i).getText().equals(expectedValue)) {
                expectedValueWebElement = dropDownList.get(i);
                expectedValueWebElement.click();
            }
        }
        //With Selenium4, we can use Relative locators
        searchBox = Driver.getDriver()
                .findElement(RelativeLocator.with(By.tagName("input")).toRightOf(expectedValueWebElement));


    }

    @When("user searches for {string}")
    public void user_searches_for(String value) {
        searchBox.sendKeys(value, Keys.ENTER);
    }

    @When("user selects any product")
    public void user_selects_any_product() {
        CommonMethods.click(signedInPage.product);

    }

    @When("user clicks on add to cart button")
    public void user_clicks_on_add_to_cart_button() {
        CommonMethods.click(signedInPage.addToChart);
    }

    @Then("verify if product is added to cart")
    public void verify_if_product_is_added_to_cart() {
        String expectedText = "Added to Cart";
        Assert.assertEquals(expectedText, signedInPage.addedToChart.getText(), "the product could not be added");
    }

    @Then("verify error message for password")
    public void verify_error_message_for_password() {
        Assert.assertTrue(signInPage.wrongPasswordError.getText().contains("Your password is incorrect"));
    }

    @Then("verify error message for email")
    public void verify_error_message_for_email() {
        Assert.assertTrue(signInPage.wrongEmailError.getText().contains("cannot find an account with that email"));
    }


}
