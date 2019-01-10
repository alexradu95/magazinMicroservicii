package aos.product.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aos.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findById(String id);
}
