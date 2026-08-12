package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataChampionshipTeamRepository extends JpaRepository<ChampionshipTeamJPAEntity, Integer> {
    @Query("""
            select new api.ahm.motogp.championship.application.port.query.ChampionshipTeamView(
                          ct.id,
                          t.id,
                          ct.name,
                          c.id,
                          c.name
                      )
                      from ChampionshipTeamJPAEntity ct
                      join ct.team t
                      join ct.constructor c
                      where ct.championship.id = :championshipId and t.active = true
                      order by t.name 
                """)
    List<ChampionshipTeamView> getChampionshipTeams(int championshipId);

    @Query("""
            select new api.ahm.motogp.championship.application.port.query.ChampionshipTeamView(
                          ct.id,
                          t.id,
                          ct.name,
                          c.id,
                          c.name
                      )
                      from ChampionshipTeamJPAEntity ct
                      join ct.team t
                      join ct.constructor c
                      where ct.championship.id = :championshipId and ct.id = :championshipTeamId and t.active = true
                      order by t.name 
                """)
    ChampionshipTeamView getChampionshipTeam(int championshipId, int championshipTeamId);
    boolean existsChampionshipTeamByChampionshipIdAndId(int championshipId, int id);
    boolean existsChampionshipTeamByChampionshipIdAndName(int championshipId, String name);
    boolean existsChampionshipTeamByChampionshipIdAndTeamId(int championshipId, int teamId);
}
