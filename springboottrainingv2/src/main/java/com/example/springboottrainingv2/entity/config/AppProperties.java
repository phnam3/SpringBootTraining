package com.example.springboottrainingv2.entity.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("app")
public class AppProperties {
    private String defaultTitle;
    private String title;
    private String description;
}
