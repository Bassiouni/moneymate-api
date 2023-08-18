package ai.moneymate.api.users.services;

import ai.moneymate.api.users.dto.UserDTO;
import ai.moneymate.api.users.entities.UserEntity;
import ai.moneymate.api.users.exceptions.UserNotFoundException;
import ai.moneymate.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public UserDTO getUserByID(int id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);

        if (userEntity.isEmpty())
            throw new UserNotFoundException("Can't locate user with id=" + id);

        return UserDTO.from(userEntity.get());
    }

    public UserDTO getUserByUsername(String username) throws UserNotFoundException {
        Optional<UserEntity> userEntity = this.userRepository.findByUsername(username);

        if (userEntity.isEmpty())
            throw new UserNotFoundException("Can't locate user with username=" + username);

        return UserDTO.from(userEntity.get());
    }
}
