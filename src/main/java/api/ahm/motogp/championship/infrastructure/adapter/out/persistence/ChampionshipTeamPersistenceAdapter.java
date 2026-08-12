package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipTeamCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamRepositoryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipTeamQueryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipTeamView;
import api.ahm.motogp.shared.constructor.infrastructure.adapter.out.persistance.ConstructorJPAEntity;
import api.ahm.motogp.team.infrastructure.adapter.out.persistence.TeamJPAEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChampionshipTeamPersistenceAdapter implements ChampionshipTeamQueryPort, ChampionshipTeamRepositoryPort {

    private final SpringDataChampionshipTeamRepository springDataChampionshipTeamRepository;
    private final EntityManager em;

    public ChampionshipTeamPersistenceAdapter(SpringDataChampionshipTeamRepository springDataChampionshipTeamRepository,  EntityManager em) {
        this.springDataChampionshipTeamRepository = springDataChampionshipTeamRepository;
        this.em = em;
    }

    public List<ChampionshipTeamView> getChampionshipTeamsResponse(int championshipId) {
        return springDataChampionshipTeamRepository.getChampionshipTeams(championshipId);
    }

    public ChampionshipTeamView getChampionshipTeamResponse(int championshipId, int championshipTeamId) {
        return springDataChampionshipTeamRepository.getChampionshipTeam(championshipId,  championshipTeamId);
    }

    @Override
    public ChampionshipTeamView createChampionshipTeam(CreateChampionshipTeamCommand createCommand){
        ChampionshipTeamJPAEntity championshipTeamJPAEntity = new ChampionshipTeamJPAEntity();
        championshipTeamJPAEntity.setId(0);
        championshipTeamJPAEntity.setTeam(em.getReference(TeamJPAEntity.class, createCommand.teamId()));
        championshipTeamJPAEntity.setName(createCommand.name());
        championshipTeamJPAEntity.setConstructor(em.getReference(ConstructorJPAEntity.class, createCommand.constructorId()));
        championshipTeamJPAEntity.setChampionship(em.getReference(ChampionshipJPAEntity.class, createCommand.championshipId()));
        ChampionshipTeamJPAEntity newChampionshipTeam = springDataChampionshipTeamRepository.save(championshipTeamJPAEntity);
        return springDataChampionshipTeamRepository.getChampionshipTeam(newChampionshipTeam.getChampionship().getId(), newChampionshipTeam.getId());
    }

    @Override
    public void deleteChampionshipTeamById(int championshipTeamId) {
        springDataChampionshipTeamRepository.deleteById(championshipTeamId);
    }

    @Override
    public boolean existsChampionshipTeamById(int championshipTeamId) {
        return springDataChampionshipTeamRepository.existsById(championshipTeamId);
    }

    @Override
    public boolean existsChampionshipTeamByChampionshipIdAndId(int championshipId, int championshipTeamId) {
        return springDataChampionshipTeamRepository.existsChampionshipTeamByChampionshipIdAndId(championshipId, championshipTeamId);
    }

    @Override
    public boolean existsChampionshipTeamByChampionshipIdAndTeamId(int championshipId, int teamId) {
        return springDataChampionshipTeamRepository.existsChampionshipTeamByChampionshipIdAndTeamId(championshipId, teamId);
    }

    @Override
    public boolean existsChampionshipTeamByChampionshipIdAndName(int championshipId, String name) {
        return springDataChampionshipTeamRepository.existsChampionshipTeamByChampionshipIdAndName(championshipId, name);
    }
}
