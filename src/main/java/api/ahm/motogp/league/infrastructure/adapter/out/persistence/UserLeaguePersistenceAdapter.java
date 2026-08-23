package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.identity.infrastructure.adapter.out.persistence.UserJPAEntity;
import api.ahm.motogp.league.application.port.in.command.CreateUserLeagueCommand;
import api.ahm.motogp.league.application.port.out.UserLeagueRepositoryPort;
import api.ahm.motogp.league.application.port.query.UserLeagueView;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserLeaguePersistenceAdapter implements UserLeagueRepositoryPort {

    private final SpringDataUserLeagueRepository springDataUserLeagueRepository;
    private final SpringDataUserLeaguePointsRepository springDataUserLeaguePointsRepository;
    private final EntityManager entityManager;

    public UserLeaguePersistenceAdapter(SpringDataUserLeagueRepository springDataUserLeagueRepository,
                                        SpringDataUserLeaguePointsRepository springDataUserLeaguePointsRepository,
                                        EntityManager entityManager) {
        this.springDataUserLeagueRepository = springDataUserLeagueRepository;
        this.springDataUserLeaguePointsRepository = springDataUserLeaguePointsRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserLeagueView> getUsersByLeague(long leagueId) {
        return springDataUserLeagueRepository.getUsersByLeague(leagueId);
    }

    @Override
    public UserLeagueView getUserByLeague(long leagueId, long userId) {
        return springDataUserLeagueRepository.getUserByLeague(leagueId, userId);
    }

    @Override
    public boolean existsUserLeagueByLeagueIdAndUserId(long leagueId, long userId) {
        return springDataUserLeagueRepository.existsUserLeagueByLeagueIdAndUserId(leagueId, userId);
    }

    @Override
    public UserLeagueView createUserLeague(CreateUserLeagueCommand createUserLeagueCommand) {
        UserLeagueJPAEntity userLeagueJPAEntity = new UserLeagueJPAEntity();
        userLeagueJPAEntity.setLeague(entityManager.getReference(LeagueJPAEntity.class, createUserLeagueCommand.leagueId()));
        userLeagueJPAEntity.setUser(entityManager.getReference(UserJPAEntity.class, createUserLeagueCommand.userId().id()));
        UserLeagueJPAEntity savedEntity = springDataUserLeagueRepository.save(userLeagueJPAEntity);

        UserLeaguePointsJPAEntity ulPoints = new UserLeaguePointsJPAEntity();
        ulPoints.setUserLeague(savedEntity);
        ulPoints.setPoints(0);
        springDataUserLeaguePointsRepository.save(ulPoints);

        return springDataUserLeagueRepository.getUserByLeague(savedEntity.getLeague().getId(), savedEntity.getUser().getId());
    }
}
