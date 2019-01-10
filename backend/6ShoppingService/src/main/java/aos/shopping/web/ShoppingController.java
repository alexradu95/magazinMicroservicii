package aos.shopping.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import aos.security.CurrentUser;
import aos.security.UserPrincipal;
import aos.shopping.domain.Product;
import aos.shopping.domain.ShoppingCart;
import aos.shopping.dto.ShoppingCartDto;
import aos.shopping.payload.ShoppingRequest;
import aos.shopping.service.ShoppingService;

@RestController
public class ShoppingController {
	@Autowired
	ShoppingService shoppingService;
	
	@PostMapping(value = "/addToCart")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> addToCart(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ShoppingRequest shoppingRequest) {
		return shoppingService.addToCart(shoppingRequest, currentUser);
	
	}
	
	@GetMapping("/getCart/{cartId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> getCart(@PathVariable String cartId, @CurrentUser UserPrincipal currentUser) {
		return shoppingService.getCart(cartId);
	}
	
	@PostMapping(value = "/cart/checkout/{cartId}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ResponseEntity<?> checkoutCart(@RequestHeader HttpHeaders headers, @CurrentUser UserPrincipal currentUser, 
			@PathVariable String cartId) {
		shoppingService.checkout(cartId, headers);
		return new ResponseEntity<ShoppingCartDto>(HttpStatus.OK);		
	}
	
}
