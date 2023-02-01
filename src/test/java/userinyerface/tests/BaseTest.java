package userinyerface.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import org.testng.annotations.*;
import userinyerface.util.Json;

public class BaseTest {
    public static Logger logger = Logger.getInstance();
    protected static Browser browser;

    public static String testData;
    @BeforeMethod
    public void setUp() {
        InputStream testDataInputStream = getClass().getClassLoader().getResourceAsStream("testData.json");
        try{
            testData = new String(testDataInputStream.readAllBytes());
        } catch (IOException e){
            logger.error(e.getMessage());
            throw new IOError(e);
        }

        String baseUrl = Json.parseStringToJsonNode(testData).get("baseUrl").asText();
        browser = AqualityServices.getBrowser();
        browser.goTo(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
