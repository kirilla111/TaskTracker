package hibernate.repository;

import hibernate.entity.Desk;
import hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

    Optional<Desk> findById(Long id);
}
