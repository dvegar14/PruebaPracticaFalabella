package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utils {
    private static RequestSpecification request;
    private static Response response;

    public static Response getResponse(String contentType, String endpoint, methods metodo) {
        if (contentType.equalsIgnoreCase("JSON")) {
            request = given().header("Content-Type", "application/json");
        } else if (contentType.equalsIgnoreCase("XML")) {
            request = given().header("Content-Type", "application/xml");
        }

        if (metodo.equals(methods.GET)) {
            response = request.when().get(endpoint);
        } else if (metodo.equals(methods.POST)) {
            response = request.when().post(endpoint);
        }

        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }

    public static Properties leerPropiedades(String propertiesName) {
        Properties properties = null;
        try {
            File propsFile = new File("src/test/resources/properties/" + propertiesName);
            FileInputStream inputStream = new FileInputStream(propsFile);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public enum methods {
        GET,
        POST,
        PUT,
        DELETE
    }

}
