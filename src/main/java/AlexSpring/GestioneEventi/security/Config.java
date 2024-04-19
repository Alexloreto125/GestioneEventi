package AlexSpring.GestioneEventi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {


    //! CONFIGURIAMO LI FILTER CHAIN

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

    //DISABILITIAMO IL LOGIN

        httpSecurity.formLogin(http -> http.disable());

        // DISABILITIAMO SICUREZZE CSRF

        httpSecurity.csrf(http->http.disable());

        //Impostiamo LE SESSIONI su base TOKEN

        httpSecurity.sessionManagement(http-> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }

}
