package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;


public class Card3PersonalDetails {
    private final WebDriver driver;
    private final By dropdownFieldsLocator = By.className("dropdown__field");
    private final By nextButtonLocator = By.xpath("//button[text()='Next']");

    public Card3PersonalDetails(WebDriver driver) {
       this.driver = driver;
    }

    public Card3PersonalDetails fillInputFields() {
        List<WebElement> inputFields = driver.findElements(By.tagName("input"));

        for (WebElement inputField : inputFields) {
            inputField.clear();
            inputField.sendKeys("foo");
        }
        return this;
    }

    public Card3PersonalDetails selectDropdownFields(int year, int day, String month) {
        List<WebElement> dropdownFields = driver.findElements(dropdownFieldsLocator);

        for (WebElement dropdownField : dropdownFields) {
            dropdownField.click();

            if (dropdownField.getText().equals("Choose a title")) {
                driver.findElement(By.xpath("//div[text()='Mrs']")).click();
                continue;
            }

            WebElement item;
            if (dropdownField.getText().equals("Year")) {
                item = driver.findElement(By.xpath(String.format("//div[text()='%d']", year)));
            } else if (dropdownField.getText().equals("Day")) {
                item = driver.findElement(By.xpath(String.format("//div[text()='%d']", day)));
            } else if (dropdownField.getText().equals("Month")){
                item = driver.findElement(By.xpath(String.format("//div[text()='%s']", month)));
            } else {
                By nonDateItemLocator = By.xpath("//div[contains(@class, '-item')]");
                item = driver.findElement(RelativeLocator.with(nonDateItemLocator).near(dropdownField));
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
        }
        return this;
    }

    public Card4ProveYouAreHuman goToNextPage() {
        driver.findElement(nextButtonLocator).click();
        return new Card4ProveYouAreHuman(driver);
    }
}
