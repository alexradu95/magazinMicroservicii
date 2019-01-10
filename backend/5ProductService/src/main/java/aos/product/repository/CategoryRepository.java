package aos.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import aos.product.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	 Boolean existsByName(String name);
}
