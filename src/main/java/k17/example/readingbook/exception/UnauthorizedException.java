package k17.example.readingbook.exception;

import k17.example.readingbook.constant.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super(MessageError.INVALID_USERNAME_OR_PASSWORD);
    }
}
