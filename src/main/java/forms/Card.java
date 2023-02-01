package forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Card {
    protected final WebDriver driver;
    By timerLocator = By.className("timer");

    public Card(WebDriver driver) {
        this.driver = driver;
    }

    public String getTime(){
        return driver.findElement(timerLocator).getText();
    }
}
