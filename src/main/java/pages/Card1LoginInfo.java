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

    private WebElement getCredentials(String placeholder){
        return driver.findElement(By.xpath(String.format("//input[contains(@placeholder, '%s')]", placeholder)));
    }

    public void fillPasswordAndEmail(String emailRecipientName, String domain, String password) {
        WebElement choosePasswordInputField = getCredentials("Choose Password");
        choosePasswordInputField.clear();
        choosePasswordInputField.sendKeys(password);

        WebElement yourEmailInputField = getCredentials("Your email");
        yourEmailInputField.clear();
        yourEmailInputField.sendKeys(emailRecipientName);

        WebElement domainInputField = getCredentials("Domain");
        domainInputField.clear();
        domainInputField.sendKeys(domain);

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
