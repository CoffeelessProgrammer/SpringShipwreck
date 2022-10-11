package account.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("timestamp", sdf.format(timestamp));
        payload.put("status", 403);
        payload.put("error", "Forbidden");
        payload.put("message", "Access Denied!");
        payload.put("path", request.getServletPath());

        String json = new ObjectMapper().writeValueAsString(payload);

        response.getWriter().write(json);
    }
}
