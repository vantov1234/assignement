package api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static api.Routing.*;
import static io.restassured.RestAssured.given;
import static utils.ReadPropertiesFile.OWNER;
import static utils.ReadPropertiesFile.TOKEN;

public class RepoRequests {

    public Response sendPostRequest(String token, String scope, String accept, String endPoint, String postBody) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("scope", scope)
                .accept(accept)
                .body(postBody)
                .post(endPoint)
                .then()
                .extract()
                .response();
    }

    public void sendDeleteRequest(String repoName) {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("scope", "delete_repo")
                .accept("application/vnd.github+json")
                .when()
                .delete(REPOS + OWNER + repoName)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
