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

import static api.Routing.REPOS;
import static api.Routing.USER;
import static utils.Constants.*;
import static utils.ReadPropertiesFile.TOKEN;
import static utils.enums.AcceptHeaders.GITHUB;
import static utils.enums.AcceptHeaders.TEXT;
import static utils.enums.RepoErrorMessages.*;
import static utils.enums.Scopes.PUBLIC_REPO;

@ExtendWith({BaseRequest.class})
public class RepoNegativeTests {

    private RepoRequests repoRequests;
    private ResponseValidator responseValidator;

    String propertyMessage = "message";
    String propertyDocUrl = "documentation_url";

    JSONObject testRequestBody;

    @BeforeEach
    void setUp() {
        repoRequests = new RepoRequests();
        responseValidator = new ResponseValidator();
        testRequestBody = new JSONObject();
    }

    @Test
    void createRepoUnauthorized() {
        // Create the POST body object with required fields
        testRequestBody.put(PROPERTY_NAME, REPO_NAME);
        testRequestBody.put(PROPERTY_DESCRIPTION, REPO_DESCRIPTION);

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest("", PUBLIC_REPO, GITHUB, REPOS, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_UNAUTHORIZED, response)
                .verifyResponseValueIsSame(propertyMessage, BAD_CREDENTIALS.getMessage(), response)
                .verifyResponseValueIsSame(propertyDocUrl, BAD_CREDENTIALS.getDocUrl(), response);
    }

    @Test
    void createRepoWithoutName() {
        // Create the POST body object with required fields
        testRequestBody.put(PROPERTY_NAME, "");
        testRequestBody.put(PROPERTY_DESCRIPTION, REPO_DESCRIPTION);

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, GITHUB, REPOS, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_NOT_FOUND, response)
                .verifyResponseValueIsSame(propertyMessage, NOT_FOUND.getMessage(), response)
                .verifyResponseValueIsSame(propertyDocUrl, NOT_FOUND.getDocUrl(), response);
    }

    @Test
    void createRepoWithoutDescription() {
        // Create the POST body object with required fields
        testRequestBody.put(PROPERTY_NAME, REPO_NAME);
        testRequestBody.put(PROPERTY_DESCRIPTION, "");

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, GITHUB, REPOS, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_NOT_FOUND, response)
                .verifyResponseValueIsSame(propertyMessage, NOT_FOUND.getMessage(), response)
                .verifyResponseValueIsSame(propertyDocUrl, NOT_FOUND.getDocUrl(), response);
    }

    @Test
    void createRepoWithoutNameAndDescription() {
        // Create the POST body object with required fields
        testRequestBody.put(PROPERTY_NAME, "");
        testRequestBody.put(PROPERTY_DESCRIPTION, "");

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, GITHUB, REPOS, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_NOT_FOUND, response)
                .verifyResponseValueIsSame(propertyMessage, NOT_FOUND.getMessage(), response)
                .verifyResponseValueIsSame(propertyDocUrl, NOT_FOUND.getDocUrl(), response);
    }

    @Test
    void createRepoOnWrongEndPoint() {
        // Create the POST body object with required fields
        testRequestBody.put(PROPERTY_NAME, REPO_NAME);
        testRequestBody.put(PROPERTY_DESCRIPTION, REPO_DESCRIPTION);

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, GITHUB, USER, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_NOT_FOUND, response)
                .verifyResponseValueIsSame(propertyMessage, NOT_FOUND.getMessage(), response)
                .verifyResponseValueIsDifferent(propertyDocUrl, NOT_FOUND.getDocUrl(), response);
    }

    @Test
    void createRepoWithWrongAcceptHeader() {
        // Create the POST body object with required fields
        testRequestBody.put(PROPERTY_NAME, REPO_NAME);
        testRequestBody.put(PROPERTY_DESCRIPTION, REPO_DESCRIPTION);

        // Send POST request to create the repo
        Response response = repoRequests.sendPostRequest(TOKEN, PUBLIC_REPO, TEXT, REPOS, testRequestBody.toString());

        // Verify response status code and property values
        responseValidator
                .verifyStatusCodeIsSame(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, response)
                .verifyResponseValueIsSame(propertyMessage, WRONG_HEADER.getMessage(), response)
                .verifyResponseValueIsSame(propertyDocUrl, WRONG_HEADER.getDocUrl(), response);
    }
}
