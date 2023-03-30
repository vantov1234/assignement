package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseValidator {

    public String getResponseValue(Response response, String responseParameter) {
        JsonPath path = response.jsonPath();
        return path.get(responseParameter);
    }

    public int getResponseStatusCode(Response response) {
        return response.getStatusCode();
    }
}
