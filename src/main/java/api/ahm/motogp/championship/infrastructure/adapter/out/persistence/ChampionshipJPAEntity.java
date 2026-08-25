package api.ahm.motogp.championship.infrastructure.adapter.out.persistence;

import api.ahm.motogp.shared.category.infrastructure.adapter.out.persistence.CategoryJPAEntity;
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
public class ChampionshipJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryJPAEntity category;

    @Column(name = "year")
    private int year;

    public ChampionshipJPAEntity() {
    }

    public ChampionshipJPAEntity(int id, CategoryJPAEntity category, int year) {
        this.id = id;
        this.category = category;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryJPAEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryJPAEntity category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
