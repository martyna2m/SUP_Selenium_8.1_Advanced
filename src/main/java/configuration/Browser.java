package configuration;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    IE("ie");

    private String name;

    Browser(String name) {
        this.name = name;
    }

    public static Browser getBrowserByName(String name) {
        for (Browser browser : values()) {
            if (browser.name.equalsIgnoreCase(name)) {
                return browser;
            }
        }
        throw new IllegalArgumentException("No browser with name: " + name + " found.");
    }
}
