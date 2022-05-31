package definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pojo.Post;
import pojo.User;
import utils.ServiceMethods;
import utils.Utils;

import java.util.List;
import java.util.Properties;

public class FeatureA {
    Properties prop = Utils.leerPropiedades("urls.properties");
    int userID;

    @When("se llama a \\/users y se obtiene userId en posicion {string}")
    public void se_llama_a_users_y_se_obtiene_user_id_en_posicion(String numero) {
        try {
            List<User> users = ServiceMethods.listarUsuarios("JSON", prop, Utils.methods.GET);
            if (Integer.parseInt(numero) <= users.size()) {
                userID = users.get(Integer.parseInt(numero) - 1).getId();
            } else {
                Assert.fail("Numero de registro que se desea capturar, no existe en listado de usuarios");
            }
        } catch (Exception ex) {
            Assert.fail("Error al realizar llamada a servicio users");
        }
    }

    @Then("se llama a \\/posts y se valida title y body desde respuesta consultando con userID")
    public void se_llama_a_posts_y_se_valida_title_y_body_desde_respuesta_consultando_con_user_id() {
        try {
            List<Post> posts = ServiceMethods.listarPost("JSON", prop, String.valueOf(userID), Utils.methods.GET);
            for (Post post : posts) {
                if (post.getTitle().equals(null) || post.getBody().equals(null)) {
                    Assert.fail("Tittle o body de post ID: " + post.getId() + ", asociado a userID: " + userID + " esta vacio.");
                }
            }
            System.out.println("RESULTADO FEATUREA: EXITOSO");
        } catch (Exception ex) {
            Assert.fail("Error al realizar llamada a servicio posts");
        }
    }


}
