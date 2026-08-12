package api.ahm.motogp.shared.constructor.infrastructure.adapter.out.persistance;

import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.CountryJPAEntity;
import jakarta.persistence.*;

@Entity
@Table(name="constructor")
public class ConstructorJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryJPAEntity country;

    public ConstructorJPAEntity() {
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

    public CountryJPAEntity getCountry() {
        return country;
    }

    public void setCountry(CountryJPAEntity country) {
        this.country = country;
    }
}
