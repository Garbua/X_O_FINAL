package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table(name = "profile")
public class Profile implements Serializable {

	@OneToOne(mappedBy = "profile")
	private UserEntity userEntity;

	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileID;

	@Column(length = 64)
	@NotNull
	private String email;

	@Column(length = 64)
	@NotNull
	private String name;

	@Column(length = 64)
	@NotNull
	private String lastName;

	@Column(length = 64)
	@NotNull
	private String sex;

	@Column(length = 64)
	@NotNull
	private Integer age;

	@Column(length = 64)
	@NotNull
	private String city;

	@Column(length = 64)
	@NotNull
	private String fhoneNumber;

	public Profile() {
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public Long getProfileID() {
		return profileID;
	}

	public void setProfileID(Long profileID) {
		this.profileID = profileID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFhoneNumber() {
		return fhoneNumber;
	}

	public void setFhoneNumber(String fhoneNumber) {
		this.fhoneNumber = fhoneNumber;
	}
}
