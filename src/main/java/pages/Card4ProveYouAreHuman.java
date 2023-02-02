package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Card4ProveYouAreHuman {
    private final WebDriver driver;
    private final By checkboxesLocator = By.className("checkbox__box");
    private final By validateButtonLocator = By.xpath("//button[text()='Validate']");

    public Card4ProveYouAreHuman(WebDriver driver) {
        this.driver = driver;
    }

    public void checkAllCheckboxes(){
        for (WebElement checkbox : driver.findElements(checkboxesLocator)){
            checkbox.click();
        }
    }

    public YouAreAwesome validate() {
        driver.findElement(validateButtonLocator).click();
        return new YouAreAwesome(driver);
    }
}
