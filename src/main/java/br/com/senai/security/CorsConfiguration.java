package br.com.senai.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// classe responsavel por permitir que o navegador acesse as rotas da api, por questoes de seguranca e necessario
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
        allowedOrigins("http://localhost:3000").
        allowedMethods("*");
    }

}
