package userinyerface.pages;

import org.openqa.selenium.By;
import aquality.selenium.forms.Form;

public class Card3PersonalDetails extends Form {

    public Card3PersonalDetails() {
        super(By.xpath("//h3[contains(text(), \"Personal details\")]"), "card3PersonalDetails");
    }
}
