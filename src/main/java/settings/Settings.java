package settings;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Logger;

public class Settings {
    private Logger logger = Logger.getLogger(Settings.class.getName());

    private static final String accessToken = "EAAF4HFgcxY0BAPgZC0o6TT9kPcfnkWY6DR34WzzQcGMIE9ga6ZCLbivZADJWugPab5nrIxDZAZCwC7qkLBvPnF3DtmTWjrDLUzCL5pKFXCOxGZAe4ZBm2DEINZBXX8WALl3Kc7nu39GFZAqulz55edXV6NAskjZCZC9KICZBxIf7vfBYAFZCyKZBdEMjzo8nG4hfZAZAjVh0jn2FSEXzLH54u8cwOoX5xqVkfBexwM0ZD";
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

    @BeforeSuite
    public void setup() {
        logger.info("Setup");
        RestAssured.baseURI = getBaseUrl();
    }

    @AfterSuite
    public void tearDown() {
        logger.info("Teardown");
    }
}
