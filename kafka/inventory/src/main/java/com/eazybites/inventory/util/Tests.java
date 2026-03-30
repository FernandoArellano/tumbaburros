package com.eazybites.inventory.util;

import com.eazybites.inventory.dto.ConfigurationPropertiesTest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Tests {

    @Value("${spring.application.name}")
    private String propertyValue;

    private final ConfigurationPropertiesTest configurationPropertiesTest;

    public Tests(ConfigurationPropertiesTest configurationPropertiesTest) {
        this.configurationPropertiesTest = configurationPropertiesTest;
    }

    public void printAppNameWithValue(){
        System.out.println("app name with value: " + propertyValue);
        System.out.println("config properties: "+ configurationPropertiesTest);
    }
}


