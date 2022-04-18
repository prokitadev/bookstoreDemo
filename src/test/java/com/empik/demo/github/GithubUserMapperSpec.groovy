package com.empik.demo.github

import com.empik.demo.data.github.mapper.GithubUserResponseToUserDtoMapper
import com.empik.demo.data.github.model.GithubUserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class GithubUserMapperSpec extends Specification {

    @Subject
    @Autowired
    private GithubUserResponseToUserDtoMapper mapper

    def "It should correctly calculate value for big numbers"() {
        given:
        def publicRepose = 89L
        def followers = 23943284L
        def userResponse = new GithubUserResponse()
        userResponse.setPublicRepos(publicRepose)
        userResponse.setFollowers(followers)

        when:
        def user = mapper.map(userResponse)

        then:
        user.getCalculations() == "0.0000228038893912798261090667428912424890"
    }

    def "It should correctly calculate value for small numbers"() {
        given:
        def publicRepose = 8
        def followers = 6
        def userResponse = new GithubUserResponse()
        userResponse.setPublicRepos(publicRepose)
        userResponse.setFollowers(followers)

        when:
        def user = mapper.map(userResponse)

        then:
        user.getCalculations() == "10"
    }


    def "It should return empty string if divide by zero"() {
        given:
        def publicRepose = 864646
        def followers = 0
        def userResponse = new GithubUserResponse()
        userResponse.setPublicRepos(publicRepose)
        userResponse.setFollowers(followers)

        when:
        def user = mapper.map(userResponse)

        then:
        user.getCalculations() == ""
    }

}
