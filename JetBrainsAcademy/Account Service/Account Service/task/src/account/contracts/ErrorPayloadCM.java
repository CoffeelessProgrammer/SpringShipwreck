package account.contracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

public class ErrorPayloadCM {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    static {
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
    }

    @Getter
    private String timestamp;
    @Getter
    private int status;
    @Getter
    private String error;
    @Getter @Setter
    private String message;
    @Getter
    private String path;    // ServletPath

    public ErrorPayloadCM(int status, String path) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = sdf.format(timestamp);
        this.path = path;
        this.message = null;
        this.status = status;
        if(HttpStatus.resolve(status) == null)
            throw new IllegalArgumentException("Invalid http status: " + status);
        this.error = HttpStatus.resolve(status).getReasonPhrase();
    }

    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
