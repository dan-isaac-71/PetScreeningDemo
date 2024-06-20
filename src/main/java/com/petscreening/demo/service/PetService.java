package com.petscreening.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscreening.demo.model.Pet;
import com.petscreening.demo.repository.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepository;
	
	public List<Pet> getPets() {
		return petRepository.findAll();
	}

	public Optional<Pet> getPetById(Long id) {
		return petRepository.findById(id);
	}

	public List<Pet> getPetsByBreed(String breed) {
		return petRepository.findByBreed(breed);
	}
	
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}
	
	public Pet updateTrainingLevel(Long id, Integer trainingLevel) {
		Pet existingPet = petRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No pet found with id: " + id));
		existingPet.setTrainingLevel(trainingLevel);
		return petRepository.save(existingPet);
	}
}
