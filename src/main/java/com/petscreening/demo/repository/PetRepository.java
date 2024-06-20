package com.petscreening.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petscreening.demo.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

	List<Pet> findByBreed(String breed);
}
