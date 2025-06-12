package com.skilldistillery.soilmates.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="species_comment")
public class SpeciesComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	private String comment;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="plant_species_id")
	private PlantSpecies plantSpecies;
	
	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private SpeciesComment reply;

	@OneToMany(mappedBy = "reply")
	private List<SpeciesComment> replies;
	

	public SpeciesComment() {
		super();
	}

	@Override
	public String toString() {
		return "SpeciesComment [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", comment="
				+ comment + ", imageUrl=" + imageUrl + ", enabled=" + enabled + "]";
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PlantSpecies getPlantSpecies() {
		return plantSpecies;
	}

	public void setPlantSpecies(PlantSpecies plantSpeciesComment) {
		this.plantSpecies = plantSpeciesComment;
	}

	public SpeciesComment getReply() {
		return reply;
	}

	public void setReply(SpeciesComment reply) {
		this.reply = reply;
	}

	public List<SpeciesComment> getReplies() {
		return replies;
	}

	public void setReplies(List<SpeciesComment> replies) {
		this.replies = replies;
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
		SpeciesComment other = (SpeciesComment) obj;
		return id == other.id;
	}

	public SpeciesComment(int id, LocalDateTime createdAt, LocalDateTime updatedAt, String comment, String imageUrl,
			boolean enabled) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.comment = comment;
		this.imageUrl = imageUrl;
		this.enabled = enabled;
	}
	
}
