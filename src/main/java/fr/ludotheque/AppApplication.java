package fr.ludotheque;

import fr.ludotheque.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


    @Bean
    public PasswordEncoder passwordEncoder() {


	    //Mot de passe stockés en base via BCrypt

        return new BCryptPasswordEncoder();
    }

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

    @Bean
    public AuditorAware<User> auditor() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty();
            }

            return Optional.of((User) authentication.getPrincipal());
        };
    }
}
