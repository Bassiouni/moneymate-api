package ai.moneymate.api.auth;

import ai.moneymate.api.config.JwtService;
import ai.moneymate.api.exceptions.UserAlreadyExistsInDB;
import ai.moneymate.api.users.entities.UserEntity;
import ai.moneymate.api.users.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
					   AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public AuthResponse register(RegisterRequest request) throws UserAlreadyExistsInDB {
		if (userRepository.existsByUsername(request.getUsername()))
			throw new UserAlreadyExistsInDB("This username is already taken");

		UserEntity user = UserEntity
				.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.build();

		userRepository.save(user);
		String token = jwtService.generateToken(user);
		return AuthResponse
				.builder()
				.token(token)
				.build();
	}

	public AuthResponse authenticate(AuthRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()));

		Optional<UserEntity> user = userRepository.findByUsername(request.getUsername());
		if (user.isEmpty()) {
			log.info("authenticating a non-existing user; falling back to empty token");
			return AuthResponse
					.builder().build();
		}

		String token = jwtService.generateToken(user.get());

		return AuthResponse
				.builder()
				.token(token)
				.build();
	}
}
