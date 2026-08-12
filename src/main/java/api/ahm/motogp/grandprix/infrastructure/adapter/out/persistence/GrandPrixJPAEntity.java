package api.ahm.motogp.grandprix.infrastructure.adapter.out.persistence;

import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.CountryJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "grandprix")
public class GrandPrixJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "circuit_name")
    private String circuitName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryJPAEntity country;

    public GrandPrixJPAEntity() {
    }

    public GrandPrixJPAEntity(int id, String name, String circuitName, CountryJPAEntity country) {
        this.id = id;
        this.name = name;
        this.circuitName = circuitName;
        this.country = country;
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

    public CountryJPAEntity getCountry() {
        return country;
    }

    public void setCountry(CountryJPAEntity country) {
        this.country = country;
    }
}
