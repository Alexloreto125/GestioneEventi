package AlexSpring.GestioneEventi.security;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.UnauthorizedException;
import AlexSpring.GestioneEventi.services.AuthService;
import AlexSpring.GestioneEventi.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


         //? C'è l'header?
        String authHeader= request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw  new UnauthorizedException("Token mancante. Inseriscilo nell'header");

        //* A QUESTO PUNTO ESISTE QUINDI ESTRAIAMO IL TOKEN

        String accessToken= authHeader.substring(7);

        //? Manipolato o scaduto?
        jwtTools.verifyToken(accessToken);      //METODO CREATO DAL TOOLS


        //* A QUESTO PUNTO E' VERIFICATO
        //* ESTRAIAMO ID DELL'UTENTE

        String userId= jwtTools.extractIdFromToken(accessToken); //METODO CREATO DAL TOOLS
        User user = this.authService.findById(UUID.fromString(userId));

        //* ASSOCIAMO L'UTENTE
        //* AUTENTICHIAMO

        Authentication authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //* continuo la catena
        filterChain.doFilter(request,response);

    }

    //? Ci sono richieste che non hanno bisogno di un token?
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
