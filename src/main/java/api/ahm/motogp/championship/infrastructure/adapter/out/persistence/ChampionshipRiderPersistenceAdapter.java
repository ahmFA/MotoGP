package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipRiderCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderQueryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipRiderRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipRiderView;
import api.ahm.motogp.rider.infrastructure.adapter.out.persistence.RiderJPAEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChampionshipRiderPersistenceAdapter implements ChampionshipRiderQueryPort, ChampionshipRiderRepositoryPort {

    private final SpringDataChampionshipRiderRepository springDataChampionshipRiderRepository;
    private final EntityManager em;

    public ChampionshipRiderPersistenceAdapter(SpringDataChampionshipRiderRepository springDataChampionshipRiderRepository,
                                               EntityManager em) {
        this.springDataChampionshipRiderRepository = springDataChampionshipRiderRepository;
        this.em = em;
    }

    @Override
    public List<ChampionshipRiderView> getChampionshipRidersResponse(int championshipId) {
        return springDataChampionshipRiderRepository.getChampionshipRiders(championshipId);
    }

    @Override
    public ChampionshipRiderView getChampionshipRiderResponse(int championshipId, int championshipRiderId) {
        return springDataChampionshipRiderRepository.getChampionshipRider(championshipId, championshipRiderId);
    }

    @Override
    public boolean existsChampionshipRiderById(int championshipRiderId) {
        return springDataChampionshipRiderRepository.existsById(championshipRiderId);
    }

    @Override
    public boolean existsChampionshipRiderByChampionshipIdAndId(int championshipId, int championshipRiderId) {
        return springDataChampionshipRiderRepository.existsChampionshipRiderByChampionshipIdAndId(championshipId, championshipRiderId);
    }

    @Override
    public boolean existsChampionshipRiderByChampionshipIdAndRiderId(int championshipId, int riderId) {
        return springDataChampionshipRiderRepository.existsChampionshipRiderByChampionshipIdAndRiderId(championshipId, riderId);
    }

    @Override
    public ChampionshipRiderView createChampionshipRider(CreateChampionshipRiderCommand createCommand) {
        ChampionshipRiderJPAEntity championshipRiderJPAEntity = new ChampionshipRiderJPAEntity();
        championshipRiderJPAEntity.setId(0);
        championshipRiderJPAEntity.setRider(em.getReference(RiderJPAEntity.class, createCommand.riderId()));
        championshipRiderJPAEntity.setTeam(em.getReference(ChampionshipTeamJPAEntity.class, createCommand.teamId()));
        championshipRiderJPAEntity.setChampionship(em.getReference(ChampionshipJPAEntity.class, createCommand.championshipId()));
        championshipRiderJPAEntity.setNumber(createCommand.number());

        ChampionshipRiderJPAEntity newChampionshipRider = springDataChampionshipRiderRepository.save(championshipRiderJPAEntity);
        return springDataChampionshipRiderRepository.getChampionshipRider(
                newChampionshipRider.getChampionship().getId(),
                newChampionshipRider.getId()
        );
    }

    @Override
    public void deleteChampionshipRiderById(int championshipRiderId) {
        springDataChampionshipRiderRepository.deleteById(championshipRiderId);
    }
}
