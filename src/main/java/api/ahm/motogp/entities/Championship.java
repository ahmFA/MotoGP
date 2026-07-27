package api.ahm.motogp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "championship",
            uniqueConstraints = {
            @UniqueConstraint(
                    name = "uk_championship_year_category",
                    columnNames = {
                            "category_id",
                            "year"
                    }
            )
        }
)
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "year")
    private int year;

    public Championship() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
