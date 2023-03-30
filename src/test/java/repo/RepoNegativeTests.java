package repo;

import api.RepoRequests;
import api.ResponseValidator;
import config.BaseRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.enums.AcceptHeaders;

import static api.Routing.REPOS;
import static utils.enums.AcceptHeaders.GITHUB;
import static utils.enums.Scopes.PUBLIC_REPO;

@ExtendWith({BaseRequest.class})
public class RepoNegativeTests {

    private RepoRequests repoRequests;
    private ResponseValidator responseValidator;

    String propertyName = "name";
    String propertyDescription = "description";
    String repoName = "Api-repo";
    String repoDescription = "Api created test repo";
    String propertyMessage = "message";
    String propertyDocUrl = "documentation_url";

    @BeforeEach
    void setUp() {
        // New instance for every test (prevents parallel execution errors)
        repoRequests = new RepoRequests();
        responseValidator = new ResponseValidator();
    }

    @Test
    void createRepoOnAuthenticatedUserUnauthorized() {
        // Create the POST body object with required fields
        JSONObject testRequestBody = new JSONObject();
        testRequestBody.put(propertyName, repoName);
        testRequestBody.put(propertyDescription, repoDescription);

        // Send POST request to create the repo
        Response createRepoRequest = repoRequests.sendPostRequest("", PUBLIC_REPO, GITHUB, REPOS, testRequestBody.toString());

        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_CREATED, createRepoRequest)
                .verifyResponseValueIsSame(propertyName, repoName, createRepoRequest)
                .verifyResponseValueIsSame(propertyDescription, repoDescription, createRepoRequest);
    }
}
