package br.com.fiap.clyvovet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication
@EnableCaching
public class ClyvoVetApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClyvoVetApiApplication.class, args);
    }
}