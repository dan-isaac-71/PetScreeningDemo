# **Boat Rental API Demo**
Created by Dan Isaac - June, 2024


- - - - - - - - - - - -


### Overview

The following document covers the setup and usage of an API for managing and evaluating the eligibility of Pets for a business such as a Boat Rental company. 

This Application implements a GraphQL API using Spring Boot that interfaces with a PostgreSQL database. It will track specific conditions such as weight, breed, vaccination status, and training level for the pets.

The API includes endpoints which will allow the users to perform the following functioanlity:
- Add new pet records to the database
- Retrieve pet data based on various criteria
    - Under 25 lbs
    - Vaccinated
    - Not a Poodle
    - Training level 3 or above
- Validate whether a given pet meets the default eligibility requirements


- - - - - - - - - - - -


### Environment Setup

After cloning this git repository out into a working directory, perform the following steps to prepare your local environment to run the application.

**Set up a PostgreSQL database on your local system.**
Then run the following SQL statements into that database to create the appropriate user (ps_api) and schema (petscreening) which will be used by the Boat Rental Demo application:

	create user ps_api with encrypted password 'petscreening';
	create schema if not exists petscreening authorization ps_api;

**Database Cleanup**
The following SQL can be used to later remove the schema, tables, and the ps_api user from the database:

	drop owned by ps_api;
	drop user if exists ps_api;

**Create Sample Data**
If you wish for the application to create a few sample Pet records in the database:
- Edit the **DemoApplication.java** class by uncommenting the 
    - import statements
    - Autowired repository resource
    - and PostConstruct initDB() method

Upon starting the application, this code will insert three sample records into the database table.

After that initial load, remember to comment those lines back out. Otherwise each launch of the application will insert additional copies of those records into the database.

_NOTE: Pet records can also be created manually by executing the **createPet** mutation request._


- - - - - - - - - - - -


### Starting and Stopping the application

To **start** the application from a command-line terminal, perform the following steps:
- navigate to the directory where you have extracted the git repository
- run the following command from that location:

        ./mvnw spring-boot:run

To **stop** the application, 
- press Ctrl-C in the terminal where you are running the application.


- - - - - - - - - - - -


### Using the API

The application can be exercised either by using the PostMan tool, with a GraphQL request tab using the following URL:
        http://localhost:8080/graphql

Or by navigating to the following URL in a browser and using the interactive GraphIQL user interface:
        http://localhost:8080/graphiql?path=/graphql
   
   
- - - - - - - - - - - -
- - - - - - - - - - - -


### Sample Requests and Responses

Below are some sample requests, and an example of the expected results you should get for each.

_NOTE: Most of the results shown are based on using the sample Pet records mentioned above._


=============================================


#### CREATE A PET RECORD 

**REQUEST:**

    mutation CreatePet {
        createPet(
            name: "Doug"
            weight: 8.0
            breed: "Doberman"
            vaccinated: true
            trainingLevel: 1
        ) {
            id
            name
            breed
            vaccinated
            trainingLevel
            weight
        }
    }

**RESPONSE:**

    {
        "data": {
            "createPet": {
                "id": "4",
                "name": "Doug",
                "breed": "Doberman",
                "vaccinated": true,
                "trainingLevel": 1,
                "weight": 8
            }
        }
    }


=============================================


#### FETCH ALL PET RECORDS

**REQUEST:**

    query GetPets {
        getPets {
            id
            name
            weight
            breed
            vaccinated
            trainingLevel
        }
    }
    
**RESPONSE:**

    {
        "data": {
            "getPets": [
                {
                    "id": "1",
                    "name": "Archie",
                    "weight": 15,
                    "breed": "Basset Hound",
                    "vaccinated": true,
                    "trainingLevel": 5
                },
                {
                    "id": "2",
                    "name": "Barney",
                    "weight": 20,
                    "breed": "Lab",
                    "vaccinated": false,
                    "trainingLevel": 3
                },
                {
                    "id": "3",
                    "name": "Cindy",
                    "weight": 10,
                    "breed": "Poodle",
                    "vaccinated": true,
                    "trainingLevel": 10
                },
                {
                    "id": "4",
                    "name": "Doug",
                    "weight": 8,
                    "breed": "Doberman",
                    "vaccinated": true,
                    "trainingLevel": 1
                }
            ]
        }
    }


=============================================


#### GET A FILTERED LIST OF PETS (weight < 15 lbs)

**REQUEST:**

    query GetPets {
        getPets(filter: { maxWeight: 15.0 }) {
            id
            name
            weight
        }
    }

**RESPONSE:**

    {
        "data": {
            "getPets": [
                {
                    "id": "3",
                    "name": "Cindy",
                    "weight": 10
                },
                {
                    "id": "4",
                    "name": "Doug",
                    "weight": 8
                }
            ]
        }
    }


=============================================


#### GET A FILTERED LIST OF PETS (has vaccinations)

**REQUEST:**

    query GetPets {
        getPets(filter: { hasVaccination: true }) {
            id
            name
            vaccinated
        }
    }

**RESPONSE:**

    {
        "data": {
            "getPets": [
                {
                    "id": "1",
                    "name": "Archie",
                    "vaccinated": true
                },
                {
                    "id": "3",
                    "name": "Cindy",
                    "vaccinated": true
                },
                {
                    "id": "4",
                    "name": "Doug",
                    "vaccinated": true
                }
            ]
        }
    }
    
    
=============================================


#### GET A FILTERED LIST OF PETS (restrict Poodle breed)

**REQUEST:**

    query GetPets {
        getPets(filter: { restrictedBreeds: ["Poodle"] }) {
            id
            name
            breed
        }
    }

**RESPONSE:**

    {
        "data": {
            "getPets": [
                {
                    "id": "1",
                    "name": "Archie",
                    "breed": "Basset Hound"
                },
                {
                    "id": "2",
                    "name": "Barney",
                    "breed": "Lab"
                },
                {
                    "id": "4",
                    "name": "Doug",
                    "breed": "Doberman"
                }
            ]
        }
    }


=============================================


#### GET A FILTERED LIST OF PETS (Training Level 3 or greater)

**REQUEST:**

    query GetPets {
        getPets(filter: { minTrainingLevel: 3 }) {
            id
            name
            trainingLevel
        }
    }

**RESPONSE:**

    {
        "data": {
            "getPets": [
                {
                    "id": "1",
                    "name": "Archie",
                    "trainingLevel": 5
                },
                {
                    "id": "2",
                    "name": "Barney",
                    "trainingLevel": 3
                },
                {
                    "id": "3",
                    "name": "Cindy",
                    "trainingLevel": 10
                }
            ]
        }
    }


=============================================


#### GET DETAIL FOR A SINGLE PET RECORD BY ID

**REQUEST:**

    query GetPetById {
        getPetById(id: "3") {
            id
            name
            weight
            breed
            vaccinated
            trainingLevel
        }
    }

**RESPONSE:**

    {
        "data": {
            "getPetById": {
                "id": "3",
                "name": "Cindy",
                "weight": 10,
                "breed": "Poodle",
                "vaccinated": true,
                "trainingLevel": 10
            }
        }
    }


=============================================


#### TEST IF A GIVEN PET (by ID) IS ELIGIBLE BASED ON DEFAULT FILTER CRITERIA:

**REQUEST:**

    query IsEligible {
        isEligible(id: "1")
    }

**RESPONSE:**

    {
        "data": {
            "isEligible": true
        }
    }

