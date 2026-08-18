package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipJPAEntity;
import api.ahm.motogp.league.application.port.in.command.CreateLeagueCommand;
import api.ahm.motogp.league.application.port.out.LeagueRepositoryPort;
import api.ahm.motogp.league.domain.model.League;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class LeaguePersistenceAdapter implements LeagueRepositoryPort {

    private final SpringDataLeagueRepository springDataLeagueRepository;
    private final EntityManager entityManager;

    public LeaguePersistenceAdapter(SpringDataLeagueRepository springDataLeagueRepository,
                                    EntityManager entityManager) {
        this.springDataLeagueRepository = springDataLeagueRepository;
        this.entityManager = entityManager;
    }

    @Override
    public League createLeague(CreateLeagueCommand createLeagueCommand) {
        LeagueJPAEntity leagueJPAEntity = new LeagueJPAEntity();
        leagueJPAEntity.setId(0);
        leagueJPAEntity.setChampionship(entityManager.getReference(ChampionshipJPAEntity.class, createLeagueCommand.championshipId()));
        leagueJPAEntity.setName(createLeagueCommand.name());
        return toDomain(springDataLeagueRepository.save(leagueJPAEntity));
    }

    @Override
    public boolean existsLeague(long leagueId) {
        return springDataLeagueRepository.existsById(leagueId);
    }

    private League toDomain(LeagueJPAEntity leagueJPAEntity) {
        return new League(
                leagueJPAEntity.getId(),
                leagueJPAEntity.getChampionship().getId(),
                leagueJPAEntity.getName()
        );
    }
}
