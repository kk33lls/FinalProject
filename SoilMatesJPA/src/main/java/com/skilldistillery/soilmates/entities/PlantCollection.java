package com.skilldistillery.soilmates.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="plant_collecion")
public class PlantCollection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private String name;
	
	private String description;
	
	@ManyToMany
	@JoinTable(name = "collection_has_plant", joinColumns = @JoinColumn(name = "plant_collection_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_plant_id"))
	private List<UserPlant> userPlantCollections;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	


	public PlantCollection() {
		super();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<UserPlant> getUserPlantCollections() {
//		return userPlantCollections;
//	}
//
//	public void setUserPlantCollections(List<UserPlant> userPlantCollections) {
//		this.userPlantCollections = userPlantCollections;
//	}

	public List<UserPlant> getUserPlantCollections() {
		return userPlantCollections;
	}

	public void setUserPlantCollections(List<UserPlant> userPlantCollections) {
		this.userPlantCollections = userPlantCollections;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantCollection other = (PlantCollection) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "PlantCollection [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", imageUrl="
				+ imageUrl + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
