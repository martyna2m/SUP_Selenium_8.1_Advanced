package configuration;

import java.util.Map;

public class PropertiesFromYaml {
    private static YamlReader yamlReader = new YamlReader();
    public static Map<String, Object> config = yamlReader.readYamlFile("src/main/resources/config.yaml");

    public PropertiesFromYaml() {
        // setUrlProperties(config);
    }


//    public static void setUrlProperties(Map<String, Object> data) {
//        Map<String, Object> urls = (Map<String, Object>) data.get("url");
//        urls.forEach((key, value) -> {
//            System.setProperty(key, value.toString());
//        });
}






