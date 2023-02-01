package userinyerface.forms.pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Card3PersonalDetails extends Form {

    public Card3PersonalDetails() {
        super(By.xpath("//h3[contains(text(), \"Personal details\")]"), "Personal details header");
    }
}
