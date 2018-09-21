package entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	@NotNull(message = "поле не может быть пустым!")
	@Size(min = 3, max = 30, message = "от 3 до 30 символов!")
	private String login;

	@Column
	@NotNull(message = "поле не может быть пустым!")
	@Size(min = 6, max = 30, message = "от 6 до 30 символов!")
	@Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Некорректный Email!!")
	private String email;

	@Column(name = "pass")
	@NotNull(message = "поле не может быть пустым!")
	@Size(min = 6 , max = 30, message = "от 6 до 30 символов!")
	private String password;

	@Column(name = "firstName")
	@NotNull(message = "поле не может быть пустым!")
	@Size(min = 4, max = 30, message = "от 6 до 30 символов!")
	private String firstName;

	@Column(name = "lastName")
	@NotNull(message = "поле не может быть пустым!")
	@Size(min = 4, max = 30,message = "от 6 до 30 символов!")
	private String lastName;



	public UserEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", login='" + login + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
