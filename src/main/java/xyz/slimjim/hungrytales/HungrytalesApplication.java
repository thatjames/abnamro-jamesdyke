package xyz.slimjim.hungrytales;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class HungrytalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HungrytalesApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(HungrytalesApplication.class);

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String corsHostsConfig = System.getProperty("cors.hosts") == null ? "*" : System.getProperty("cors.hosts");
                log.debug(String.format("set cors.hosts %s", corsHostsConfig));
                registry.addMapping("/**").allowedOrigins(corsHostsConfig.split(","));
            }
        };
    }
}
