package providers;

import configuration.PropertiesFromYaml;
import lombok.Getter;

import java.util.Map;

@Getter
public class UrlProvider {

    private Map<String, Object> urls;

    public UrlProvider() {
        setUrlsFromYaml(PropertiesFromYaml.config);
    }


    private void setUrlsFromYaml(Map<String, Object> data) {
        this.urls = (Map<String, Object>) data.get("url");
    }
}
