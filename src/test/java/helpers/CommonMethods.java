package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonMethods {

    public static Actions actions;
    public static WebDriverWait wait;

    public static void hoverOver(WebElement element) {
        actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void click(WebElement element) {
        waitForClickablility(element,10);
        try {
            element.click();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
        }
    }


    public static void sendKeys(WebElement element, String value) {
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new TestException(String.format("Error in sending [%s] to the following element: [%s]", value, element.toString()));
        }
    }

    public static List<String> getDropdownValues(WebElement element) {

        Select dropdown = new Select(element);
        List<String> elementList = new ArrayList<String>();
        List<WebElement> allValues = dropdown.getOptions();
        sleep(200);
        if (allValues == null) {
            throw new TestException("Some elements in the list do not exist");
        }

        for (WebElement value : allValues) {
            if (value.isDisplayed()) {
                elementList.add(value.getText().trim());
            }
        }
        return elementList;
    }


    //Hard wait:
    public static void sleep(final long millis) {
        System.out.println((String.format("sleeping %d ms", millis)));
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickablility(WebElement element, int timeout) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e) {
            throw new TestException("The following element is not clickable: " + element);
        }
    }

    public static void waitForClickablility(By locator, int timeout) {

        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (Exception e) {
            throw new TestException("The following element is not clickable: " + locator);
        }
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    public static void scrollToThenClick(By selector) {
        WebElement element = Driver.getDriver().findElement(selector);
        actions = new Actions(Driver.getDriver());
        try {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            actions.moveToElement(element).perform();
            actions.click(element).perform();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
        }
    }



}
