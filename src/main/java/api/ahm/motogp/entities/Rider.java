package api.ahm.motogp.entities;

import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="rider")
public class Rider {

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
    private Country country;

    public Rider(){

    }

    public Rider(int id, String name, int number, Date birthday, Country country) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.birthday = birthday;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
