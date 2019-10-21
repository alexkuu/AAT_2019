package apiTests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.api.RestClient;

import java.io.IOException;

public class SmokeApiTest {

    @Test
    public static void SmokeApi_1() throws IOException {
        String unAuthorizedResponse = RestClient.get("public-api/users");
        JSONObject obj = new JSONObject(unAuthorizedResponse);
        Assert.assertEquals(obj.get("result").toString(), "{\"code\":0,\"name\":\"Unauthorized\",\"message\":\"Your request was made with invalid credentials.\",\"status\":401}");
    }

    @Test(enabled = false)
    public static void SmokeApi_2_FAIL() throws IOException {
        String unAuthorizedResponse = RestClient.get("public-api/users");
        JSONObject obj = new JSONObject(unAuthorizedResponse);
        Assert.assertEquals(obj.get("result").toString(), "{\"code\":1,\"name\":\"Unauthorized\",\"message\":\"Your request was made with invalid credentials.\",\"status\":401}");
    }

}
