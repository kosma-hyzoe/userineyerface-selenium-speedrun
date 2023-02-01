package userinyerface.util;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    private static final ObjectMapper objectMapper = new ObjectMapper();

     public static JsonNode parseStringToJsonNode(String string) {
        try {
            return objectMapper.readTree(string);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().warn("Failed to parse string to json node: " + e.getOriginalMessage());
            throw new Error(new IllegalArgumentException());
        }
    }
}
