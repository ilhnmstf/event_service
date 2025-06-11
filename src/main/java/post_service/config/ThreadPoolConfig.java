package post_service.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import post_service.properties.ThreadPoolProperties;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ThreadPoolConfig {
    private final ThreadPoolProperties threadPoolProperties;

    @Bean
    public ExecutorService saveEventPool() {
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolProperties.getEventSave());
        log.info("Init executor service {} for save event", executor);
        return executor;
    }
}
