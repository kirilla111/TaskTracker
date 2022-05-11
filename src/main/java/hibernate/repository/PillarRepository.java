package hibernate.repository;

import hibernate.entity.Pillar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillarRepository extends JpaRepository<Pillar, Long> {
}
