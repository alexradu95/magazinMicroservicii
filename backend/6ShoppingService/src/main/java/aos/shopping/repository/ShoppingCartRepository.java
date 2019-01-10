package aos.shopping.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import aos.shopping.domain.ShoppingCart;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

}
