package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.application.port.in.command.CreateChampionshipGrandPrixCommand;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixQueryPort;
import api.ahm.motogp.championship.application.port.out.ChampionshipGrandPrixRepositoryPort;
import api.ahm.motogp.championship.application.port.query.ChampionshipGrandPrixView;
import api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence.GrandPrixJPAEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChampionshipGrandPrixPersistenceAdapter implements ChampionshipGrandPrixQueryPort, ChampionshipGrandPrixRepositoryPort {

    private final SpringDataChampionshipGrandPrixRepository springDataChampionshipGrandPrixRepository;
    private final EntityManager em;

    public ChampionshipGrandPrixPersistenceAdapter(SpringDataChampionshipGrandPrixRepository springDataChampionshipGrandPrixRepository,
                                                   EntityManager em) {
        this.springDataChampionshipGrandPrixRepository = springDataChampionshipGrandPrixRepository;
        this.em = em;
    }

    @Override
    public List<ChampionshipGrandPrixView> getChampionshipGrandPrixesResponse(int championshipId) {
        return springDataChampionshipGrandPrixRepository.getChampionshipGrandPrixes(championshipId);
    }

    @Override
    public ChampionshipGrandPrixView getChampionshipGrandPrixResponse(int championshipId, int championshipGrandPrixId) {
        return springDataChampionshipGrandPrixRepository.getChampionshipGrandPrix(championshipId, championshipGrandPrixId);
    }

    @Override
    public boolean existsChampionshipGrandPrixById(int championshipGrandPrixId) {
        return springDataChampionshipGrandPrixRepository.existsById(championshipGrandPrixId);
    }

    @Override
    public boolean existsChampionshipGrandPrixByChampionshipIdAndId(int championshipId, int championshipGrandPrixId) {
        return springDataChampionshipGrandPrixRepository.existsChampionshipGrandPrixByChampionshipIdAndId(championshipId, championshipGrandPrixId);
    }

    @Override
    public boolean existsChampionshipGrandPrixByChampionshipIdAndGrandPrixId(int championshipId, int grandPrixId) {
        return springDataChampionshipGrandPrixRepository.existsChampionshipGrandPrixByChampionshipIdAndGrandPrixId(championshipId, grandPrixId);
    }

    @Override
    public ChampionshipGrandPrixView createChampionshipGrandPrix(CreateChampionshipGrandPrixCommand createCommand) {
        ChampionshipGrandPrixJPAEntity championshipGrandPrixJPAEntity = new ChampionshipGrandPrixJPAEntity();
        championshipGrandPrixJPAEntity.setId(0);
        championshipGrandPrixJPAEntity.setGrandPrix(em.getReference(GrandPrixJPAEntity.class, createCommand.grandPrixId()));
        championshipGrandPrixJPAEntity.setChampionship(em.getReference(ChampionshipJPAEntity.class, createCommand.championshipId()));
        championshipGrandPrixJPAEntity.setDate(createCommand.date());
        championshipGrandPrixJPAEntity.setRoundNumber(createCommand.roundNumber());

        ChampionshipGrandPrixJPAEntity newChampionshipGrandPrix = springDataChampionshipGrandPrixRepository.save(championshipGrandPrixJPAEntity);
        return springDataChampionshipGrandPrixRepository.getChampionshipGrandPrix(
                newChampionshipGrandPrix.getChampionship().getId(),
                newChampionshipGrandPrix.getId()
        );
    }

    @Override
    public void deleteChampionshipGrandPrixById(int championshipGrandPrixId) {
        springDataChampionshipGrandPrixRepository.deleteById(championshipGrandPrixId);
    }
}
