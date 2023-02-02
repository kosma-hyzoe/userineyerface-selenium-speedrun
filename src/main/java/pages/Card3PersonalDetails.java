package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;



public class Card3PersonalDetails {
    private final WebDriver driver;
    private final By dropdownFieldsLocator = By.className("dropdown__field");
    private final By nextButtonLocator = By.xpath("//button[text()='Next']");
    private final By nonDateItemLocator = By.xpath("//div[contains(@class, '-item')]");
    private final By inputFieldsLocator = By.tagName("input");

    public Card3PersonalDetails(WebDriver driver) {
       this.driver = driver;
    }

    public Card3PersonalDetails fillInputFields(String keysToSend) {
        List<WebElement> inputFields = driver.findElements(inputFieldsLocator);

        for (WebElement inputField : inputFields) {
            inputField.clear();
            inputField.sendKeys(keysToSend);
        }
        return this;
    }

    private WebElement findDivByText(String text){
        return driver.findElement(By.xpath(String.format("//div[text()='%s']", text)));
    }

    public Card3PersonalDetails selectDropdownFields(int year, int day, String month) {
        List<WebElement> dropdownFields = driver.findElements(dropdownFieldsLocator);

        for (WebElement dropdownField : dropdownFields) {
            dropdownField.click();

            if (dropdownField.getText().equals("Choose a title")) {
                findDivByText("Mrs").click();
                continue;
            }

            if (dropdownField.getText().equals("Year")) {
                findDivByText(String.valueOf(year)).click();
            } else if (dropdownField.getText().equals("Day")) {
                findDivByText(String.valueOf(day)).click();
            } else if (dropdownField.getText().equals("Month")){
                findDivByText(month).click();
            } else {
                driver.findElement(RelativeLocator.with(nonDateItemLocator).near(dropdownField)).click();
            }
        }
        return this;
    }

    public Card4ProveYouAreHuman goToNextPage() {
        driver.findElement(nextButtonLocator).click();
        return new Card4ProveYouAreHuman(driver);
    }
}
