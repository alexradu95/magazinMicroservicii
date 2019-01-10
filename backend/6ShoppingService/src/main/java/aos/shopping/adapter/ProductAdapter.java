package aos.shopping.adapter;

import aos.shopping.domain.Product;
import aos.shopping.dto.ProductDto;

public class ProductAdapter {
	public static Product getProduct(ProductDto productDto) {
		Product product = new Product(
				productDto.getProductId(),
				productDto.getName(),
				productDto.getDescription(),
				productDto.getPrice()
				);		
		return product;				
	}
	
	public static ProductDto getProductDto(Product product) {
		ProductDto productDto = new ProductDto(
				product.getProductId(),
				product.getName(),
				product.getDescription(),
				product.getPrice()
				);		
		return productDto;				
	}
}
