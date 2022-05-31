package utils;

import io.restassured.response.Response;
import pojo.Comment;
import pojo.Post;
import pojo.User;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ServiceMethods {

    public static List<User> listarUsuarios(String content, Properties prop, Utils.methods method) {
        Response response = Utils.getResponse(content, prop.getProperty("ENDPOINT_USERS"), method);
        List<User> users = Arrays.asList(response.getBody().as(User[].class));

        return users;
    }

    public static List<Post> listarPost(String content, Properties prop, String userID, Utils.methods method) {
        Response response = Utils.getResponse(content, prop.getProperty("ENDPOINT_POSTS").replace("$USERID", userID), method);
        List<Post> posts = Arrays.asList(response.getBody().as(Post[].class));

        return posts;
    }

    public static List<Comment> listarComments(String content, Properties prop, String postID, Utils.methods method) {
        Response response = Utils.getResponse(content, prop.getProperty("ENDPOINT_COMMENTS").replace("$POSTID", String.valueOf(postID)), method);
        List<Comment> comments = Arrays.asList(response.getBody().as(Comment[].class));

        return comments;
    }


}
