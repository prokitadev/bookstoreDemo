package com.empik.demo.github

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.empik.demo.data.github.GithubService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.github.tomakehurst.wiremock.WireMockServer
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class GithubClientSpec extends Specification {

    @Subject
    @Autowired
    private GithubService service

    def JSON_RESPONSE = "github_user_response.json"
    def FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME

    def "It should properly response" () {
        given:
        def login = "octocat"
        WireMockServer wireMock = new WireMockServer(8089)
        wireMock.start()

        and:
        configureFor("localhost", wireMock.port())
        stubFor(get(urlMatching("/users/octocat")).willReturn(ok().
                withHeader("Content-Type", "application/json").withBodyFile(JSON_RESPONSE)))

        when:
        def user = service.getUser(login)

        then:
        user.id == 583231
        user.login == login
        user.name == "The Octocat"
        user.type == "User"
        user.avatarUrl == "https://avatars.githubusercontent.com/u/583231?v=4"
        user.createdAt == LocalDateTime.parse("2011-01-25T18:44:36Z", FORMATTER)
        user.calculations == "0.010783608914450035945363048166786480"

        cleanup:
        wireMock.stop()
    }

}
