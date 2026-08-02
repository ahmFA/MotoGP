package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataChampionshipGrandPrixRepository extends JpaRepository<ChampionshipGrandPrixJPAEntity, Integer> {

    @Query("""
            select new api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView(
                          cgp.id,
                          gp.id,
                          gp.name,
                          gp.circuitName,
                          cgp.date,
                          cgp.roundNumber
                      )
                      from ChampionshipGrandPrixJPAEntity cgp
                      join cgp.grandPrix gp
                      where cgp.championship.id = :championshipId
                      order by cgp.roundNumber
                """)
    List<ChampionshipGrandPrixView> getChampionshipGrandPrixes(int championshipId);

    @Query("""
            select new api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView(
                          cgp.id,
                          gp.id,
                          gp.name,
                          gp.circuitName,
                          cgp.date,
                          cgp.roundNumber
                      )
                      from ChampionshipGrandPrixJPAEntity cgp
                      join cgp.grandPrix gp
                      where cgp.championship.id = :championshipId and cgp.id = :championshipGrandPrixId
                """)
    ChampionshipGrandPrixView getChampionshipGrandPrix(int championshipId, int championshipGrandPrixId);

    boolean existsChampionshipGrandPrixByChampionshipIdAndId(int championshipId, int id);
    boolean existsChampionshipGrandPrixByChampionshipIdAndGrandPrixId(int championshipId, int grandPrixId);
}
