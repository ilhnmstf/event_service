package post_service;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EventServiceApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EventServiceApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}