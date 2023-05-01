package dev.koicreek.springshipwreck.pokemon;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if(methodKey.startsWith("TrainerPartyServiceClient")) {
            switch(response.status()) {
                case 400: {
                    if (methodKey.contains("#addToTrainerParty")) {
                        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Party is full");
                    }
                    break;
                }
                default:
                    return errorDecoder.decode(methodKey, response);
            }
        }

        return errorDecoder.decode(methodKey, response);
    }

}
