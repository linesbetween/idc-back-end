package org.utpe.freeopenuniversity.intelligentdocumentclassifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.ui.UI;

@SpringBootApplication
public class IntelligentdocumentclassifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentdocumentclassifierApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200",
				"https://idc-front-end.herokuapp.com/");
            }
        };
    }

}
