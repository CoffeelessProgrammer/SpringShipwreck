package dev.koicreek.springshipwreck.hellospring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j  // Automatically generates 'Logger log = ...' field
public class HelloLoggingAPI {

    Logger logger = LoggerFactory.getLogger(HelloLoggingAPI.class);

    @GetMapping
    ResponseEntity<String> loggingTest() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return new ResponseEntity<>("Tao Te Ching", HttpStatus.OK);
    }
}
