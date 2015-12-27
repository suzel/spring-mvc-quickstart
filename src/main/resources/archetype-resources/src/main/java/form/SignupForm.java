package ${package}.form;

import org.hibernate.validator.constraints.*;

import ${package}.domain.User;

public class SignupForm {

	private static final String EMAIL_NOT_BLANK_MESSAGE = "{signup.email.notblank.message}";
	private static final String EMAIL_MESSAGE = "{signup.email.message}";

	private static final String PASSWORD_NOT_BLANK_MESSAGE = "{signup.password.notblank.message}";

	@NotBlank(message = SignupForm.EMAIL_NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

	@NotBlank(message = SignupForm.PASSWORD_NOT_BLANK_MESSAGE)
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User createUser() {
		return new User(getEmail(), getPassword(), "ROLE_USER");
	}

}