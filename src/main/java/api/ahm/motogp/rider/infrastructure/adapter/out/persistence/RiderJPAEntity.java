package api.ahm.motogp.rider.infrastructure.adapter.out.persistence;

import api.ahm.motogp.shared.country.infrastructure.adapter.out.persistence.CountryJPAEntity;
import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="rider",
uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        })
public class RiderJPAEntity {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="number")
    private int number;
    @Column(name="birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryJPAEntity country;

    @Column(name="active")
    private boolean active;

    public RiderJPAEntity(){

    }

    public RiderJPAEntity(int id, String name, int number, Date birthday, CountryJPAEntity country, boolean active) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.birthday = birthday;
        this.country = country;
        this.active = active;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CountryJPAEntity getCountry() {
        return country;
    }

    public void setCountry(CountryJPAEntity country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

