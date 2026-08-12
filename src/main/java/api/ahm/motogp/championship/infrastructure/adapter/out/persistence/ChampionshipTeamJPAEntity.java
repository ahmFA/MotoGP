package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.shared.constructor.infrastructure.adapter.out.persistance.ConstructorJPAEntity;
import api.ahm.motogp.team.infrastructure.adapter.out.persistence.TeamJPAEntity;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="championship_team",
    uniqueConstraints = {
    @UniqueConstraint(
        name = "uk_championshipteam_team_championship",
        columnNames = {
                "team_id",
                "championship_id"
        }
    )
}
)
public class ChampionshipTeamJPAEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="team_id")
    private TeamJPAEntity team;

    @ManyToOne
    @JoinColumn(name="constructor_id")
    private ConstructorJPAEntity constructor;

    @ManyToOne
    @JoinColumn(name="championship_id")
    private ChampionshipJPAEntity championship;

    public ChampionshipTeamJPAEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamJPAEntity getTeam() {
        return team;
    }

    public void setTeam(TeamJPAEntity team) {
        this.team = team;
    }

    public ConstructorJPAEntity getConstructor() {
        return constructor;
    }

    public void setConstructor(ConstructorJPAEntity constructor) {
        this.constructor = constructor;
    }

    public ChampionshipJPAEntity getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionshipJPAEntity championship) {
        this.championship = championship;
    }
}
