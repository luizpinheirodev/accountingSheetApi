package net.sicredi.accountingSheet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperCustomConfig {

    @Bean
    public ObjectMapperCustom objectMapperCustom() {
        return new ObjectMapperCustom();
    }

    public class ObjectMapperCustom {
        public ObjectMapper newObjectMapper() {
            return new ObjectMapper().findAndRegisterModules();
        }
    }

}
