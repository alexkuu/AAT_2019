package service.thirdPartyIntergration;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.ITestResult;
import service.Config;

import java.io.IOException;
import java.util.Date;

public class Slack {

    private static String slackWebhookUrl = Config.getSlackWebHook();


    private static void sendMessage(String message) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(slackWebhookUrl);

        try {
            String json = "{\"text\":\"" + message + "\"}";

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reportTestStart(String testName) {
        sendMessage(new Date() + ": Starting test *" + testName + "*");
    }

    public static void reportTestFinish(ITestResult result) {
        sendMessage(new Date() + ": Test finished *" + result.getName() + "*. result: " + (result.isSuccess() ? "success" : "failure"));
    }
}
