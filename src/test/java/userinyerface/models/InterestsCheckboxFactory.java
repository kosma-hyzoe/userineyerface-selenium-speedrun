package userinyerface.models;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ICheckBox;
import org.openqa.selenium.By;

public class InterestsCheckboxFactory {
    public static ICheckBox getInterestsCheckbox(String interest){
           return AqualityServices.getElementFactory().getCheckBox(
                   By.xpath(String.format("//label[@for=\"interest_%s\"]", interest)),
                   interest + "interests checkbox");
    }
}
