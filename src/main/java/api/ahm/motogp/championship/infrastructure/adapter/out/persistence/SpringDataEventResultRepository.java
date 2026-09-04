package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.query.EventResultView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataEventResultRepository extends JpaRepository<EventResultJPAEntity, Integer> {
    boolean existsByChampionshipEventIdAndChampionshipRiderId(int championshipEventId, int championshipRiderId);
    @Query("""
            select new api.ahm.motogp.championship.application.port.query.EventResultView(
                          er.id,
                          er.position,
                          er.points,
                          cr.id,
                          r.name,
                          cr.number
                      )
                      from EventResultJPAEntity er
                      join er.championshipRider cr
                      join cr.rider r
                      where er.championshipEvent.id = :eventId
                      order by er.position asc 
                """)
    List<EventResultView> getEventResults(int eventId);
}
