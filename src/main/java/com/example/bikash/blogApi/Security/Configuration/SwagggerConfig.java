package com.example.bikash.blogApi.Security.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwagggerConfig {

    String scheme_name = "bearerScheme";


    @Bean
    public OpenAPI openAPI()
    {

         return  new OpenAPI()

                 .addSecurityItem(new SecurityRequirement()
                         .addList(scheme_name))

                 .components(new Components()


                         .addSecuritySchemes(scheme_name, new SecurityScheme()
                                 .name(scheme_name)
                                 .type(SecurityScheme.Type.HTTP)
                                 .bearerFormat("JWT")
                                 .scheme("bearer"))
                 )


                 .info( new Info()
                         .title("Blog APP Api ")
                         .description("This is  just learning purpose Api")
                         .version("1.0")
                         .contact(new Contact()
                                 .name(" Bikash Hujdar Chaudhary")
                                 .email("hujdarbikash000@gmail.com")
                                 .url(""))

                 );


    }


}


