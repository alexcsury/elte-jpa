package com.epam.elte.training.springbootjpa;

import com.epam.elte.training.springbootjpa.io.ManagementConsolePrinter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            final ManagementConsolePrinter printer = ctx.getBean(ManagementConsolePrinter.class);
            printer.showOptions();
            System.exit(0);
        };
    }
}
