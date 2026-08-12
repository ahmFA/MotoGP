package api.ahm.motogp.shared.category.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public CategoryJPAEntity() {
    }

    public CategoryJPAEntity(int id) {
        this.id = id;
    }

    public CategoryJPAEntity(int id, String name) {
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
