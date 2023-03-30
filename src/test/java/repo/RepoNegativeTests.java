package repo;

import api.RepoRequests;
import api.ResponseValidator;
import config.BaseRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static api.Routing.REPOS;
import static utils.ReadPropertiesFile.TOKEN;
import static utils.enums.StatusCodes.CREATED;

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
        JSONObject object = new JSONObject();
        object.put(propertyName, repoName);
        object.put(propertyDescription, repoDescription);

        // Send POST request to create the repo
        Response createRepo = repoRequests.sendPostRequest(TOKEN, "public_repo", "application/vnd.github+json", REPOS, object.toString());

        // Extract response property values
        String responseMessage = responseValidator.getResponseValue(createRepo, propertyMessage);
        String responseDocUrl = responseValidator.getResponseValue(createRepo, propertyDocUrl);
        int responseStatusCode = responseValidator.getResponseStatusCode(createRepo);

        // Verify response property values
        Assertions.assertEquals(CREATED.getStatusCode(), responseStatusCode, "Status code:");
        Assertions.assertEquals(object.get(propertyMessage), responseMessage, propertyMessage);
        Assertions.assertEquals(object.get(propertyDocUrl), responseDocUrl, propertyDocUrl);
    }
}
