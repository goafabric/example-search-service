package org.goafabric.example.searchservice;

import org.goafabric.example.searchservice.persistence.DatabaseProvisioning;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.Arrays;

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
            databaseProvisioning.run();
            if ((args.length > 0) && ("-check-integrity".equals(args[0]))) { SpringApplication.exit(context, () -> 0);}
        };

    }

    static class ApplicationRuntimeHints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            //Logger and ExceptionHandler
            hints.reflection().registerType(org.goafabric.example.searchservice.crossfunctional.TenantIdBean.class,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);

            //elastic
            registerReflection(org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository.class, hints);
            registerReflection(org.springframework.boot.actuate.elasticsearch.ElasticsearchRestClientHealthIndicator.class, hints);

            registerReflection(org.springframework.data.elasticsearch.core.event.BeforeConvertCallback.class, hints);
            registerReflection(org.springframework.data.elasticsearch.core.event.AfterConvertCallback.class, hints);
            registerReflection(org.springframework.data.elasticsearch.core.event.AfterSaveCallback.class, hints);
            registerReflection(org.springframework.data.elasticsearch.core.event.AfterLoadCallback.class, hints);

            hints.reflection().registerType(org.apache.http.impl.auth.BasicScheme.class,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);

            //persistence
            hints.reflection().registerType(org.goafabric.example.searchservice.persistence.AddressBo.class,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);

            hints.reflection().registerType(org.goafabric.example.searchservice.persistence.PersonBo.class,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);

            hints.reflection().registerType(org.goafabric.example.searchservice.persistence.SkillBo.class,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        }

        private void registerReflection(Class clazz, RuntimeHints hints) {
            hints.reflection().registerType(clazz,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS);
        }

        private void registerFields(Class clazz, RuntimeHints hints) {
            Arrays.stream(clazz.getDeclaredFields()).forEach(
                    r -> hints.reflection().registerField(r));
        }

    }

}
