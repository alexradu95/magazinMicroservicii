package aos.shopping.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CartLine {
	Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
