package AlexSpring.GestioneEventi.security;

import AlexSpring.GestioneEventi.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;


    //* DOBBIAMO CREARE IL TOKEN QUINDI METODO CREATE TOKEN

    public String createToken(User user){
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()*1000*60*60*24*7)).subject(String.valueOf(user.getId())).signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

    }




}
