package service.thirdPartyIntergration;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import service.Config;

import java.io.IOException;
import java.util.Date;

public class Slack {

    private static String slackWebhookUrl = Config.getSlackWebHook();
    private final static Logger logger = Logger.getLogger(Slack.class);


    private static void sendMessage(String message) {
        HttpPost httpPost = new HttpPost(slackWebhookUrl);

        try (CloseableHttpClient client = HttpClients.createDefault();) {
            String json = "{\"text\":\"" + message + "\"}";

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            client.execute(httpPost);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }e.printStackTrace();
    }

    public static void reportTestStart(String testName) {
        sendMessage(new Date() + ": Starting test *" + testName + "*");
    }

    public static void reportTestFinish(ITestResult result) {
        sendMessage(new Date() + ": Test finished *" + result.getName() + "*. result: " + (result.isSuccess() ? "success" : "failure"));
    }
}
