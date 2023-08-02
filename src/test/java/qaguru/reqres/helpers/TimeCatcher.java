package qaguru.reqres.helpers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCatcher {

    public static String getCurrentTime() {
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateTime.format(formatter);
    }
}
