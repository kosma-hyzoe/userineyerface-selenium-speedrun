package userinyerface.tests;

import java.util.ArrayList;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import userinyerface.models.LoginInfo;
import userinyerface.pages.Card1LoginInfo;
import userinyerface.pages.Card2ThisIsMe;
import userinyerface.pages.Card3PersonalDetails;
import userinyerface.pages.Home;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class UserinyerfaceTest extends BaseTest {
    @Test
    public void cards1to3ShouldDisplay() throws JsonProcessingException {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        Card1LoginInfo card1LoginInfo = home.goToTheNextPage();
        assertTrue(card1LoginInfo.state().isDisplayed(), "Failed to display card 1");

        LoginInfo loginInfo = mapper.readValue(testData.get("loginInfo").toString(), LoginInfo.class);
        card1LoginInfo.fillPasswordAndEmail(loginInfo);
        card1LoginInfo.acceptTermsAndConditions();

        Card2ThisIsMe card2ThisIsMe = card1LoginInfo.goToTheNextPage();
        assertTrue(card2ThisIsMe.state().isDisplayed(), "Failed to display card 2");

        ArrayList<String> interests = mapper.readValue(testData.get("interests").toString(), ArrayList.class);
        card2ThisIsMe.chooseInterests(interests);
        card2ThisIsMe.uploadImageManually();

        Card3PersonalDetails card3PersonalDetails = card2ThisIsMe.goToTheNextPage();
        assertTrue(card3PersonalDetails.state().isDisplayed(), "Failed to display card 3");
    }

    @Test
    public void canHideHelpForm()  {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        Card1LoginInfo card1LoginInfo = home.goToTheNextPage();
        assertTrue(card1LoginInfo.state().isDisplayed(), "Failed to display card 1");

        card1LoginInfo.hideHelpForm();
        assertTrue(card1LoginInfo.isHelpFormHidden(), "Failed to hide the help form");
    }

    @Test
    public void canCloseCookies() {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        Card1LoginInfo card1LoginInfo = home.goToTheNextPage();
        assertTrue(card1LoginInfo.state().isDisplayed(), "Failed to display card 1");

        card1LoginInfo.acceptCookies();
        assertTrue(card1LoginInfo.isCookiesPanelClosed(), "Failed to close the cookies panel");
    }

    @Test
    public void doesTimerStartWith00_00_00() {
        Home home = new Home();
        assertTrue(home.state().isDisplayed(), "Failed to display the home page");

        Card1LoginInfo card1LoginInfo = home.goToTheNextPage();
        assertTrue(card1LoginInfo.state().isDisplayed(), "Failed to display card 1");
        assertEquals(card1LoginInfo.getTimerText(), "00:00:00");
    }
}
