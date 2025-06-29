package com.skilldistillery.soilmates.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "plant_species")
public class PlantSpecies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String genus;
	
	private String species;
	
	@Column(name = "common_names")
	private String commonNames;
	
	private String description;
	
	@Column(name = "light_requirements")
	private String lightRequirements;
	
	@Column(name = "water_frequency")
	private String waterFrequency;
	
	@Column(name = "native_to")
	private String nativeTo;
	
	@Column(name = "temperature_range")
	private String temperatureRange;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="care_difficulty_id")
	private CareDifficulty careDifficulty;
	
	@OneToMany(mappedBy="plantSpecies")
	@JsonIgnore
	private List<UserPlant> userPlants;
	
	@OneToMany(mappedBy="plantSpecies")
	@JsonIgnore
	private List<SpeciesComment> speciesComments;


	// constructors
	public PlantSpecies() {
		super();
	}

	// Getters/Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getCommonNames() {
		return commonNames;
	}

	public void setCommonNames(String commonNames) {
		this.commonNames = commonNames;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLightRequirements() {
		return lightRequirements;
	}

	public void setLightRequirements(String lightRequirements) {
		this.lightRequirements = lightRequirements;
	}

	public String getWaterFrequency() {
		return waterFrequency;
	}

	public void setWaterFrequency(String waterFrequency) {
		this.waterFrequency = waterFrequency;
	}

	public String getNativeTo() {
		return nativeTo;
	}

	public void setNativeTo(String nativeTo) {
		this.nativeTo = nativeTo;
	}

	public String getTemperatureRange() {
		return temperatureRange;
	}

	public void setTemperatureRange(String temperatureRange) {
		this.temperatureRange = temperatureRange;
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

	public CareDifficulty getCareDifficulty() {
		return careDifficulty;
	}
	
	public void setCareDifficulty(CareDifficulty careDifficulty) {
		this.careDifficulty = careDifficulty;
	}
	
	public List<UserPlant> getUserPlants() {
		return userPlants;
	}
	
	public void setUserPlants(List<UserPlant> userPlants) {
		this.userPlants = userPlants;
	}
	
	public List<SpeciesComment> getSpeciesComments() {
		return speciesComments;
	}
	
	public void setSpeciesComments(List<SpeciesComment> speciesComments) {
		this.speciesComments = speciesComments;
	}
	// hash/equals
	@Override
	public int hashCode() {
		return Objects.hash(commonNames, description, enabled, genus, id, imageUrl, lightRequirements, nativeTo,
				species, temperatureRange, waterFrequency);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantSpecies other = (PlantSpecies) obj;
		return Objects.equals(commonNames, other.commonNames) && Objects.equals(description, other.description)
				&& enabled == other.enabled && Objects.equals(genus, other.genus) && Objects.equals(id, other.id)
				&& Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(lightRequirements, other.lightRequirements)
				&& Objects.equals(nativeTo, other.nativeTo) && Objects.equals(species, other.species)
				&& Objects.equals(temperatureRange, other.temperatureRange)
				&& Objects.equals(waterFrequency, other.waterFrequency);
	}

	// toString
	@Override
	public String toString() {
		return "PlantSpecies [id=" + id + ", genus=" + genus + ", species=" + species + ", commonNames=" + commonNames
				+ ", description=" + description + ", lightRequirements=" + lightRequirements + ", waterFrequency="
				+ waterFrequency + ", nativeTo=" + nativeTo + ", temperatureRange=" + temperatureRange + ", imageUrl="
				+ imageUrl + ", enabled=" + enabled + "]";
	}

}
