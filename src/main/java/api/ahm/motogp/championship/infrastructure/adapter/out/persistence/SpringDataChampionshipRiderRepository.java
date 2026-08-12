package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataChampionshipRiderRepository extends JpaRepository<ChampionshipRiderJPAEntity, Integer> {

    @Query("""
            select new api.ahm.motogp.championship.application.port.query.ChampionshipRiderView(
                          cr.id,
                          r.id,
                          r.name,
                          ct.id,
                          ct.name,
                          cr.number
                      )
                      from ChampionshipRiderJPAEntity cr
                      join cr.rider r
                      join cr.team ct
                      where cr.championship.id = :championshipId and r.active = true
                      order by cr.number
                """)
    List<ChampionshipRiderView> getChampionshipRiders(int championshipId);

    @Query("""
            select new api.ahm.motogp.championship.application.port.query.ChampionshipRiderView(
                          cr.id,
                          r.id,
                          r.name,
                          ct.id,
                          ct.name,
                          cr.number
                      )
                      from ChampionshipRiderJPAEntity cr
                      join cr.rider r
                      join cr.team ct
                      where cr.championship.id = :championshipId and cr.id = :championshipRiderId and r.active = true
                """)
    ChampionshipRiderView getChampionshipRider(int championshipId, int championshipRiderId);

    boolean existsChampionshipRiderByChampionshipIdAndId(int championshipId, int id);
    boolean existsChampionshipRiderByChampionshipIdAndRiderId(int championshipId, int riderId);
}
