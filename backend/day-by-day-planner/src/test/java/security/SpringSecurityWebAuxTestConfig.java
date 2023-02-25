package security;

import com.ikadev.daybydayplanner.persistence.model.Role;
import com.ikadev.daybydayplanner.persistence.model.User;
import com.ikadev.daybydayplanner.persistence.model.UserPrinciple;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;
import java.util.Set;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsServiceTest() {
        User user = User.builder().username("test").password("test").roles(Set.of(Role.USER)).build();
        UserPrinciple userPrinciple = new UserPrinciple(user);
        return new InMemoryUserDetailsManager(List.of(
                userPrinciple
        ));
    }
}