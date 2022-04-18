package com.empik.demo.api.user;

import com.empik.demo.api.user.model.UserHistory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserHistoryService {

    private UserHistoryRepository repository;

    public void updateUserHistory(String login) {
        UserHistory userHistory = repository.findOne(Example.of(buildUserHistory(login))).orElse(buildUserHistory(login));
        userHistory.setRequestCount(Optional.ofNullable(userHistory.getRequestCount()).orElse(0L) + 1L);
        repository.save(userHistory);
    }

    private UserHistory buildUserHistory(String login) {
        return UserHistory.builder().login(login).build();
    }

}
