package pages;

import forms.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Card2ThisIsMe extends Card {
    private final By uploadImageLinkLocator = By.xpath("//a[contains(@class, 'upload-button')]");
    private final By unselectAllInterestsLocator = By.xpath("//label[@for='interest_unselectall']");
    private final By userUploadedAvatarImageLocator = By.className("avatar-and-interests__avatar-image");
    private final By nextButtonLocator = By.xpath("//button[text()='Next']");

    public Card2ThisIsMe(WebDriver driver) {
        super(driver);
    }

    public void chooseInterests(String... interests) {
        driver.findElement(unselectAllInterestsLocator).click();
        for (String interest : interests) {
            driver.findElement(By.xpath(String.format("//label[@for='interest_%s']", interest))).click();
        }
    }

    public void uploadImageManually(int secondsToUpload) {
        driver.findElement(uploadImageLinkLocator).click();
        new WebDriverWait(driver, Duration.ofSeconds(secondsToUpload)).until(
                ExpectedConditions.visibilityOfElementLocated(userUploadedAvatarImageLocator));
    }

    public Card3PersonalDetails goToNextPage() {
        driver.findElement(nextButtonLocator).click();
        return new Card3PersonalDetails(driver);
    }
}
