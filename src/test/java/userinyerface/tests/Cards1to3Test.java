package userinyerface.tests;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import userinyerface.forms.pages.*;
import userinyerface.choices.SubDomain;


public class Cards1to3Test extends BaseTest {
    @Test
    public void cards1to3ShouldDisplay() {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");
        home.goToTheNextPage();

        Card1Password card1Password = new Card1Password();
        assertTrue(card1Password.state().isDisplayed(), "Failed to display card 1");

        card1Password.fillPasswordAndEmail("foo", "bar", ".org", "Foobar1234");
        card1Password.acceptTermsAndConditions();
        card1Password.goToTheNextPage();

        Card2ThisIsMe card2ThisIsMe = new Card2ThisIsMe();
        assertTrue(card2ThisIsMe.state().isDisplayed(), "Failed to display card 2");

        card2ThisIsMe.chooseInterests();
        card2ThisIsMe.uploadImage();
        card2ThisIsMe.goToTheNextPage();

        Card3PersonalDetails card3PersonalDetails = new Card3PersonalDetails();
        assertTrue(card3PersonalDetails.state().isDisplayed(), "Failed to display card 3");

    }

    @Test
    public void canHideHelpForm()  {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        home.goToTheNextPage();
        Card1Password card1Password = new Card1Password();
        assertTrue(card1Password.state().isDisplayed(), "Failed to display card 1");

        card1Password.hideHelpForm();
        assertTrue(card1Password.isHelpFormHidden(), "Failed to hide the help form");
    }
    @Test

    public void canCloseCookies() {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        home.goToTheNextPage();
        Card1Password card1Password = new Card1Password();
        assertTrue(card1Password.state().isDisplayed(), "Failed to display card 1");

        card1Password.acceptCookies();
        assertTrue(card1Password.isCookiesPanelClosed(), "Failed to close the cookies panel");
    }

    @Test
    public void doesTimerStartWith00_00_00() {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        home.goToTheNextPage();
        Card1Password card1Password = new Card1Password();
        assertTrue(card1Password.state().isDisplayed(), "Failed to display card 1");
        assertEquals(card1Password.getTimerText(), "00:00:00");
    }
}
