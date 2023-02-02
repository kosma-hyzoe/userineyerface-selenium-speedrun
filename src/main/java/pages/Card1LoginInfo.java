package pages;

import forms.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;


public class Card1LoginInfo extends Card {
    By subDomainLocator = By.className("dropdown__field");
    By dotOrgChoiceLocator = RelativeLocator.with(By.tagName("div")).below(subDomainLocator);
    By iDoNotAcceptTermsAndConditionsLocator = By.className("checkbox__box");
    By nextLinkLocator = By.xpath("//a[contains(@class, 'button--secondary') and text()='Next']");

    public Card1LoginInfo(WebDriver driver) {
        super(driver);
    }

    public void fillInLoginInfo(String emailRecipientName, String emailDomain, String password) {
        final String[][] inputFields = {
                {"Choose Password", password},
                {"Your email", emailRecipientName},
                {"Domain", emailDomain}
        };

        for (String[] field : inputFields){
            String placeholder = field[0];
            String value = field[1];

            By inputFieldLocator = By.xpath(String.format("//input[contains(@placeholder, '%s')]", placeholder));
            WebElement inputField = driver.findElement(inputFieldLocator);

            inputField.clear();
            inputField.sendKeys(value);
        }

        driver.findElement(subDomainLocator).click();
        driver.findElement(dotOrgChoiceLocator).click();
    }

    public void acceptTermsAndConditions() {
        driver.findElement(iDoNotAcceptTermsAndConditionsLocator).click();
    }

    public Card2ThisIsMe goToTheNextPage() {
        driver.findElement(nextLinkLocator).click();
        return new Card2ThisIsMe(driver);
    }
}
