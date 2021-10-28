package ru.usov.storetestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.usov.storetestapp"})
public class StoretestappApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoretestappApplication.class, args);
    }

}
