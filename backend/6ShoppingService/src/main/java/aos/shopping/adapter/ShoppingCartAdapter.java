package aos.shopping.adapter;

import aos.shopping.adapter.ProductAdapter;
import aos.shopping.domain.CartLine;
import aos.shopping.domain.ShoppingCart;
import aos.shopping.dto.CartLineDto;
import aos.shopping.dto.ShoppingCartDto;

public class ShoppingCartAdapter {

	public static ShoppingCartDto getShoppingCartDto(ShoppingCart shoppingCart) {
		ShoppingCartDto shoppingCartDTO = new ShoppingCartDto();
		shoppingCartDTO.setCartid(shoppingCart.getCartId());
		for (CartLine cartLine : shoppingCart.getCartlineList()) {
			CartLineDto cartLineDTO = new CartLineDto();
			cartLineDTO.setProduct(ProductAdapter.getProductDto(cartLine.getProduct()));
			shoppingCartDTO.addCartLine(cartLineDTO);
		}
		return shoppingCartDTO;
	}

}
