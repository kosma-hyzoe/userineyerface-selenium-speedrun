package userinyerface.pages;

import org.openqa.selenium.By;
import aquality.selenium.forms.Form;
import aquality.selenium.elements.interfaces.ILink;

public class Home extends Form {
    private final ILink startLink = getElementFactory().getLink(By.className("start__link"), "Start link");

    public Home() {
        super(By.className("start__button"), "Main page");
    }

    public Card1LoginInfo goToTheNextPage(){
        startLink.click();
        return new Card1LoginInfo();
    }
}