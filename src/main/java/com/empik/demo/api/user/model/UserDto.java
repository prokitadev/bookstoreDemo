package com.empik.demo.api.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDto {

    private String login;
    private Integer id;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private String calculations;

}
