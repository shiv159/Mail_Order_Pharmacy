package com.mailorderpharmacy.drugservice.config;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2);
    }
    
}