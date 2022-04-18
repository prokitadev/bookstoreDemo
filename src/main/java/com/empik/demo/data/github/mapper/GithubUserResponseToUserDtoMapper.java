package com.empik.demo.data.github.mapper;

import com.empik.demo.api.user.model.UserDto;
import com.empik.demo.data.github.model.GithubUserResponse;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

@Component
@NoArgsConstructor
public class GithubUserResponseToUserDtoMapper {

    private static final BigDecimal SIX = BigDecimal.valueOf(6);
    private static final BigDecimal TWO = BigDecimal.valueOf(2);


    public UserDto map(GithubUserResponse userResponse) {
        return UserDto.builder()
                .id(userResponse.getId())
                .login(userResponse.getLogin())
                .name(Optional.ofNullable(userResponse.getName()).orElse(Strings.EMPTY))
                .type(userResponse.getType())
                .avatarUrl(userResponse.getAvatarUrl())
                .createdAt(userResponse.getCreatedAt())
                .calculations(getCalculations(userResponse))
                .build();
    }

    private String getCalculations(GithubUserResponse userResponse) {
        if(userResponse.getFollowers() == null || userResponse.getFollowers() == 0) {
            return Strings.EMPTY;
        }
        return SIX.divide(BigDecimal.valueOf(userResponse.getFollowers()), MathContext.DECIMAL128).multiply(
                TWO.add(BigDecimal.valueOf(Optional.ofNullable(userResponse.getPublicRepos()).orElse(0L)))).toString();
    }

}
