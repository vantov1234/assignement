package config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static utils.ReadPropertiesFile.BASE_URI;

public class BaseRequest implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        RestAssured.baseURI = BASE_URI;
    }
}
