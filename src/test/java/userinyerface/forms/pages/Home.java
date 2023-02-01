package userinyerface.forms.pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class Home extends Form {
    private final ILink startLink = getElementFactory().getLink(By.className("start__link"), "Start link");

    public Home() {
        super(By.className("start__button"), "Main page");
    }

    public void goToTheNextPage(){
        startLink.click();
    }
}