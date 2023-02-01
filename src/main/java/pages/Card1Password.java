package pages;

import forms.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;


public class Card1Password extends Card {
    By subDomainLocator = By.className("dropdown__field");
    By dotOrgChoiceLocator = RelativeLocator.with(By.tagName("div")).below(subDomainLocator);
    // todo try by class only
    By iDoNotAcceptTermsAndConditionsLocator = By.xpath("//span[@class=\"checkbox__box\"]/span");
    By nextLinkLocator = By.xpath("//a[@class=\"button--secondary\" and text()=\"Next\"]");

    public Card1Password(WebDriver driver) {
        super(driver);
    }

    private static By getCredentialsLocator(String placeholder){
        return By.xpath(String.format("//input[contains(@placeholder, \"%s\")]", placeholder));
    }

    public void fillPasswordAndEmail(String emailRecipientName, String domain, String password) {
        WebElement choosePasswordInputField = driver.findElement(getCredentialsLocator("Choose Password"));
        choosePasswordInputField.clear();
        choosePasswordInputField.sendKeys(password);

        WebElement yourEmailInputField = driver.findElement(getCredentialsLocator("Your email"));
        yourEmailInputField.clear();
        yourEmailInputField.sendKeys(emailRecipientName);

        WebElement domainInputField = driver.findElement(getCredentialsLocator("Domain"));
        domainInputField.clear();
        domainInputField.sendKeys(domain);

        driver.findElement(subDomainLocator).click();
        driver.findElement(dotOrgChoiceLocator).click();
    }

    public void acceptTermsAndConditions() {
        driver.findElement(iDoNotAcceptTermsAndConditionsLocator).click();
    }

    public void goToTheNextPage() {
        driver.findElement(nextLinkLocator).click();
    }
}
