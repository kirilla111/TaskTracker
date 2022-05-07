package hibernate.repository;

import hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginAndPassword(String login, String password);

    Optional<User> findByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
