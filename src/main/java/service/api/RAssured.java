package service.api;

import Interfaces.RestClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import service.Config;

import java.util.List;

import static io.restassured.specification.ProxySpecification.host;

public class RAssured implements RestClient {

    @Override
    public String get(String url) {
        return getWithToken(url, null);
    }

    @Override
    public String getWithToken(String url, String token) {
        RestAssured.baseURI = Config.getApiUrl();
        if(Config.getUseProxy()) {
            RestAssured.proxy = host("127.0.0.1").withPort(9999);
        }
        try {
            Response response = RestAssured.given()
                    .headers("Authorization", "Bearer " + token)
                    .get(url);
            return response.asString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Cookie> getCookies(String url, String token) {
        return null;
    }

    @Override
    public String post(String url, String json) {
        return postWithToken(url, json, null);
    }

    @Override
    public String postWithToken(String url, String json, String token) {
        RestAssured.baseURI = Config.getApiUrl();
        if(Config.getUseProxy()) {
            RestAssured.proxy = host("127.0.0.1").withPort(9999);
        }
        try {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + token)
                    .body(json)
                    .post(url);
            return response.asString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String postWithoutBaseUrl(String url, String json) {
        RestAssured.baseURI = "";
        if(Config.getUseProxy()) {
            RestAssured.proxy = host("127.0.0.1").withPort(9999);
        }
        try {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Basic dWk6dWltYW4=")
                    .body(json)
                    .post(url);
            return response.asString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String put(String url, String json) {
        return putWithToken(url, json, null);
    }

    @Override
    public String putWithToken(String url, String json, String token) {
        RestAssured.baseURI = Config.getApiUrl();
        if(Config.getUseProxy()) {
            RestAssured.proxy = host("127.0.0.1").withPort(9999);
        }
        try {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + token)
                    .body(json)
                    .put(url);
            return response.asString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(String url) {
        return deleteWithToken(url, null);
    }

    @Override
    public String deleteWithToken(String url, String token) {
        RestAssured.baseURI = Config.getApiUrl();
        if(Config.getUseProxy()) {
            RestAssured.proxy = host("127.0.0.1").withPort(9999);
        }
        try {
            Response response = RestAssured.given()
                    .headers("Authorization", "Bearer " + token)
                    .delete(url);
            return response.asString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteWithoutBaseUrl(String url, String token) {
        RestAssured.baseURI = "";
        if(Config.getUseProxy()) {
            RestAssured.proxy = host("127.0.0.1").withPort(9999);
        }
        try {
            Response response = RestAssured.given()
                    .headers("Authorization", "Bearer " + token)
                    .delete(url);
            return response.asString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
