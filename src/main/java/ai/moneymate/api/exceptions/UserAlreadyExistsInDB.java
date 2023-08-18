package ai.moneymate.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Username already exists")
public class UserAlreadyExistsInDB extends RuntimeException {
    public UserAlreadyExistsInDB(String msg) {
        super(msg);
    }
}
