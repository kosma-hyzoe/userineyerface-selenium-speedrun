package userinyerface.forms.pages;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.getConditionalWait;
import static userinyerface.tests.BaseTest.testData;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import userinyerface.util.Json;

public class Card1Password extends Form {

    private final IButton acceptCookiesButton = getElementFactory().getButton(
            By.xpath("//button[text()=\"Not really, no\"]"), "\"Not really, no\" button");
    private final ILabel cookiesPanel = getElementFactory().getLabel(
            By.xpath("//div[@class=\"cookies\"]"), "Red cookies panel");
    private final ITextBox subDomainDropdownMenu = getElementFactory().getTextBox(
            By.className("dropdown__field"), ".domain dropdown menu");
    public ITextBox getCredentialsInputField(String placeholder){
        return getElementFactory().getTextBox(
                By.xpath(String.format("//input[contains(@placeholder, \"%s\")]", placeholder)),
                placeholder + " credentials input field");
    }
    private final ICheckBox iDoNotAcceptTermsAndConditionsCheckbox = getElementFactory().getCheckBox(
            By.xpath("//span[@class=\"checkbox__box\"]/span"),
            "\"I do not accept the Terms & Conditions\" checkbox");
    private final ILink termsAndConditionsLink = getElementFactory().getLink(
            By.className("login-form__terms-conditions"), "\"Terms & Conditions\" link");
    private final ILabel termsAndConditionsScroller = getElementFactory().getLabel(
            By.xpath("//div[contains(@class, \"scroller\")]"), "Terms and conditions scroller");
    private final IButton acceptButton = getElementFactory().getButton(
            By.xpath("//button[text()=\"Accept\"]"), "Accept button");
    private final ILink nextLink = getElementFactory().getLink(
            By.xpath("//a[@class=\"button--secondary\" and text()=\"Next\"]"), "\"Next\" link");
    private final IButton sendHelpFormToBottomButton = getElementFactory().getButton(
            By.xpath("//button/span[text()=\"Send\"]"), "\"Send to bottom\" button");
    private final IButton sendHelpFormUpButton = getElementFactory().getButton(
            By.className("icon-chevron-up"), "\"^\" button");
    private final ITextBox timer = getElementFactory().getTextBox(
            By.className("timer--white"), "White centered timer");

    public Card1Password() {
        super(By.className("login-form__terms-conditions"), "\"Terms & Conditions\" link");
    }

    public void fillPasswordAndEmail(String emailRecipientName, String domain, String subDomain, String password) {
        getCredentialsInputField("Choose Password").clearAndType(password);
        getCredentialsInputField("Your email").clearAndType(emailRecipientName);
        getCredentialsInputField("Domain").clearAndType(domain);

        subDomainDropdownMenu.click();
        ITextBox subDomainCheckBoxChoice = getElementFactory().getTextBox(
                By.xpath(String.format("//div[@class=\"dropdown__list-item\" and text()=\"%s\"]", subDomain)),
                String.format("\"%s\" choice", subDomain));
        subDomainCheckBoxChoice.click();
    }

    public void acceptTermsAndConditions() {
        iDoNotAcceptTermsAndConditionsCheckbox.click();
        termsAndConditionsLink.click();
        getLogger().info("Initializing Selenium Actions...");
        Actions actions = new Actions(getBrowser().getDriver());
        getLogger().info("Pressing and holding the 'LEFT_ALT' key via actions...");
        actions.keyDown(Keys.LEFT_ALT).perform();
        getLogger().info("Dragging and dropping termsAndConditionsScroller to acceptButton via actions...");
        actions.dragAndDrop(termsAndConditionsScroller.getElement(), acceptButton.getElement()).perform();
        getLogger().info("Releasing the the 'LEFT_ALT' key via actions...");
        actions.keyUp(Keys.LEFT_ALT).perform();
        acceptButton.click();
    }

    public void hideHelpForm() {
        JsonNode testDataJsonNode = null;
        testDataJsonNode = Json.parseStringToJsonNode(testData);

        sendHelpFormToBottomButton.click();
        try {
            getConditionalWait().waitForTrue(this::isHelpFormHidden,
                    Duration.ofSeconds(testDataJsonNode.get("waits").get("hideHelpForm").get("seconds").asInt()),
                    Duration.ofMillis(testDataJsonNode.get("waits").get("hideHelpForm").get("milliseconds").asInt()),
                    "Help form should be hidden");
        } catch (TimeoutException e) {
            throw new AssertionError("Failed to hide the help form: " + e.getMessage());
        }
    }

    public boolean isHelpFormHidden() {
        return !sendHelpFormUpButton.state().isDisplayed();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public boolean isCookiesPanelClosed() {
        return !cookiesPanel.state().isDisplayed();
    }

    public String getTimerText() {
        return timer.getText();
    }

    public void goToTheNextPage() {
        nextLink.click();
    }
}
