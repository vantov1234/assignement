package api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import utils.enums.AcceptHeaders;
import utils.enums.Scopes;

import static api.Routing.*;
import static io.restassured.RestAssured.given;
import static utils.ReadPropertiesFile.OWNER;
import static utils.ReadPropertiesFile.TOKEN;
import static utils.enums.AcceptHeaders.GITHUB;
import static utils.enums.Scopes.DELETE_REPO;

public class RepoRequests {

    public Response sendPostRequest(String token, Scopes scope, AcceptHeaders acceptHeader, String endPoint, String postBody) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("scope", scope.getScope())
                .accept(acceptHeader.getAcceptHeader())
                .body(postBody)
                .post(endPoint)
                .then()
                .extract()
                .response();
    }

    public void sendDeleteRequest(String repoName) {
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .header("scope", DELETE_REPO.getScope())
                .accept(GITHUB.getAcceptHeader())
                .when()
                .delete(REPOS + OWNER + repoName)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
