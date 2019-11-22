package Interfaces;

import org.openqa.selenium.Cookie;

import java.util.List;

public interface RestClient {

    String get(String url);
    String getWithToken(String url, String token);

    List<Cookie> getCookies(String url, String token);

    String post(String url, String json);
    String postWithToken(String url, String json, String token);

    String postWithoutBaseUrl(String url, String json);

    String put(String url, String json);
    String putWithToken(String url, String json, String token);

    String delete(String url);
    String deleteWithToken(String url, String token);
    String deleteWithoutBaseUrl(String url, String token);

}
