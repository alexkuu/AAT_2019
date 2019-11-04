package apiTests;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import service.api.RestClient;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SmokeApiTest {

    @Test
    public void SmokeApi_1() throws IOException {
        String unAuthorizedResponse = RestClient.get("public-api/users");
        JSONObject obj = new JSONObject(unAuthorizedResponse);
        assertEquals(obj.get("result").toString(), "{\"code\":0,\"name\":\"Unauthorized\",\"message\":\"Your request was made with invalid credentials.\",\"status\":401}");
    }

//    @Ignore
    @Test
    public void SmokeApi_2_FAIL() throws IOException {
        String unAuthorizedResponse = RestClient.get("public-api/users");
        JSONObject obj = new JSONObject(unAuthorizedResponse);
        assertEquals(obj.get("result").toString(), "{\"code\":1,\"name\":\"Unauthorized\",\"message\":\"Your request was made with invalid credentials.\",\"status\":401}");
    }

}
