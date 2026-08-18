package api.ahm.motogp.league.infrastructure.adapter.out.persistence;

import api.ahm.motogp.championship.infrastructure.adapter.out.persistence.ChampionshipJPAEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "league")
public class LeagueJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private ChampionshipJPAEntity championship;

    @Column(name = "name")
    private String name;

    public LeagueJPAEntity() {
    }

    public LeagueJPAEntity(int id, ChampionshipJPAEntity championship, String name) {
        this.id = id;
        this.championship = championship;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChampionshipJPAEntity getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionshipJPAEntity championship) {
        this.championship = championship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
