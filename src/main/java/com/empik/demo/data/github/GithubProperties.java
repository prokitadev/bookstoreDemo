package com.empik.demo.data.github;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "github")
@Getter
@Setter
public class GithubProperties {

    private String host;
    private String users;

}
