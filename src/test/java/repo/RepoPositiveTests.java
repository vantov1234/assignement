package repo;

import api.RepoRequests;
import api.ResponseValidator;
import config.BaseRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static api.Routing.USER_REPOS;
import static utils.ReadPropertiesFile.TOKEN;
import static utils.enums.AcceptHeaders.GITHUB;
import static utils.enums.Scopes.PUBLIC_REPO;

@ExtendWith({BaseRequest.class})
public class RepoPositiveTests {

    private RepoRequests repoRequests;
    private ResponseValidator responseValidator;

    String propertyName = "name";
    String propertyDescription = "description";
    String repoName = "Api-repo";
    String repoDescription = "Api created test repo";

    @BeforeEach
    void setUp() {
        // New instance for every test (prevents parallel execution errors)
        repoRequests = new RepoRequests();
        responseValidator = new ResponseValidator();
    }

    @Test
    void createRepoOnAuthenticatedUser() {
        // Create the POST body object with required fields
        JSONObject testRequestBody = new JSONObject();
        testRequestBody.put(propertyName, repoName);
        testRequestBody.put(propertyDescription, repoDescription);

        // Send POST request to create the repo
        Response createRepoRequest = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, GITHUB, USER_REPOS, testRequestBody.toString());

        // Verify response property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_CREATED, createRepoRequest)
                .verifyResponseValueIsSame(propertyName, repoName, createRepoRequest)
                .verifyResponseValueIsSame(propertyDescription, repoDescription, createRepoRequest);

        // Delete the test repo
        repoRequests.sendDeleteRequest(repoName);
    }
}
