//package com.self.library.config;
//
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("library")
//                .pathsToMatch("/api/**")
//                .build();
//    }
//
//    @Bean
//    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
//        return new io.swagger.v3.oas.models.OpenAPI()
//                .info(new Info().title("Library System API")
//                        .description("API documentation for Library System")
//                        .version("1.0")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
//}
