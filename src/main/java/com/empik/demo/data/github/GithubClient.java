package com.empik.demo.data.github;

import com.empik.demo.common.CustomRestTemplate;
import com.empik.demo.data.github.model.GithubUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GithubClient {

    private CustomRestTemplate restTemplate;
    private GithubProperties properties;

    public GithubUserResponse performRequest(String login, HttpMethod httpMethod) {
        return Optional.ofNullable(
                restTemplate.exchange(
                properties.getHost() + properties.getUsers() + "/" + login,
                httpMethod,
                null,
                GithubUserResponse.class).getBody())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

}
