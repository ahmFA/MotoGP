package api.ahm.motogp.shared.category.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryJPAEntity, Integer> {
}
