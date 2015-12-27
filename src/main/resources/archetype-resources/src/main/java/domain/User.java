package ${package}.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TBL_USER")
@AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class User extends BaseEntityAudit {

	@Column(name = "email", unique = true)
	private String email;

	// TODO : @JsonIgnore
	@Column(name = "password")
	private String password;

	// TODO : ROLE_USER
	private String role = "ROLE_USER";

	public User() {
	}
	
	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}