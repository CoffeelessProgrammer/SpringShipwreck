package dev.koicreek.springshipwreck.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyPostFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("My global post-filter activated...");
        }));
    }

    /* Implement Ordered from org.springframework.core.Ordered
     * for custom ordering.
     * Post-filters: higher order value = executed before others
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
