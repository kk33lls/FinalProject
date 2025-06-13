package com.skilldistillery.soilmates.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "care_type")
public class CareType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;

	// Relationships

	@OneToMany(mappedBy = "careType")
	@JsonIgnore
	private List<CareLog> careLogs;

	@OneToMany(mappedBy = "careType")
	@JsonIgnore
	private List<Reminder> reminders;

	// constructors
	public CareType() {
		super();
	}

	// Getters/Setters
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<CareLog> getCareLogs() {
		return careLogs;
	}

	public void setCareLogs(List<CareLog> careLogs) {
		this.careLogs = careLogs;
	}

	public List<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	// hashCode
	@Override
	public int hashCode() {
		return Objects.hash(careLogs, description, id, imageUrl, name, reminders);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CareType other = (CareType) obj;
		return Objects.equals(careLogs, other.careLogs) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(name, other.name) && Objects.equals(reminders, other.reminders);
	}

	// toString
	@Override
	public String toString() {
		return "CareType [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", careLogs=" + careLogs + ", reminders=" + reminders + "]";
	}

}
