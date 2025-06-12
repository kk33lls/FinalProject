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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="plant_comment")
public class PlantComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private String comment;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

//	@ManyToOne
//	@JoinColumn(name = "user_plant_id")
//	private UserPlant userPlant;
	
	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private PlantComment reply;

	@OneToMany(mappedBy = "reply")
	private List<PlantComment> replies;
	
	public PlantComment() {
		super();
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

//	public UserPlant getUserPlant() {
//		return userPlant;
//	}
//
//	public void setUserPlant(UserPlant userPlant) {
//		this.userPlant = userPlant;
//	}

	public PlantComment getReply() {
		return reply;
	}

	public void setReply(PlantComment reply) {
		this.reply = reply;
	}

	public List<PlantComment> getReplies() {
		return replies;
	}

	public void setReplies(List<PlantComment> replies) {
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
		PlantComment other = (PlantComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "PlantComment [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", comment="
				+ comment + ", imageUrl=" + imageUrl + ", enabled=" + enabled + "]";
	}
	
	
	
	

}
