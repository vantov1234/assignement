package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {

    public static final String BASE_URI = getConfigurationProperty("base_uri");
    public static final String TOKEN = getConfigurationProperty("token");
    public static final String OWNER = getConfigurationProperty("owner");

    private static String getConfigurationProperty(String propertyName) {
        String propertyFile = Constants.envFileName;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(propertyFile)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(propertyName);
    }
}
