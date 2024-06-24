package com.petscreening.demo.model;

import java.util.List;

public class PetFilter {
    private Float maxWeight;
	private List<String> restrictedBreeds;
	private Boolean hasVaccination;
	private Integer	minTrainingLevel;
	
	
	public PetFilter() {}
	
	public Float getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}
	public List<String> getRestrictedBreeds() {
		return restrictedBreeds;
	}
	public void setRestrictedBreeds(List<String> restrictedBreeds) {
		this.restrictedBreeds = restrictedBreeds;
	}
	public Boolean getHasVaccination() {
		return hasVaccination;
	}
	public void setHasVaccination(Boolean hasVaccination) {
		this.hasVaccination = hasVaccination;
	}
	public Integer getMinTrainingLevel() {
		return minTrainingLevel;
	}
	public void setMinTrainingLevel(Integer minTrainingLevel) {
		this.minTrainingLevel = minTrainingLevel;
	}
}
