package dev.koicreek.springshipwreck.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@Slf4j
public class MyPreFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final String requestPath = exchange.getRequest().getPath().toString();
        log.info("My global pre-filter activated..." + "\n\tRequest path = " + requestPath);

        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> headerNames = headers.keySet();

        StringBuilder sb = new StringBuilder("Request Headers:");

        headerNames.forEach((headerName) -> {
            String headerValue = headers.getFirst(headerName);
            sb.append(String.format("\n\t  - %s: %s", headerName, headerValue));
        });

        log.info(sb.toString());

        return chain.filter(exchange);
    }

    /* Implement Ordered from org.springframework.core.Ordered
     * for custom ordering.
     * Pre-filters: Lower order value = executed before others
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
