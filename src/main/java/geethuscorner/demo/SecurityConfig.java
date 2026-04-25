package geethuscorner.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filtrCain(HttpSecurity https) throws Exception{
        https.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth)-> auth
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return https.build();
    }

}
