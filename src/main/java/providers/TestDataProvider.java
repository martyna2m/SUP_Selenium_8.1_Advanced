package providers;

import configuration.PropertiesFromYaml;

import java.util.Map;


public class TestDataProvider {

    private Map<String, Object> testData;

    public TestDataProvider() {
        setTestDataFromYaml(PropertiesFromYaml.config);
    }

    private void setTestDataFromYaml(Map<String, Object> data) {
        this.testData = (Map<String, Object>) data.get("testData");
    }


    public String getTestData(String key) throws NullPointerException {
        try {
            return testData.get(key).toString();
        } catch (Exception e) {
            System.out.println("Property for keyValue: " + key + " is null. Check yaml file.");
        }
        return null;
    }

}
