package com.skilldistillery.soilmates.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private boolean enabled;
	
	private String role;
	@Column(name="first_name")
	private String firstName; 
	@Column(name="last_name")
	private String lastName; 
	private String email; 
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt; 
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt; 
	@Column(name="image_url")
	private String imageUrl; 
	
	private String biography; 
	
	
//mapping
	
	
	
//Constructor
	
	public User() {
		super();
		
	}

	
	//getters/setters

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}

	
	//hash/equals

	@Override
	public int hashCode() {
		return Objects.hash(biography, createdAt, email, enabled, firstName, id, imageUrl, lastName, password, role,
				updatedAt, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(biography, other.biography) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && enabled == other.enabled
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(username, other.username);
	}

	
	//toString

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", imageUrl=" + imageUrl + ", biography="
				+ biography + "]";
	}
	
	
	
	
	
}

	