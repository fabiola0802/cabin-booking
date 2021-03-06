package com.ikubinfo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ikubinfo.enums.Role;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 50, nullable = false)
	private String surname;

	@Column(length = 50, nullable = false)
	private String username;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 50, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", columnDefinition = "varchar")
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<BookingEntity> bookings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<BookingEntity> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingEntity> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

}
