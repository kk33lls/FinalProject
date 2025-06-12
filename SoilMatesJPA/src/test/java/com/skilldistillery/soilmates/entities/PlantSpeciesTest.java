package com.skilldistillery.soilmates.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class PlantSpeciesTest {


		private static EntityManagerFactory emf; 
		private EntityManager em;
		private PlantSpecies plantSpecies; 

		@BeforeAll //executes once at beginning
		static void setUpBeforeClass() throws Exception {
			emf = Persistence.createEntityManagerFactory("SoilMatesJPA"); 
		}

		@AfterAll // executes once after all
		static void tearDownAfterClass() throws Exception {
			emf.close();
		}

		@BeforeEach // executes before every test
		void setUp() throws Exception {
			em = emf.createEntityManager(); 
			plantSpecies = em.find(PlantSpecies.class, 1);
		}

		@AfterEach //executes after every test
		void tearDown() throws Exception {
			em.close();
			plantSpecies = null; 
		}
		@Test
		void test_PlantSpecies_entity_mapping() {
			assertNotNull(plantSpecies);
		}
		
		@Test
		void test_PlantSpecies_UserPlants_OTM_mapping() {
			assertNotNull(plantSpecies.getUserPlants());
			assertTrue(plantSpecies.getUserPlants().size() > 0);
		}
		@Test
		void test_PlantSpecies_SpeciesComment_OTM_mapping() {
			assertNotNull(plantSpecies.getSpeciesComments());
			assertTrue(plantSpecies.getSpeciesComments().size() > 0);
		}
		
		@Test
		void test_PlantSpecies_CareDifficulty_MTO_mapping() {
			assertNotNull(plantSpecies.getCareDifficulty());
		}
		
		
		



}
