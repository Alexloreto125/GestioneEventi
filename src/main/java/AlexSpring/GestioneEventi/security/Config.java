package AlexSpring.GestioneEventi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Bean
    PasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder(11);
        // 11 è il NUMERO DI ROUNDS, ovvero quante volte viene eseguito l'algoritmo. Questo valore
        // è utile per poter personalizzare la velocità di esecuzione di BCrypt. Più è veloce, meno sicure
        // saranno le password, e ovviamente viceversa. Bisogna comunque tenere sempre in considerazione
        // anche il fatto che se lo rendessimo estremamente lento peggiorerebbe la UX. Bisogna trovare il
        // giusto bilanciamento tra le 2.
        // 11 significa che l'algoritmo ogni volta viene eseguito 2^11 volte cioè 2048 volte. Su un computer
        // di prestazioni medie vuol dire circa 100/200 ms
    }

}
