package userinyerface.pages;

import static userinyerface.waits.ConditionalWaits.waitForTrue;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;

import org.openqa.selenium.By;
import userinyerface.models.LoginInfo;
import userinyerface.waits.WaitDuration;

public class Card1LoginInfo extends Form {
    private final IButton acceptCookiesButton = getElementFactory().getButton(
            By.xpath("//button[contains(text(), 'Not really, no')]"), "'Not really, no'");
    private final ILabel cookiesPanel = getElementFactory().getLabel(
            By.className("cookies"), "Red cookies panel");
    private final ITextBox subDomainDropdownMenu = getElementFactory().getTextBox(
            By.className("dropdown__field"), ".domain dropdown menu");
    private final ICheckBox iDoNotAcceptTermsAndConditionsCheckbox = getElementFactory().getCheckBox(
            By.className("checkbox__box"), "'I do not accept the Terms & Conditions'");
    private final ILink nextLink = getElementFactory().getLink(
            By.xpath("//a[@class='button--secondary' and text()='Next']"), "'Next'");
    private final IButton sendHelpFormToBottomButton = getElementFactory().getButton(
            By.xpath("//button/span[contains(text(), 'Send')]"), "'Send to bottom'");
    private final IButton sendHelpFormUpButton = getElementFactory().getButton(
            By.className("icon-chevron-up"), "'^'");
    private final ITextBox timer = getElementFactory().getTextBox(
            By.className("timer--white"), "White centered timer");

    public Card1LoginInfo() {
        super(By.className("login-form__terms-conditions"), "card1LoginInfo");
    }

    public ITextBox getCredentialsInputField(String placeholder){
        return getElementFactory().getTextBox(
                By.xpath(String.format("//input[contains(@placeholder, '%s')]", placeholder)),
                placeholder + " credentials input field");
    }

    public void fillPasswordAndEmail(LoginInfo loginInfo) {
        getCredentialsInputField("Choose Password").clearAndType(loginInfo.getPassword());
        getCredentialsInputField("Your email").clearAndType(loginInfo.getEmailRecipientName());
        getCredentialsInputField("Domain").clearAndType(loginInfo.getEmailDomain());

        subDomainDropdownMenu.click();

        String emailSubDomainXpath = String.format("//div[contains(@class, 'dropdown__list-item') and text()='%s']",
                loginInfo.getEmailSubDomain());
        ITextBox subDomainCheckBoxChoice = getElementFactory().getTextBox(By.xpath(emailSubDomainXpath),
                String.format("'%s' choice", loginInfo.getEmailSubDomain()));

        subDomainCheckBoxChoice.click();
    }

    public void hideHelpForm() {
        sendHelpFormToBottomButton.click();
        waitForTrue(this::isHelpFormHidden, WaitDuration.LONG,  "Help form should be hidden");
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

    public Card2ThisIsMe goToTheNextPage() {
        nextLink.click();
        return new Card2ThisIsMe();
    }

    public void acceptTermsAndConditions() {
        iDoNotAcceptTermsAndConditionsCheckbox.click();
    }
}
