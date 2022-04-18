package com.empik.demo.api.user;

import com.empik.demo.api.user.exception.NotFoundExcetpion;
import com.empik.demo.api.user.model.UserDto;
import com.empik.demo.data.github.GithubService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserHistoryService userHistoryService;
    private GithubService githubService;

    public UserDto getUser(String login) {
        userHistoryService.updateUserHistory(login);
        try {
            return githubService.getUser(login);
        } catch (Exception e) {
            throw new NotFoundExcetpion();
        }
    }



}
