package pl.sebaszczen.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors. basePackage("pl.sebaszczen.controller.restController")) // fuj - może skanować wszystkie paczki
//                .paths((PathSelectors.ant("/rest/**")))
//                .paths(regex("/rest.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Api Documentation",
                "Api documentation",
                "0.1",
                "sebaszczen.pl/terms",
                new Contact("Sebastian Szczebiot", "www.skimpy.pl", "sebastianszczebiot@gmail.com.pl"),
                "Licence, privacy policy",
                "skimpy.com/license"
        );
        return apiInfo;
    }
}