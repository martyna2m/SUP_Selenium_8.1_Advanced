package providers;

import configuration.PropertiesFromYaml;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class UrlProvider {

    private Map<String, Object> urls;

    public UrlProvider() {
        setUrlsFromYaml(PropertiesFromYaml.config);
    }

    private void setUrlsFromYaml(Map<String, Object> data) {
        this.urls = (Map<String, Object>) data.get("url");
    }



    public String getUrl(String urlKey) {
        String homePageUrl = urls.get("homePage").toString();
        if (!urlKey.equals("homePage")) {
            String urlSuffix = urls.get(urlKey).toString();
            return homePageUrl + urlSuffix;
        } else return homePageUrl;

    }
}
