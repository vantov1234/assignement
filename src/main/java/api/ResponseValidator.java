package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ResponseValidator {

    public ResponseValidator verifyStatusCodeIsSame(int statusCode, Response response) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code:");
        return this;
    }

    public ResponseValidator verifyResponseValueIsSame(String key, String value, Response response) {
        String responseValue = getResponseValue(key, response);
        Assertions.assertEquals(value, responseValue, key);
        return this;
    }

    public String getResponseValue(String responseParameter, Response response) {
        JsonPath path = response.jsonPath();
        return path.get(responseParameter);
    }

    public int getResponseStatusCode(Response response) {
        return response.getStatusCode();
    }
}
