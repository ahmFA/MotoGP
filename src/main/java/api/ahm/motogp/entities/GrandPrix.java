package api.ahm.motogp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grandprix")
public class GrandPrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "circuit_name")
    private String circuitName;
    @ManyToOne
    @JoinColumn(name="country")
    private Country country;

    public GrandPrix() {
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

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
