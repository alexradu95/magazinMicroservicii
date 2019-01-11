package aos.customers.payload;

import javax.validation.constraints.*;

import org.springframework.core.annotation.Order;


public class CustomerSignUpRequest {
	
    @NotBlank(message = "Numele nu este completat!")
    @Size(min = 4, max = 40)
    @Order(1)
    private String firstName;
    
    @NotBlank(message = "Prenumele nu este completat!")
    @Size(min = 4, max = 40)
    @Order(2)
    private String lastName;

    @NotBlank(message = "Numele de utilizator nu este completat!")
    @Size(min = 4, max = 40)
    private String username;

    @NotBlank(message = "Adresa de email nu este completata!")
    @Size(max = 40)
    @Email
    private String email;
    
    @NotBlank(message = "Numarul de telefon nu este completat!!")
    @Size(max = 40)
    private String phone;

    @NotBlank(message = "Parola nu este completata!")
    @Size(min = 6, max = 20)
    private String password;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
