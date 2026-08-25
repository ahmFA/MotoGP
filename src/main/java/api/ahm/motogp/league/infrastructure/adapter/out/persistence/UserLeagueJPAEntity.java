package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.identity.infrastructure.adapter.out.persistence.UserJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name="user_league",
uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_userleague_user_league",
                columnNames = {"user_id", "league_id"})
        })
public class UserLeagueJPAEntity {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id")
        private UserJPAEntity user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="league_id")
        private LeagueJPAEntity league;

        public UserLeagueJPAEntity() {
        }

        public UserLeagueJPAEntity(Long id, UserJPAEntity user, LeagueJPAEntity league) {
                this.id = id;
                this.user = user;
                this.league = league;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public UserJPAEntity getUser() {
                return user;
        }

        public void setUser(UserJPAEntity user) {
                this.user = user;
        }

        public LeagueJPAEntity getLeague() {
                return league;
        }

        public void setLeague(LeagueJPAEntity league) {
                this.league = league;
        }
}
