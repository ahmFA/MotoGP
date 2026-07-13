package api.ahm.motogp.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="championship_grandprix")
public class ChampionshipGrandPrix {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="grandPrix_id")
    private GrandPrix grandPrix;

    @ManyToOne
    @JoinColumn(name="championship_id")
    private Championship championship;

    private Date date;

    private int round_number;

    public ChampionshipGrandPrix() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GrandPrix getRace() {
        return grandPrix;
    }

    public void setRace(GrandPrix grandPrix) {
        this.grandPrix = grandPrix;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRound_number() {
        return round_number;
    }

    public void setRound_number(int round_number) {
        this.round_number = round_number;
    }
}
