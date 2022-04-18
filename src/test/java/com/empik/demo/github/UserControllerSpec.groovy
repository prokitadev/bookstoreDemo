package com.empik.demo.github

import com.empik.demo.api.user.UserController
import com.empik.demo.api.user.UserHistoryRepository
import com.empik.demo.api.user.UserHistoryService
import com.empik.demo.api.user.UserService
import com.empik.demo.api.user.exception.NotFoundExcetpion
import com.empik.demo.api.user.model.UserDto
import com.empik.demo.api.user.model.UserHistory
import com.empik.demo.data.github.GithubService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.validation.support.BindingAwareModelMap
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class UserControllerSpec extends Specification{

    @Subject
    @Autowired
    private UserController controller

    @Autowired
    private UserService userService

    @Autowired
    private UserHistoryService userHistoryService

    @Autowired
    private GithubService githubService

    @Autowired
    private UserHistoryRepository repository

    def setup() {
        githubService = Mock()
        userService = new UserService(userHistoryService, githubService)
        controller = new UserController(userService)

    }

    def "It should return user and save history"() {
        given:
        def login = "octocat"
        def model = new BindingAwareModelMap()
        githubService.getUser(login) >> UserDto.builder().login(login).build()

        when:
        controller.getUser(login, model)

        then:
        model.getAttribute("user").getProperties().get("login") == login
        repository.findOne(Example.of(UserHistory.builder().login(login).build())).get().requestCount == 1
    }

    def "It should throw Not Found Exception if get user thrown error"() {
        given:
        def login = "octocat"
        def model = new BindingAwareModelMap()
        githubService.getUser(login) >>  new Exception()

        when:
        controller.getUser(login, model)

        then:
        thrown NotFoundExcetpion
    }

}
