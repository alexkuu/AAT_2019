package service.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import service.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RestClient {

    public static String get(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Config.getApiUrl() + url);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        StringBuilder resp = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            resp.append(line).append("\n");
        }
        return resp.toString();
    }

    public static String post(String url, ArrayList args) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(Config.getApiUrl() + url);
        StringEntity input = new StringEntity("product");
        post.setEntity(new UrlEncodedFormEntity(args));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        StringBuilder resp = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            resp.append(line).append("\n");
        }
        return resp.toString();
    }

}
