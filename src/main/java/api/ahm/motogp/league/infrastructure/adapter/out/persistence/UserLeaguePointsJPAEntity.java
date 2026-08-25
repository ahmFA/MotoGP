package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name="user_league_points_total",
uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_ulpt_userleague",
                columnNames = {"user_league_id"})
})
public class UserLeaguePointsJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_league_id")
    private UserLeagueJPAEntity userLeague;

    @Column(name="points")
    private int points;

    public UserLeaguePointsJPAEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLeagueJPAEntity getUserLeague() {
        return userLeague;
    }

    public void setUserLeague(UserLeagueJPAEntity userLeague) {
        this.userLeague = userLeague;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
