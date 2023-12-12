package configuration;

import java.util.Map;

public class PropertiesFromYaml {
    private static YamlReader yamlReader = new YamlReader();
    public static Map<String, Object> config = yamlReader.readYamlFile("src/main/resources/config.yaml");

    public PropertiesFromYaml() {

    }

}
