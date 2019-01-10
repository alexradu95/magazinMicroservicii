package aos.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import aos.product.domain.Category;
import aos.product.domain.ProductImage;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	 //Boolean existsByName(String name);
}
