package com.empik.demo.data.github.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubUserResponse {

    private String login;
    private Integer id;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private Long publicRepos;
    private Long followers;

}
