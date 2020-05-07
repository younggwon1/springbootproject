package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private static final Contact DEFAULT_CONTACT = new Contact("Kenneth Lee",
            "http://www.joneculsting.co.kr", "edowon@joneconsluting.co.kr");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title",
            "Awesome API Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES
            = new HashSet<>(Arrays.asList("application/json", "application/xml"));


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(DEFAULT_API_INFO)
                    .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                    .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    // Swagger와 HATEOAS를 동시에 사용할 수 있도록 해주는 코드(둘은 사용할 수 있는 버전이 다르기 때문에 맞춰주는 작업이 필요)
    @Bean
    public LinkDiscoverers discoverer() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

}
