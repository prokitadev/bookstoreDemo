package com.empik.demo.data.github;

import com.empik.demo.data.github.mapper.GithubUserResponseToUserDtoMapper;
import com.empik.demo.api.user.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GithubService {

    private GithubClient client;
    private GithubUserResponseToUserDtoMapper mapper;

    public UserDto getUser(@NonNull String login) {
        return mapper.map(client.performRequest(login, HttpMethod.GET));
    }

}
