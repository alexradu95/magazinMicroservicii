package aos.shopping.payload;

import javax.validation.constraints.NotNull;



public class ShoppingRequest {
	
		private String cartId;
		
	    @NotNull
	    private int productId;
	  
		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public String getCartId() {
			return cartId;
		}

		public void setCartId(String cartId) {
			this.cartId = cartId;
		}


}
