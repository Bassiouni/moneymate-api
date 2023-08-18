package ai.moneymate.api.users.controllers;

import ai.moneymate.api.users.dto.UserDTO;
import ai.moneymate.api.users.entities.UserEntity;
import ai.moneymate.api.users.exceptions.UserNotFoundException;
import ai.moneymate.api.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("{username}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable("username") String username) {
        try {
            UserDTO user = this.usersService.getUserByUsername(username);
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("profile")
    public ResponseEntity<UserEntity> getCurrentUserInfo() {
        UserEntity details = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(details);
    }
}
