package com.skilldistillery.soilmates.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	
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
		 user = em.find(User.class, 1);
	 }


	 @AfterEach
	 void tearDown() throws Exception {
		 em.close();
		 user = null;
	 }
	 
	 @Test
	 void test_User_basic_mappings() {
		 assertEquals("1", user.getUsername());
		 assertEquals("role", user.getRole());
		 assertTrue(user.isEnabled());
	 }
	 @Test
		void test_User_UserPlants_OTM_mapping() {
			assertNotNull(user.getUserPlants());
			assertTrue(user.getUserPlants().size() > 0);
		}
	 @Test
		void test_User_PlantCollection_OTM_mapping() {
			assertNotNull(user.getPlantCollections());
			assertTrue(user.getPlantCollections().size() > 0);
		}
	 @Test
		void test_User_PlantComment_OTM_mapping() {
			assertNotNull(user.getPlantComments());
			assertTrue(user.getPlantComments().size() > 0);
		}
	 @Test
		void test_User_SpeciesComment_OTM_mapping() {
			assertNotNull(user.getSpeciesComments());
			assertTrue(user.getSpeciesComments().size() > 0);
		}
}
