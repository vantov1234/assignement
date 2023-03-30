package api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static api.Routing.*;
import static io.restassured.RestAssured.given;
import static utils.ReadPropertiesFile.OWNER;
import static utils.ReadPropertiesFile.TOKEN;

public class RepoRequests {

    public Response sendPostRequest(Object postBody) {
        return given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("scope", "public_repo")
                .accept("application/vnd.github+json")
                .body(postBody)
                .post(USER_REPOS)
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
