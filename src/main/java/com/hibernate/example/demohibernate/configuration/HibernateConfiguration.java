package com.hibernate.example.demohibernate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Configuration
public class HibernateConfiguration {

    //El Session Factory solo se instancia una vez para servir la aplicacion
    @Bean
    @Scope("singleton")
    public EntityManagerFactory hibernateSessionFactory() {
        return Persistence
            .createEntityManagerFactory("hibernateExample");
        
    }
}
