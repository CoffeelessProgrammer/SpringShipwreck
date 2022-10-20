package account.contracts.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ErrorPayloadCM {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Getter
    private final String timestamp;
    @Getter
    private final int status;
    @Getter
    private final String error;
    @Getter @Setter
    private String message;
    @Getter
    private final String path;    // ServletPath

    public ErrorPayloadCM(HttpStatus httpStatus, String path) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = sdf.format(timestamp);
        this.path = path;
        this.message = null;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
