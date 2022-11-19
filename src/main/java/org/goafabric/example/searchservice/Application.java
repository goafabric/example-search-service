package org.goafabric.example.searchservice;

import org.goafabric.example.searchservice.persistence.DatabaseProvisioning;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * Created by amautsch on 26.06.2015.
 */

@SpringBootApplication
@ImportRuntimeHints(Application.ApplicationRuntimeHints.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(ApplicationContext context, DatabaseProvisioning databaseProvisioning) {
        return args -> {
            if ((args.length > 0) && ("-check-integrity".equals(args[0]))) { SpringApplication.exit(context, () -> 0);}
            else {databaseProvisioning.run();}
        };
    }

    static class ApplicationRuntimeHints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.resources().registerPattern("graphql/*");
            hints.resources().registerPattern("graphiql/*");
        }


    }

}
