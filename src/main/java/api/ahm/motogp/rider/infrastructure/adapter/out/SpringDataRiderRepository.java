package api.ahm.motogp.rider.infrastructure.adapter.out;

import api.ahm.motogp.rider.domain.model.Rider;
import api.ahm.motogp.rider.infrastructure.adapter.out.persistence.RiderJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataRiderRepository extends JpaRepository<RiderJPAEntity, Integer> {
    List<RiderJPAEntity> findByActiveTrue();
    Boolean existsRiderByNumber(Integer number);
    Boolean existsRiderByName(String name);
    Boolean existsRiderByIdNotAndName(Integer myId,String name);
    Boolean existsRiderByIdNotAndNumber(Integer myId,Integer number);
    Boolean existsRiderByIdAndActiveTrue(Integer id);
}
