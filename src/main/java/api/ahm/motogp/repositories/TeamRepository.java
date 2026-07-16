package api.ahm.motogp.repositories;

import api.ahm.motogp.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {


}
