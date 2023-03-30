package repo;

import api.RepoRequests;
import api.ResponseValidator;
import config.BaseRequest;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static api.Routing.USER_REPOS;
import static utils.ReadPropertiesFile.TOKEN;
import static utils.enums.StatusCodes.CREATED;

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
        JSONObject object = new JSONObject();
        object.put(propertyName, repoName);
        object.put(propertyDescription, repoDescription);

        System.out.println(object);

        // Send POST request to create the repo
        Response createRepo = repoRequests.sendPostRequest(TOKEN, "public_repo", "application/vnd.github+json", USER_REPOS, object.toString());

        // Extract response property values
        String responseRepoName = responseValidator.getResponseValue(createRepo, propertyName);
        String responseRepoDescription = responseValidator.getResponseValue(createRepo, propertyDescription);
        int responseStatusCode = responseValidator.getResponseStatusCode(createRepo);

        // Verify response property values
        Assertions.assertEquals(CREATED.getStatusCode(), responseStatusCode, "Status code:");
        Assertions.assertEquals(object.get(propertyName), responseRepoName, "Repo name:");
        Assertions.assertEquals(object.get(propertyDescription), responseRepoDescription, "Repo description: ");

        // Delete the test repo
        repoRequests.sendDeleteRequest(repoName);
    }
}
