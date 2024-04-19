package AlexSpring.GestioneEventi.exceptions;

import AlexSpring.GestioneEventi.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handleBadRequest(BadRequestException exception) {
        if (exception.getErrorList() != null) {

            String message = exception.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorsResponseDTO(message, LocalDateTime.now());

        } else {
            return new ErrorsResponseDTO(exception.getMessage(), LocalDateTime.now());
        }
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorsResponseDTO handleNotFound(NotFoundException notFoundException) {
        return new ErrorsResponseDTO(notFoundException.getMessage(), LocalDateTime.now());
    }



    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsResponseDTO handleUnAuthorized(UnauthorizedException unauthorizedException){
        return new ErrorsResponseDTO(unauthorizedException.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsResponseDTO handleForbidden(AccessDeniedException ex){
        return new ErrorsResponseDTO("Non hai accesso a questa funzionalità!", LocalDateTime.now());
    }

}
