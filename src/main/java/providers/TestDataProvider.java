package providers;

import configuration.PropertiesFromYaml;
import lombok.Getter;

import java.util.Map;


public class TestDataProvider {

    private Map<String, Object> testData;

    public TestDataProvider() {
        setTestDataFromYaml(PropertiesFromYaml.config);
    }

    private void setTestDataFromYaml(Map<String, Object> data) {
        this.testData = (Map<String, Object>) data.get("testData");
    }


    public String getTestData(String key){
        return testData.get(key).toString();
    }

}
