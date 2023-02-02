package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;


public class Card1LoginInfo {
    private final WebDriver driver;
    private final By subDomainLocator = By.className("dropdown__field");
    private final By dotOrgChoiceLocator = RelativeLocator.with(By.tagName("div")).below(subDomainLocator);
    private final By iDoNotAcceptTermsAndConditionsLocator = By.className("checkbox__box");
    private final By nextLinkLocator = By.xpath("//a[contains(@class, 'button--secondary') and text()='Next']");

    public Card1LoginInfo(WebDriver driver) {
       this.driver = driver;
    }

    public Card1LoginInfo fillInLoginInfo(String emailRecipientName, String emailDomain, String password) {
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

        return this;
    }

    public Card1LoginInfo acceptTermsAndConditions() {
        driver.findElement(iDoNotAcceptTermsAndConditionsLocator).click();
        return this;
    }

    public Card2ThisIsMe goToTheNextPage() {
        driver.findElement(nextLinkLocator).click();
        return new Card2ThisIsMe(driver);
    }
}
