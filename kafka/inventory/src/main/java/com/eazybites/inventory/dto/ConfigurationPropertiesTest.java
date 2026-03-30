package com.eazybites.inventory.dto;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom")
public record ConfigurationPropertiesTest(String name, int age, String description ){};
