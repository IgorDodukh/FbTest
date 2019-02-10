package settings;

public final class EndPoint {
    public static final String getPosts = "/{userId}/posts?access_token={token}";
    public static final String createPost = "/{userId}/feed?message={postMessage}&access_token={token}";
    public static final String updatePost = "/{postId}?message={postMessage}&access_token={token}";
    public static final String getDeletePost = "/{postId}?access_token={token}";
}