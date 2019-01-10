package aos.customers.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressRequest {
	
	@NotBlank
    @Size(min = 4, max = 255)
	private String street;
	
	@NotBlank
    @Size(min = 2, max = 20)
	private String city;
	
	@NotBlank
    @Size(min = 4, max = 10)
	private String zip;
	
	@NotBlank
    @Size(min = 2, max = 20)
	private String state;
	
	@NotBlank
    @Size(min = 2, max = 20)
	private String country;

	
	AddressRequest(String street, String city, String zip, String state, String country) {
		this.street = street;		
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
	}
	
	AddressRequest() {
		
	}
	
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
