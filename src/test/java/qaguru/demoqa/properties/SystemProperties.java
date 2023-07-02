package qaguru.demoqa.properties;

public class SystemProperties {

    void setProperties() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser", "safari");

        System.setProperty("version", "114");
        String version = System.getProperty("version", "101");

        System.setProperty("windowSize", "1920x1080");
        String windowSize = System.getProperty("windowSize", "1366x768");

        System.setProperty("baseUrl", "https://demoqa.com");
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");

        System.setProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        String url = System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
    }
}