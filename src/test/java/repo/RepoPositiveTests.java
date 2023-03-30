package repo;

import api.RepoRequests;
import api.ResponseValidator;
import com.google.gson.JsonObject;
import config.BaseRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
        JsonObject object = new JsonObject();
        object.addProperty(propertyName, repoName);
        object.addProperty(propertyDescription, repoDescription);

        // Send POST request to create the repo
        Response createRepo = repoRequests.sendPostRequest(object);

        // Extract response property values
        String responseRepoName = responseValidator.getResponseValue(createRepo, propertyName);
        String responseRepoDescription = responseValidator.getResponseValue(createRepo, propertyDescription);
        int responseStatusCode = responseValidator.getResponseStatusCode(createRepo);

        // Verify response property values
        Assertions.assertEquals(CREATED.getStatusCode(), responseStatusCode, "Status code:");
        Assertions.assertEquals(object.get(propertyName).getAsString(), responseRepoName, "Repo name:");
        Assertions.assertEquals(object.get(propertyDescription).getAsString(), responseRepoDescription, "Repo description: ");

        // Delete the test repo
        repoRequests.sendDeleteRequest(repoName);
    }
}
