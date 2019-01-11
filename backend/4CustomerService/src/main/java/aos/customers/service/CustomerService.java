package aos.customers.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import aos.customers.domain.Address;
import aos.customers.domain.Customer;
import aos.customers.dto.AccountDto;
import aos.customers.integration.OAuth2Proxy;
import aos.customers.payload.AccountRequest;
import aos.customers.payload.AddressRequest;
import aos.customers.payload.ApiResponse;
import aos.customers.payload.CustomerSignUpRequest;
import aos.customers.payload.CustomerUpdateRequest;
import aos.customers.repository.CustomerRepository;
import aos.security.UserPrincipal;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OAuth2Proxy oauth2Proxy;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> createAccount(CustomerSignUpRequest customerSignUpRequest) {

		if (customerRepository.existsByEmail(customerSignUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Adresa de email este deja folosita!"), HttpStatus.BAD_REQUEST);
		}

		AccountRequest account = new AccountRequest(customerSignUpRequest.getUsername(),
				customerSignUpRequest.getEmail(),
				customerSignUpRequest.getFirstName() + " " + customerSignUpRequest.getLastName(),
				customerSignUpRequest.getPassword());
		AccountDto responseEntity = oauth2Proxy.registerUser(account);
		System.out.println("test");
		if (responseEntity.isSuccess()) {
			Customer customer = new Customer(customerSignUpRequest.getFirstName(), customerSignUpRequest.getLastName(),
					customerSignUpRequest.getEmail(), customerSignUpRequest.getPhone());

			Customer result = customerRepository.save(customer);

			URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/customer/" + result.getCustomerId()).buildAndExpand(result.getCustomerId()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "Contul este creeat cu succes!"));
		} else {
			return new ResponseEntity(
					new ApiResponse(false,
							"A aparut o problema!"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> updateCustomert(CustomerUpdateRequest customerUpdateRequest, UserPrincipal currentUser) {
		Customer customer = customerRepository.findByEmail(currentUser.getEmail());
		Customer updateResult = null;
		if (customer == null) {
			return new ResponseEntity(new ApiResponse(false, "Clientul nu exista!"), HttpStatus.BAD_REQUEST);
		}

		customer.setFirstname(customerUpdateRequest.getFirstname());
		customer.setLastname(customerUpdateRequest.getLastname());
		customer.setPhone(customerUpdateRequest.getPhone());

		updateResult = customerRepository.save(customer);

		if (updateResult != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/customer/" + updateResult.getCustomerId()).buildAndExpand(updateResult.getCustomerId())
					.toUri();

			return ResponseEntity.created(location)
					.body(new ApiResponse(true, "Update cu succes!"));

		} else {
			return new ResponseEntity(
					new ApiResponse(false, "A aparut o problema!"),
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> addAddress(AddressRequest addressRequest, UserPrincipal currentUser) {

		Customer customer = customerRepository.findByEmail(currentUser.getEmail());

		Address address = new Address(addressRequest.getStreet(), addressRequest.getCity(),
				addressRequest.getZip(), addressRequest.getState(), addressRequest.getCountry());
		customer.setAdress(address); 
		address.setCustomer(customer);
		customerRepository.save(customer);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer").buildAndExpand().toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Adresa adaugata cu succes!"));
	}

	public ResponseEntity<?> getCustomer(UserPrincipal currentUser) {

		Customer customer = customerRepository.findByEmail(currentUser.getEmail());
		if (customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Clientul nu exista!"),
				HttpStatus.BAD_REQUEST);

	}
}
