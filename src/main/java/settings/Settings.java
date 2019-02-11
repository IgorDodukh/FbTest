package settings;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.logging.Logger;

public class Settings {
    private Logger logger = Logger.getLogger(Settings.class.getName());

    private static String accessToken = "";
    private static final String userId = "105597373911925";
    private static final String baseUrl = "https://graph.facebook.com/";

    private String getBaseUrl() {
        return baseUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        Settings.accessToken = accessToken;
    }

    @BeforeSuite
    public void setup() throws IOException {
        logger.info("Setup");
        PropertiesManager properties = new PropertiesManager();
        String token = properties.getPropetyValues();
        setAccessToken(token);
        RestAssured.baseURI = getBaseUrl();
    }

    @AfterSuite
    public void tearDown() {
        logger.info("Teardown");
    }
}
