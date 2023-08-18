package ai.moneymate.api.config;

import ai.moneymate.api.users.exceptions.UserNotFoundException;
import ai.moneymate.api.users.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Log4j2
public class AppConfig {
    private final ConfigurableApplicationContext applicationContext;
    private final UserRepository userRepository;

    public AppConfig(ConfigurableApplicationContext applicationContext, UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(applicationContext.getBean(UserDetailsService.class));
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> userRepository.findByUsername(username).orElseThrow(() ->{
            log.error("Throwing user not found exception with username=" + username);
            return new UserNotFoundException("User not found with username=" + username);
        });
    }
}
