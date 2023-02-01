package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
    private final WebDriver driver;
    By startLinkLocator = By.className("start__link");

    public Home(WebDriver driver) {
       this.driver = driver;
    }

    public void goToTheNextPage(){
        driver.findElement(startLinkLocator).click();
    }
}