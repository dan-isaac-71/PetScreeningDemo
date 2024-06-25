package com.petscreening.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.petscreening.demo.model.Pet;
import com.petscreening.demo.model.PetFilter;
import com.petscreening.demo.service.PetService;

@Controller
public class PetController {
	@Autowired
	private PetService petService;

	@QueryMapping
	public Boolean isEligible(@Argument Long id) {
		PetFilter filter = new PetFilter();
		filter.setMaxWeight(25.0f);
		filter.setHasVaccination(true);
		filter.setRestrictedBreeds(Arrays.asList("Poodle"));
		filter.setMinTrainingLevel(3);
		
		return petService.isEligible(id, filter);
	}
	
	@QueryMapping
	public List<Pet> getPets(@Argument PetFilter filter) {
		return petService.getPets(filter);
	}

	@QueryMapping
	public Optional<Pet> getPetById(@Argument Long id) {
		return petService.getPetById(id);
	}

	@MutationMapping
	public Pet createPet(@Argument String name, @Argument Float weight, @Argument String breed, 
			@Argument Boolean vaccinated, @Argument Integer trainingLevel) {
		if(trainingLevel > 10 || trainingLevel < 1) {
			return null;
		}
		Pet pet = new Pet();
		pet.setName(name);
		pet.setWeight(weight);
		pet.setBreed(breed);
		pet.setVaccinated(vaccinated);
		pet.setTrainingLevel(trainingLevel);
		return petService.save(pet);
	}
}
