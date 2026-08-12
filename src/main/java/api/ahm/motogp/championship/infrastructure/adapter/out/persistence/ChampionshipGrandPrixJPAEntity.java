package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence.GrandPrixJPAEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Date;

@Entity
@Table(name = "championship_grandprix",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_championshipgrandprix_grandprix_championship",
                        columnNames = {
                                "grandPrix_id",
                                "championship_id"
                        }
                )
        }
)
public class ChampionshipGrandPrixJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "grandPrix_id")
    private GrandPrixJPAEntity grandPrix;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private ChampionshipJPAEntity championship;

    @Column(name = "date")
    private Date date;

    @Column(name = "round_number")
    private int roundNumber;

    public ChampionshipGrandPrixJPAEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GrandPrixJPAEntity getGrandPrix() {
        return grandPrix;
    }

    public void setGrandPrix(GrandPrixJPAEntity grandPrix) {
        this.grandPrix = grandPrix;
    }

    public ChampionshipJPAEntity getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionshipJPAEntity championship) {
        this.championship = championship;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
