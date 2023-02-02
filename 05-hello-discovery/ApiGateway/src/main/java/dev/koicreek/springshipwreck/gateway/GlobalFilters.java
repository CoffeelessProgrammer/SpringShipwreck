package dev.koicreek.springshipwreck.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class GlobalFilters {

    // @Order annotation values below are redundant

    @Order(1)
    @Bean
    GlobalFilter secondFilterSet() {
        return (exchange, chain) -> {
            log.info("Second global pre-filter activated...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Second global post-filter activated...");
            }));
        };
    }


    @Order(2)
    @Bean
    GlobalFilter thirdFilterSet() {
        return (exchange, chain) -> {
            log.info("Third global pre-filter activated...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("First global post-filter activated...");
            }));
        };
    }
}
