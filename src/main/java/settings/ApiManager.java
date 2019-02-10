package settings;

import io.qameta.allure.Step;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static settings.Specification.postBodyResponse;
import static settings.Specification.successResponse;

public class ApiManager extends Settings{
    private Logger logger = Logger.getLogger(Settings.class.getName());

    private ResponseBody responseBody;
    private String postMessage;
    private String postId;

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Step("Send request to create Post with message: {postMessage}")
    public void createPost(String postMessage) {
        logger.info("Create new post");

        ResponseBody response = given().when()
                .pathParam("userId", getUserId())
                .pathParam("postMessage", postMessage)
                .pathParam("token", getAccessToken())
                .post(EndPoint.createPost)
                .then().spec(successResponse)
                .extract().response().getBody();

        setResponseBody(response);

        String postId = getJsonValue("id");
        setPostId(postId);
    }

    @Step("Send request to update Post by id [{postId}] with message: {postMessage}")
    public void updatePost(String postId, String postMessage) {
        logger.info(String.format("Update post [%s] with message: %s", postId, postMessage));

        ResponseBody response = given().when()
                .pathParam("postId", postId)
                .pathParam("postMessage", postMessage)
                .pathParam("token", getAccessToken())
                .post(EndPoint.updatePost)
                .then().spec(successResponse)
                .extract().response().getBody();

        setResponseBody(response);
    }

    @Step("Send request to delete Post by id: {postId}")
    public void deletePost(String postId) {
        logger.info(String.format("Delete post [%s]", postId));

        ResponseBody response = given().when()
                .pathParam("postId", postId)
                .pathParam("token", getAccessToken())
                .post(EndPoint.getDeletePost)
                .then().spec(successResponse)
                .extract().response().getBody();

        setResponseBody(response);
    }

    @Step("Send request to receive all Posts")
    public void getAllPosts() {
        logger.info("Get existing posts list");

        ResponseBody response = given().when()
                .pathParam("userId", getUserId())
                .pathParam("token", getAccessToken())
                .when()
                .get(EndPoint.getPosts)
                .then().spec(postBodyResponse)
                .extract().response().getBody();

        setResponseBody(response);

        String bodyStringValue = getResponseBody().asString();

        JSONObject responseBodyJSON = new JSONObject(bodyStringValue);
        JSONObject latestPost = responseBodyJSON.getJSONArray("data").getJSONObject(0);

        String message = latestPost.getString("message");
        setPostMessage(message);

        String id = latestPost.getString("id");
        setPostId(id);
    }

    @Step("Send request to receive single Post by Id: {postId}")
    public void getPost(String postId) {
        logger.info(String.format("Get post by id: %s", postId));

        ResponseBody response = given().when()
                .pathParam("postId", postId)
                .pathParam("token", getAccessToken())
                .get(EndPoint.getDeletePost)
                .then().spec(postBodyResponse)
                .extract().response().getBody();

        setResponseBody(response);

        String message = getJsonValue("message");
        setPostMessage(message);

        String id = getJsonValue("id");
        setPostId(id);
    }

    @Step("Get json value: {valueName}")
    private String getJsonValue(String valueName) {
        String bodyStringValue = getResponseBody().asString();
        JSONObject responseBodyJSON = new JSONObject(bodyStringValue);

        return responseBodyJSON.getString(valueName);
    }
}
