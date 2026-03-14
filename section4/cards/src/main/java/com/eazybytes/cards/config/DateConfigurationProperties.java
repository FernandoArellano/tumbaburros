package com.eazybytes.cards.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "current.date")
@Getter
@Setter
public class DateConfigurationProperties {

    private int day;
    private int month;
    private int year;


}
