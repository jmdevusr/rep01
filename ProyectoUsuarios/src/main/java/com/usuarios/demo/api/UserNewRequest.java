package com.usuarios.demo.api;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserNewRequest {

	
	@NotNull(message="First name is required")
	@Size(min=2, max=25, message="First name must be between {min} and {max} characters")
	private String firstName;
	
	
	@NotNull(message="Last name is required")
	@Size(min=2, max=25, message="Last name must be between {min} and {max} characters")
	private String lastName;
	
	
	
	@NotNull(message="Date of birth is required")
	@Pattern(regexp="^\\d{2}-\\d{2}-\\d{4}$", message="dateOfBirth must match the format DD-MM-YYYY")
	String dateOfBirth;
	
	


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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
	
	
}
