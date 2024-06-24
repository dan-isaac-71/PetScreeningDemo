package com.petscreening.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.petscreening.demo.model.Pet;
import com.petscreening.demo.model.PetFilter;
import com.petscreening.demo.repository.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepository;
	
	
	private Specification<Pet> generateSpecificationFromFilter(PetFilter filter) {
		Specification<Pet> specification = Specification.where(null);
		
		if(filter != null) {
			if(filter.getWeight() != null) {
				specification = specification.and((root, query, criteriaBuilder) 
						-> criteriaBuilder.lessThan(root.get("weight"), filter.getWeight()));
			}

			if(filter.getVaccinated() != null) {
				specification = specification.and((root, query, criteriaBuilder) 
						-> criteriaBuilder.equal(root.get("vaccinated"), filter.getVaccinated()));
			}

			if(filter.getBreed() != null) {
				specification = specification.and((root, query, criteriaBuilder) 
						-> criteriaBuilder.notEqual(root.get("breed"), filter.getBreed()));
			}

			if(filter.getTrainingLevel() != null) {
				specification = specification.and((root, query, criteriaBuilder) 
						-> criteriaBuilder.greaterThanOrEqualTo(root.get("trainingLevel"), filter.getTrainingLevel()));
			}
		}
		return specification;
	}
	
	private Specification<Pet> addIdToSpecification(Long id, Specification<Pet> specification) {
		if(id != null) {
			specification = specification.and((root, query, criteriaBuilder) 
					-> criteriaBuilder.equal(root.get("id"), id));
		}
		return specification;
	}
	
	public Boolean isEligible(Long id, PetFilter filter) {
		Specification<Pet> specification = generateSpecificationFromFilter(filter);
		specification = addIdToSpecification(id, specification);
		return ! petRepository.findAll(specification).isEmpty();
	}
	
	public List<Pet> getPets(PetFilter filter) {
		Specification<Pet> specification = generateSpecificationFromFilter(filter);
		return petRepository.findAll(specification);
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
