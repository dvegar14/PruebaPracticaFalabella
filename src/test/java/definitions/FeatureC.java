package definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pojo.User;
import utils.ServiceMethods;
import utils.Utils;

import java.util.List;
import java.util.Properties;

public class FeatureC {
    Properties prop = Utils.leerPropiedades("urls.properties");
    int userID;
    User user;

    @When("se llama a \\/users, se obtiene userId de registro {string} validando name y username")
    public void se_llama_a_users_se_obtiene_user_id_de_registro_validando_name_y_username(String numero) {
        try {
            List<User> users = ServiceMethods.listarUsuarios("JSON", prop, Utils.methods.GET);
            if (Integer.parseInt(numero) <= users.size()) {
                for (User user : users) {
                    if (user.getName().equals(null) || user.getUsername().equals(null)) {
                        Assert.fail("Name o username de registro " + numero + ", asociado a userID: " + userID + " esta vacio.");
                    }
                }
                user = users.get(Integer.parseInt(numero) - 1);
                userID = user.getId();
            } else {
                Assert.fail("Numero de registro inexistente en listado de usuarios");
            }
        } catch (Exception ex) {
            Assert.fail("Error al realizar llamada a servicio users");
        }
    }

    @Then("se imprimen todos los datos asociados a userId")
    public void se_imprimen_todos_los_datos_asociados_a_user_id() {
        try {
            System.out.println("RESULTADO FEATUREC: " + user.toString());
        } catch (Exception ex) {
            Assert.fail("Error al imprimir datos de usuario");
        }
    }


}
