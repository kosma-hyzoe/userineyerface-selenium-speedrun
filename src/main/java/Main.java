import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.Card1Password;
import pages.Home;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static JsonNode config;
    static {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.json")) {
            config = new ObjectMapper().readTree(inputStream);
        } catch (IOException e) {
            logger.error("Failed to load config.json from resources: \n" + e.getMessage());
        }
    }
    private static long startTime;

    private static final ChromeDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", config.get("driverPath").asText());

        ChromeOptions chromeOptions = new ChromeOptions();
        for (JsonNode optionsArgument : config.get("optionsArguments")){
            chromeOptions.addArguments(optionsArgument.asText());
        }

        driver = new ChromeDriver(chromeOptions);
    }

    public static void main(String[] args) {
        try {
            driver.get("https://userinyerface.com/");

            Home home = new Home(driver);
            home.goToTheNextPage();

            startTime = System.nanoTime();

            Card1Password card1Password = new Card1Password(driver);
            card1Password.fillPasswordAndEmail("foo", "bar", "Foobar1234");
            card1Password.acceptTermsAndConditions();
            System.out.println("Time on page: " + card1Password.getTime());
            card1Password.goToTheNextPage();
        } finally {
            long endTime = System.nanoTime();
            System.out.println("Measured time: " + getHumanReadableTime(endTime - startTime));
            driver.quit();

        }
    }
    private static String getHumanReadableTime(Long nanos){
        long nanosRemainder = nanos % (1_000_000_000);
        long seconds = nanos / (1_000_000_000);
        long secondsRemainder = seconds % 60;
        long minutes = seconds / 60;

        return String.format("%dm:%ds:%dns", minutes, secondsRemainder, nanosRemainder);

    }
}