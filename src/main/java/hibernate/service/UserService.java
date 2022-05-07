package hibernate.service;

import hibernate.entity.User;
import hibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByLoginAndPassword(String login, String password) {
        return userRepository.existsByLoginAndPassword(login, password);
    }

    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password)
            .orElseThrow((() -> new IllegalStateException("No such user")));
    }

    public boolean findByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public void createUser(String login, String firstName, String patronymic, String lastName, String password, String email) throws Exception{
        String trimmedLoginInLowerCase = login.trim().toLowerCase();

        validateRegisteredLogin(trimmedLoginInLowerCase);
        validateRegisteredEmail(email);

        User user = new User(trimmedLoginInLowerCase, firstName, patronymic, lastName, password, email);
        userRepository.save(user);
    }

    private void validateRegisteredLogin(String login) throws Exception {
        if (userRepository.existsByLogin(login)) {
            throw new Exception("User with this login already exists!");
        }
    }

    private void validateRegisteredEmail(String email) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new Exception("User with this email already exists!");
        }
    }
}

