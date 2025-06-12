package com.skilldistillery.soilmates.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class UserPlantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserPlant userPlant;

	@BeforeAll // executes once at beginning
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
		userPlant = em.find(UserPlant.class, 1);
	}

	@AfterEach // executes after every test
	void tearDown() throws Exception {
		em.close();
		userPlant = null;
	}

	@Test
	void test_UserPlant_entity_mapping() {
		assertNotNull(userPlant);

	}
	@Test
	void test_UserPlant_PlantComment_OTM_mapping() {
		assertNotNull(userPlant.getPlantComments());
		assertTrue(userPlant.getPlantComments().size() > 0);
	}
	@Test
	void test_UserPlant_Reminder_OTM_mapping() {
		assertNotNull(userPlant.getReminders());
		assertTrue(userPlant.getReminders().size() > 0);
	}
	@Test
	void test_UserPlant_CareLog_OTM_mapping() {
		assertNotNull(userPlant.getCareLogs());
		assertTrue(userPlant.getCareLogs().size() > 0);
	}
	
	@Test
	void test_UserPlant_PlantCollection_MTM_mapping() {
		assertNotNull(userPlant.getUserPlantCollections());
		assertTrue(userPlant.getUserPlantCollections().size() > 0);
	}
	
	@Test
	void test_UserPlant_User_MTO_mapping() {
		assertNotNull(userPlant.getUser());
	}
	@Test
	void test_UserPlant_PlantSpecies_MTO_mapping() {
		assertNotNull(userPlant.getPlantSpecies());
	}
}
