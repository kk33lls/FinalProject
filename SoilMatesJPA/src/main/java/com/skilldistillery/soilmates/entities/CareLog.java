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
import jakarta.persistence.Table;

@Entity
@Table(name = "care_log")
public class CareLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String notes;

	@Column(name = "care_date")
	private LocalDateTime careDate;

	@Column(name = "image_url")
	private String imageUrl;

	private Boolean enabled;
	
	//relationships
	
	@ManyToOne
    @JoinColumn(name = "user_plant_id")
    private UserPlant userPlant;
    
    @ManyToOne
    @JoinColumn(name = "care_type_id")
    private CareType careType;

    //constructors
    
	public CareLog() {
		super();
	}

	//getters/ setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getCareDate() {
		return careDate;
	}

	public void setCareDate(LocalDateTime careDate) {
		this.careDate = careDate;
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

	//Hash/Equals
	
	@Override
	public int hashCode() {
		return Objects.hash(careDate, careType, enabled, id, imageUrl, notes, userPlant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CareLog other = (CareLog) obj;
		return Objects.equals(careDate, other.careDate) && Objects.equals(careType, other.careType)
				&& Objects.equals(enabled, other.enabled) && Objects.equals(id, other.id)
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(notes, other.notes)
				&& Objects.equals(userPlant, other.userPlant);
	}

	//toString
	
	@Override
	public String toString() {
		return "CareLog [id=" + id + ", notes=" + notes + ", careDate=" + careDate + ", imageUrl=" + imageUrl
				+ ", enabled=" + enabled + "]";
	}

	
    
    
}
