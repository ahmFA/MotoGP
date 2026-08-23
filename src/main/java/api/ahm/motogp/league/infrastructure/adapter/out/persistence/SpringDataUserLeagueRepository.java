package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.league.application.port.query.UserLeagueView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataUserLeagueRepository extends JpaRepository<UserLeagueJPAEntity, Long> {

    @Query("""
            select new api.ahm.motogp.league.application.port.query.UserLeagueView(
                ul.id,
                l.id,
                u.id,
                u.username,
                u.email,
                u.role
            )
            from UserLeagueJPAEntity ul
            join ul.league l
            join ul.user u
            where l.id = :leagueId
            order by u.username
            """)
    List<UserLeagueView> getUsersByLeague(long leagueId);

    @Query("""
            select new api.ahm.motogp.league.application.port.query.UserLeagueView(
                ul.id,
                l.id,
                u.id,
                u.username,
                u.email,
                u.role
            )
            from UserLeagueJPAEntity ul
            join ul.league l
            join ul.user u
            where l.id = :leagueId and u.id = :userId
            """)
    UserLeagueView getUserByLeague(long leagueId, long userId);

    boolean existsUserLeagueByLeagueIdAndUserId(long leagueId, long userId);
}
