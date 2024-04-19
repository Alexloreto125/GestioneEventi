package AlexSpring.GestioneEventi.services;

import AlexSpring.GestioneEventi.entities.User;
import AlexSpring.GestioneEventi.exceptions.NotFoundException;
import AlexSpring.GestioneEventi.exceptions.UnauthorizedException;
import AlexSpring.GestioneEventi.payloads.UserLoginDTO;
import AlexSpring.GestioneEventi.repositories.UserDAO;
import AlexSpring.GestioneEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTTools jwtTools;


    public User findById(UUID userId){
        return this.userDAO.findById(userId).orElseThrow(()->new NotFoundException(userId));


    }

    //* ORA CREIAMO UN METODO PER GENERARE IL TOKEN AL LOGIN

    public String generateToke(UserLoginDTO payload){
        //? Credenziali valide? controllo
        //* CONTROLLO DB

        User user = this.userService.findByEmail(payload.email());
        //* SE SONO QUI VUOL DIRE CHE NON HA THROWATO UN EXCEPTION NEL FIND EMAIL
        if (user.getPassword().equals(payload.password())){
            //? PASSWORD NEL DB UGUALE AL PAYLOAD?
            //* ALLORA CREO IL TOKEN
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }

    }


    public User updateUser(UUID userId,User userUpdate){

    User found= this.findById(userId);

    if (userUpdate.getName()!= null){
        found.setName(userUpdate.getName());
    }

    if (userUpdate.getSurname()!= null){
        found.setSurname(userUpdate.getSurname());
    }

    if (userUpdate.getEmail()!= null){
        found.setEmail(userUpdate.getEmail());
    }

    if (userUpdate.getPassword() != null){
        found.setPassword(userUpdate.getPassword());
    }

    return this.userDAO.save(found);

    }
}
