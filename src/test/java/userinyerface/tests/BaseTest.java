package userinyerface.tests;

import java.io.IOException;
import java.io.InputStream;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {
    protected static Browser browser = getBrowser();
    protected static final ObjectMapper mapper = new ObjectMapper();
    protected static Logger logger = Logger.getInstance();
    public static JsonNode testData;

    static {
        try (InputStream inputStream = BaseTest.class.getClassLoader().getResourceAsStream("testData.json")) {
            testData = new ObjectMapper().readTree(inputStream);
        } catch (IOException e) {
            logger.error("Failed to load config.json from resources: \n" + e.getMessage());
        }
    }

    @BeforeMethod
    public void setUp() {
        browser.goTo(testData.get("baseUrl").asText());
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
