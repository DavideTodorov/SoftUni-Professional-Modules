package com.example.cardealer.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeansConfigurations {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
