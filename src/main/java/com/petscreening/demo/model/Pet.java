package com.petscreening.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float weight;
	private String breed;
	private Boolean vaccinated;
	private Integer	trainingLevel;

	public Pet() {}

	public Pet(String name, Float weight, String breed, Boolean vaccinated, Integer trainingLevel) {
		setName(name);
		setWeight(weight);
		setBreed(breed);
		setVaccinated(vaccinated);
		setTrainingLevel(trainingLevel);
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Boolean getVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(Boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	public Integer getTrainingLevel() {
		return trainingLevel;
	}

	public void setTrainingLevel(Integer trainingLevel) {
		this.trainingLevel = trainingLevel;
	}

}
