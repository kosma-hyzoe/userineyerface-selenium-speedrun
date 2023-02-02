package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YouAreAwesome {
    private final WebDriver driver;
    By timerLocator = By.className("timer");
    By youAreAwesomeHeaderLocator = By.xpath("//h1[text()='You are awesome!']");

    public YouAreAwesome(WebDriver driver) {
        if (!driver.findElement(youAreAwesomeHeaderLocator).isDisplayed()){
            throw new IllegalStateException("Failed to display the 'You are awesome' page");
        }

        this.driver = driver;
    }

    public String getTime(){
        return driver.findElement(timerLocator).getText();
    }
}
