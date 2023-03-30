package repo;

import api.RepoRequests;
import api.ResponseValidator;
import config.BaseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BaseRequest.class})
public class RepoNegativeTests {

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
}
