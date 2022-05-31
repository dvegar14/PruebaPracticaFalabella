package definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.Comment;
import pojo.Post;
import pojo.User;
import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FeatureB {
    Properties prop = Utils.leerPropiedades("urls.properties");
    Response response;
    int userID;
    int postID;

    @When("se llama a \\/users obtienendo userId de registro num {string} validando name y username")
    public void se_llama_a_users_obtienendo_user_id_de_registro_num_validando_name_y_username(String numero) {
        try {
            response = Utils.getResponse("JSON", prop.getProperty("ENDPOINT_USERS"), Utils.serviceMethods.GET);
            List<User> users = Arrays.asList(response.getBody().as(User[].class));
            if (Integer.parseInt(numero) <= users.size()) {
                userID = users.get(Integer.parseInt(numero) - 1).getId();
            } else {
                Assert.fail("Numero de registro que se desea capturar, no existe en listado de usuarios");
            }
        } catch (Exception ex) {
            Assert.fail("Error al realizar llamada a servicio users");
        }
    }

    @Then("se llama a \\/posts validando title y body desde respuesta, consulta con userID e imprime ultimo post")
    public void se_llama_a_posts_validando_title_y_body_desde_respuesta_consulta_con_user_id_e_imprime_ultimo_post() {
        try {
            response = Utils.getResponse("JSON", prop.getProperty("ENDPOINT_POSTS").replace("$USERID", String.valueOf(userID)), Utils.serviceMethods.GET);
            List<Post> posts = Arrays.asList(response.getBody().as(Post[].class));

            for (Post post : posts) {
                if (post.getTitle().equals(null) || post.getBody().equals(null)) {
                    Assert.fail("Tittle o body de registro " + post.getId() + ", asociado a userID: " + userID + " esta vacio.");
                }
            }
            postID = posts.get(posts.size() - 1).getId();
            System.out.println("RESULTADO FEATUREB: " + posts.get(posts.size() - 1).toString());
        } catch (Exception ex) {
            Assert.fail("Error al realizar llamada a servicio posts");
        }
    }

    @Then("se llama a \\/comments obteniendo registros de postID, validando email y name del primero")
    public void se_llama_a_comments_obteniendo_registros_de_post_id_validando_email_y_name_del_primero() {
        try {
            response = Utils.getResponse("JSON", prop.getProperty("ENDPOINT_COMMENTS").replace("$POSTID", String.valueOf(postID)), Utils.serviceMethods.GET);
            List<Comment> comments = Arrays.asList(response.getBody().as(Comment[].class));

            if(comments.size() > 0){
                System.out.println("Name: " + comments.get(0).getName() + " - Email: " + comments.get(0).getEmail());
            }else{
                Assert.fail("No se obtuvieron comentarios para postId: " + postID);
            }
        } catch (Exception ex) {
            Assert.fail("Error al realizar llamada a servicio comments");
        }
    }

}
