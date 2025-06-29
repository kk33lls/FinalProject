package com.skilldistillery.soilmates.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class CareDifficultyTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private CareDifficulty careDifficulty;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SoilMatesJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
		emf = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		careDifficulty = em.find(CareDifficulty.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		em = null;
	}

	@Test
	void test_CareDifficulty_entity_mapping() {
		assertEquals("Foolproof", careDifficulty.getName());

	}

	@Test
	void test__UserPlants_OTM_mapping() {
		assertNotNull(careDifficulty.getPlantSpeciesCareDifficulties());
		assertTrue(careDifficulty.getPlantSpeciesCareDifficulties().size() > 0);
	}

}
