package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import settings.ApiManager;

@Feature("Facebook Post")
public class TestPost {

    @Test(description = "Test Post creation")
    public void testCreatePost() {
        String testMessage = "test message";

        ApiManager apiManager = new ApiManager();
        apiManager.createPost(testMessage);

        String postId = apiManager.getPostId();
        apiManager.getPost(postId);

        Assert.assertEquals(apiManager.getPostMessage(), testMessage, "Message in created Post doesn't match specified");
    }

    @Test(description = "Test Post update")
    public void testUpdatePost() {
        String testMessage = "test message updated";

        ApiManager apiManager = new ApiManager();
        apiManager.getAllPosts();

        String postId = apiManager.getPostId();
        apiManager.updatePost(postId, testMessage);
        apiManager.getPost(postId);

        Assert.assertEquals(apiManager.getPostMessage(), testMessage, "Post message doesn't contain updated text");
    }

    @Test(description = "Test Post delete")
    public void testDeletePost() {
        ApiManager apiManager = new ApiManager();
        apiManager.getAllPosts();

        String postIdToDelete = apiManager.getPostId();
        apiManager.deletePost(postIdToDelete);
        apiManager.getAllPosts();

        String lastPostId = apiManager.getPostId();
        Assert.assertNotEquals(postIdToDelete, lastPostId, "Post that must be deleted is still in the feed");
    }
}
