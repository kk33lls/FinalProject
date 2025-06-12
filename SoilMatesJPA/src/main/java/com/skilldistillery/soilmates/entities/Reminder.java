package com.skilldistillery.soilmates.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "reminder_date")
	private LocalDateTime reminderDate;

	private String title;
	private String notes;
	private Boolean completed;

	@Column(name = "image_url")
	private String imageUrl;

	private Boolean enabled;

	// Relationships

	@ManyToOne
	@JoinColumn(name = "user_plant_id")
	private UserPlant userPlant;

	@ManyToOne
	@JoinColumn(name = "care_type_id")
	private CareType careType;

	// constructor
	public Reminder() {
		super();

	}

	// getters/setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(LocalDateTime reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserPlant getUserPlant() {
		return userPlant;
	}

	public void setUserPlant(UserPlant userPlant) {
		this.userPlant = userPlant;
	}

	public CareType getCareType() {
		return careType;
	}

	public void setCareType(CareType careType) {
		this.careType = careType;
	}

	// hash/equals

	@Override
	public int hashCode() {
		return Objects.hash(careType, completed, createdAt, enabled, id, imageUrl, notes, reminderDate, title,
				userPlant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reminder other = (Reminder) obj;
		return Objects.equals(careType, other.careType) && Objects.equals(completed, other.completed)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(notes, other.notes) && Objects.equals(reminderDate, other.reminderDate)
				&& Objects.equals(title, other.title) && Objects.equals(userPlant, other.userPlant);
	}

	// toString

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", createdAt=" + createdAt + ", reminderDate=" + reminderDate + ", title=" + title
				+ ", notes=" + notes + ", completed=" + completed + ", imageUrl=" + imageUrl + ", enabled=" + enabled
				+ ", userPlant=" + userPlant + ", careType=" + careType + "]";
	}

}
