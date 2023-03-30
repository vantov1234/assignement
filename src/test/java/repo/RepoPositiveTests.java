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
import static utils.Constants.*;
import static utils.ReadPropertiesFile.TOKEN;
import static utils.enums.AcceptHeaders.GITHUB;
import static utils.enums.Scopes.PUBLIC_REPO;

@ExtendWith({BaseRequest.class})
public class RepoPositiveTests {

    private RepoRequests repoRequests;
    private ResponseValidator responseValidator;

    @BeforeEach
    void setUp() {
        repoRequests = new RepoRequests();
        responseValidator = new ResponseValidator();
    }

    @Test
    void createRepoOnAuthenticatedUser() {
        // Create the POST body object with required fields
        JSONObject testRequestBody = new JSONObject();
        testRequestBody.put(PROPERTY_NAME, REPO_NAME);
        testRequestBody.put(PROPERTY_DESCRIPTION, REPO_DESCRIPTION);

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, GITHUB, USER_REPOS, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_CREATED, response)
                .verifyResponseValueIsSame(PROPERTY_NAME, REPO_NAME, response)
                .verifyResponseValueIsSame(PROPERTY_DESCRIPTION, REPO_DESCRIPTION, response);

        // Delete the test repo
        repoRequests.sendDeleteRequest(REPO_NAME);
    }
}
