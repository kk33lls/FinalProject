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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_plant")
public class UserPlant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name; 
	
	@Column(name="acquired_date")
	private LocalDateTime acquiredDate; 
	
	@Column(name="where_acquired")
	private String whereAcquired; 
	
	private String location; 
	
	private String notes; 
	
	private Boolean alive; 
	
	@Column(name="created_date")
	@CreationTimestamp
	private LocalDateTime createdAt; 
	
	@Column(name="updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedAt; 
	
	@Column(name="image_url")
	private String imageUrl; 
	
	private boolean enabled;
	
	@OneToMany(mappedBy="userPlant")
	private List<PlantComment> plantComments;
	
	@OneToMany(mappedBy = "userPlant")
	private List<Reminder> reminders;
	
	@OneToMany(mappedBy = "userPlant")
	private List<CareLog> careLogs;
	
	@ManyToMany
	@JoinTable(name = "collection_has_plant", joinColumns = @JoinColumn(name = "plant_collection_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_plant_id"))
	private List<UserPlant> userPlantCollections;
	
	@ManyToOne
	@JoinColumn(name="plant_species_id")
	private PlantSpecies plantSpecies;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//Constructor

	public UserPlant() {
		super();
	}

	//Getters/Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getAcquiredDate() {
		return acquiredDate;
	}

	public void setAcquiredDate(LocalDateTime acquiredDate) {
		this.acquiredDate = acquiredDate;
	}

	public String getWhereAcquired() {
		return whereAcquired;
	}

	public void setWhereAcquired(String whereAcquired) {
		this.whereAcquired = whereAcquired;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
		// Hash/Equals
	
	public List<PlantComment> getPlantComments() {
		return plantComments;
	}

	public void setPlantComments(List<PlantComment> plantComments) {
		this.plantComments = plantComments;
	}

	public List<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	public List<CareLog> getCareLogs() {
		return careLogs;
	}

	public void setCareLogs(List<CareLog> careLogs) {
		this.careLogs = careLogs;
	}

	public List<UserPlant> getUserPlantCollections() {
		return userPlantCollections;
	}

	public void setUserPlantCollections(List<UserPlant> userPlantCollections) {
		this.userPlantCollections = userPlantCollections;
	}

	public PlantSpecies getPlantSpecies() {
		return plantSpecies;
	}

	public void setPlantSpecies(PlantSpecies plantSpecies) {
		this.plantSpecies = plantSpecies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acquiredDate, alive, createdAt, enabled, id, imageUrl, location, name, notes, updatedAt,
				whereAcquired);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPlant other = (UserPlant) obj;
		return Objects.equals(acquiredDate, other.acquiredDate) && Objects.equals(alive, other.alive)
				&& Objects.equals(createdAt, other.createdAt) && enabled == other.enabled
				&& Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(notes, other.notes) && Objects.equals(updatedAt, other.updatedAt)
				&& Objects.equals(whereAcquired, other.whereAcquired);
	}

	//toString
	
	@Override
	public String toString() {
		return "UserPlant [id=" + id + ", name=" + name + ", acquiredDate=" + acquiredDate + ", whereAcquired="
				+ whereAcquired + ", location=" + location + ", notes=" + notes + ", alive=" + alive + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", imageUrl=" + imageUrl + ", enabled=" + enabled + "]";
	}
	
	
	
	
	
	
	
}
