package com.petscreening.demo.model;

public class PetFilter {
    private Float weight;
	private String breed;
	private Boolean vaccinated;
	private Integer	trainingLevel;
	
	
	public PetFilter() {}
	
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
