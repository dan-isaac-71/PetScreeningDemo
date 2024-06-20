package com.petscreening.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.petscreening.demo.model.Pet;
//import com.petscreening.demo.repository.PetRepository;
//
//import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
	
//	@Autowired
//	private PetRepository petRepository;
//	
//	@PostConstruct
//	public void initDB() {
//		List<Pet> initialPets = Stream.of(
//			new Pet("Archie", 15.0f, "Basset Hound", true, 5),
//			new Pet("Barney", 20.0f, "Lab", false, 3),
//			new Pet("Cindy", 10.0f, "Poodle", true, 10)
//		).collect(Collectors.toList());
//		petRepository.saveAll(initialPets);
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
