package userinyerface.forms.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.By;
import userinyerface.util.Json;

import static userinyerface.models.InterestsCheckboxFactory.getInterestsCheckbox;
import static userinyerface.tests.BaseTest.testData;

public class Card2ThisIsMe extends Form {
    private final ILink uploadImageLink = AqualityServices.getElementFactory().getLink(
            By.xpath("//a[contains(@class, \"upload-button\")]"), "upload image link");
    private final ICheckBox unselectAllInterests = AqualityServices.getElementFactory().getCheckBox(
            By.xpath("//label[@for=\"interest_unselectall\"]"), "\"Unselect all\" checkbox");
    private final IButton nextButton = AqualityServices.getElementFactory().getButton(
            By.xpath("//button[text()=\"Next\"]"), "\"Next\" button");
    private final ILabel uploadedAvatarImage = AqualityServices.getElementFactory().getLabel(
            By.className("avatar-and-interests__avatar-image"), "A user uploaded avatar image");

    public Card2ThisIsMe() {
        super(By.xpath("//h2[text()=\"This is me\"]"), "Avatar image");
    }

    public void uploadImage() {
        uploadImageLink.click();
        JsonNode testDataJsonNode = Json.parseStringToJsonNode(testData);
        try {
            assert testDataJsonNode != null;
            if (Objects.equals(System.getProperty("os.name"), "Windows")){

                String filePath = testDataJsonNode.get("fileUploadScript").get("Windows").asText();
                ProcessBuilder processBuilder = new ProcessBuilder(filePath);
                processBuilder.start();
            }
            else if (Objects.equals(System.getProperty("os.name"), "Linux")){
                String filePath = testDataJsonNode.get("fileUploadScript").get("Linux").asText();
                ProcessBuilder processBuilder = new ProcessBuilder(filePath);
                processBuilder.start();
            }
            else {
                String message = String.format("operating system '%s' not supported", System.getProperty("os.name"));
                AqualityServices.getLogger().error(message);
                throw new RuntimeException(message);
            }
        } catch (IOException e) {
            AqualityServices.getLogger().warn(e.toString());
            AqualityServices.getLogger().warn(String.format("Using script to upload the image failed." +
                    " please upload the image manually within %s seconds...",
                    testDataJsonNode.get("waits").get("uploadImage").get("seconds").asInt()));
        }
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> uploadedAvatarImage.state().isDisplayed(),
                    Duration.ofSeconds(testDataJsonNode.get("waits").get("uploadImage").get("seconds").asInt()),
                    Duration.ofMillis(testDataJsonNode.get("waits").get("uploadImage").get("milliseconds").asInt()),
                    "An image should be uploaded manually and displayed on page");
        } catch (TimeoutException e) {
            AqualityServices.getLogger().error("Wait \"An image should be uploaded manually and displayed on page\" failed:" +
                    e.getMessage());
        }
    }

    public void chooseInterests() {
        unselectAllInterests.click();
        String[] interests = {"ponies", "enveloppes", "closets"};
        for (String interest : interests){
            getInterestsCheckbox(interest).click();
        }
//        poniesInterest.click();
//        envelopesInterest.click();
//        closetsInterest.click();
    }

    public void goToTheNextPage() {
        nextButton.click();
    }
}
