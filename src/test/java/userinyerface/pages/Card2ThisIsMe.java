package userinyerface.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

import java.util.ArrayList;

import org.openqa.selenium.By;
import userinyerface.waits.WaitDuration;

import static userinyerface.waits.ConditionalWaits.waitForTrue;

public class Card2ThisIsMe extends Form {
    private final ILink uploadImageLink = AqualityServices.getElementFactory().getLink(
            By.xpath("//a[contains(@class, 'upload-button')]"), "upload image");
    private final ICheckBox unselectAllInterests = AqualityServices.getElementFactory().getCheckBox(
            By.xpath("//label[@for='interest_unselectall']"), "'Unselect all'");
    private final IButton nextButton = AqualityServices.getElementFactory().getButton(
            By.xpath("//button[text()='Next']"), "'Next'");
    private final ILabel uploadedAvatarImage = AqualityServices.getElementFactory().getLabel(
            By.className("avatar-and-interests__avatar-image"), "A user uploaded avatar image");

    public Card2ThisIsMe() {
        super(By.className("avatar-and-interests"), "card2ThisIsMe");
    }

    public void uploadImageManually() {
       uploadImageLink.click();
       waitForTrue(() -> uploadedAvatarImage.state().isDisplayed(), WaitDuration.LONG,
               "image should be manually uploaded");
    }

    private static ICheckBox getInterestsCheckbox(String interest){
        return AqualityServices.getElementFactory().getCheckBox(
                By.xpath(String.format("//label[@for='interest_%s']", interest)),
                interest + "interests checkbox");
    }

    public void chooseInterests(ArrayList<String> interests) {
        unselectAllInterests.click();
        for (String interest : interests){
            getInterestsCheckbox(interest).click();
        }
    }

    public Card3PersonalDetails goToTheNextPage() {
        nextButton.click();
        return new Card3PersonalDetails();
    }
}
