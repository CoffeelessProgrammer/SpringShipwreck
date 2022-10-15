package dev.koicreek.springshipwreck;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@Data
public class ErrorPayloadCM {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    static {
         sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
    }

    @Getter
    private String timestamp;
    @Getter
    private int statusCode;
    @Getter
    private String error;
    @Getter @Setter
    private String message;
    @Getter
    private String path;

    public ErrorPayloadCM(HttpStatus httpStatus, String path) {
        this.timestamp = sdf.format(new Date());
        this.statusCode = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.path = path;
    }

}
