package aos.shopping.domain;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShoppingCart {
	@Id
	String cartId;

	Long customerId;

	Boolean ordered = false;

	ArrayList<CartLine> cartlineList = new ArrayList<CartLine>();

	public void addToCart(Product product) {
		CartLine cline = new CartLine();
		cline.setProduct(product);
		cartlineList.add(cline);
	}

	public void removeFromCart(Product product) {
		Iterator<CartLine> iter = cartlineList.iterator();
		while (iter.hasNext()) {
			CartLine cline = iter.next();
			if (cline.getProduct().getProductId().equals(product.getProductId())) {
				iter.remove();
			}
		}
	}
	
	public void emptyCart() {
		Iterator<CartLine> iter = cartlineList.iterator();
		while (iter.hasNext()) {
			iter.remove();
		}
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public ArrayList<CartLine> getCartlineList() {
		return cartlineList;
	}

	public void setCartlineList(ArrayList<CartLine> cartlineList) {
		this.cartlineList = cartlineList;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Boolean getOrdered() {
		return ordered;
	}

	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}

}
