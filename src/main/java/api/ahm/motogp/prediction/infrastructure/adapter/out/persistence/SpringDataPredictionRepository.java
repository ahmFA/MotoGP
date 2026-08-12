package api.ahm.motogp.prediction.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataPredictionRepository extends JpaRepository<PredictionJPAEntity, Integer> {
    Optional<PredictionJPAEntity> findByUserIdAndEventId(Long userId, Long eventId);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
}
