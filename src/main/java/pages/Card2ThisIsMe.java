package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Card2ThisIsMe {
    private final WebDriver driver;
    By uploadImageLinkLocator = By.xpath("//a[contains(@class, 'upload-button')]");
    By unselectAllInterestsLocator = By.xpath("//label[@for='interest_unselectall']");
    By userUploadedAvatarImageLocator = By.className("avatar-and-interests__avatar-image");
    By nextButtonLocator = By.xpath("//button[text()='Next']");

    public Card2ThisIsMe(WebDriver driver) {
        this.driver = driver;
    }

    public Card2ThisIsMe chooseInterests(String... interests) {
        driver.findElement(unselectAllInterestsLocator).click();
        for (String interest : interests) {
            driver.findElement(By.xpath(String.format("//label[@for='interest_%s']", interest))).click();
        }

        return this;
    }

    public Card2ThisIsMe uploadImageManually(int secondsToUpload) {
        driver.findElement(uploadImageLinkLocator).click();
        new WebDriverWait(driver, Duration.ofSeconds(secondsToUpload)).until(
                ExpectedConditions.visibilityOfElementLocated(userUploadedAvatarImageLocator));
        return this;
    }

    public Card3PersonalDetails goToNextPage() {
        driver.findElement(nextButtonLocator).click();
        return new Card3PersonalDetails(driver);
    }
}
