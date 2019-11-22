package service.api;

import Interfaces.RestClient;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.Cookie;
import service.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpClient implements RestClient {

    private org.apache.http.client.HttpClient getNewClient() {
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        if (Config.getUseProxy()) {
            HttpHost proxy = new HttpHost("127.0.0.1", 9999);
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        return client;
    }

    @Override
    public String get(String url) {
        return getWithToken(url, null);
    }

    @Override
    public String getWithToken(String url, String token) {
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpGet request = new HttpGet(Config.getApiUrl() + url);
            if (token != null) {
                request.setHeader("Authorization", "Bearer " + token);
            }
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder resp = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                resp.append(line).append("\n");
            }
            return resp.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Cookie> getCookies(String url, String token) {
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", "Bearer " + token);
            HttpResponse response = client.execute(request);
            List<Cookie> seleniumCookies = new ArrayList<>();
            for (org.apache.http.cookie.Cookie c : ((DefaultHttpClient) client).getCookieStore().getCookies()) {
                Cookie cookie = new Cookie.Builder(c.getName(), c.getValue())
                        .domain(c.getDomain())
                        .expiresOn(c.getExpiryDate())
                        .isHttpOnly(true)
                        .isSecure(c.isSecure())
                        .path(c.getPath())
                        .build();
                seleniumCookies.add(cookie);
            }
            return seleniumCookies;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String post(String url, String json) {
        return postWithToken(url, json, null);
    }

    @Override
    public String postWithToken(String url, String json, String token) {
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpPost post = new HttpPost(Config.getApiUrl() + url);
            if (token != null) {
                post.setHeader("Authorization", "Bearer " + token);
            }
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(requestEntity);
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder resp = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                resp.append(line).append("\n");
            }
            return resp.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String postWithoutBaseUrl(String url, String json) {
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Authorization", "Basic dWk6dWltYW4=");
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(requestEntity);
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder resp = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                resp.append(line).append("\n");
            }
            return resp.toString();
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
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpPut put = new HttpPut(Config.getApiUrl() + url);
            if (token != null) {
                put.setHeader("Authorization", "Bearer " + token);
            }
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            put.setEntity(requestEntity);
            HttpResponse response = client.execute(put);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder resp = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                resp.append(line).append("\n");
            }
            return resp.toString();
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
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpDelete delete = new HttpDelete(Config.getApiUrl() + url);
            if (token != null) {
                delete.addHeader("Authorization", "Bearer " + token);
            }
            HttpResponse response = client.execute(delete);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder resp = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                resp.append(line).append("\n");
            }
            return resp.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteWithoutBaseUrl(String url, String token) {
        try {
            org.apache.http.client.HttpClient client = getNewClient();
            HttpDelete delete = new HttpDelete(url);
            if (token != null) {
                delete.addHeader("Authorization", "Bearer " + token);
            }
            HttpResponse response = client.execute(delete);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder resp = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                resp.append(line).append("\n");
            }
            return resp.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
