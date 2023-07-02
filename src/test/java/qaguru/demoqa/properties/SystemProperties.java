package qaguru.demoqa.properties;

import org.junit.jupiter.api.Test;

public class SystemProperties {

    @Test
    void setProperties() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser", "safari");
        System.out.println(browser);

        System.setProperty("version", "114");
        String version = System.getProperty("version", "101");
        System.out.println(version);

        System.setProperty("windowSize", "1920x1080");
        String windowSize = System.getProperty("windowSize", "1366x768");
        System.out.println(windowSize);
    }
}