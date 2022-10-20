package account.security;

import account.contracts.response.ErrorPayloadCM;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);

//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//        Map<String, Object> payload = new LinkedHashMap<>();
//        payload.put("timestamp", sdf.format(timestamp));
//        payload.put("status", 403);
//        payload.put("error", "Forbidden");
//        payload.put("message", "Access Denied!");
//        payload.put("path", request.getServletPath());
//
//        String json = new ObjectMapper().writeValueAsString(payload);

        ErrorPayloadCM payload = new ErrorPayloadCM(HttpStatus.FORBIDDEN, request.getServletPath());
        payload.setMessage("Access denied!");

        response.getWriter().write(payload.toJson());
    }
}
