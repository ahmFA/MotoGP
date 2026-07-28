package api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name="country")
public class CountryJPAEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    public CountryJPAEntity() {
    }

    public CountryJPAEntity(int id) {
        this.id = id;
    }

    public CountryJPAEntity(int id, String name) {
        this.id = id;
        this.name = name;
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
}
