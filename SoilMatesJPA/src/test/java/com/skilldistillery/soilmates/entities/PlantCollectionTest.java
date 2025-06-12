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

class PlantCollectionTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PlantCollection plantCollection;
	
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
		 plantCollection = em.find(PlantCollection.class, 1);
	 }


	 @AfterEach
	 void tearDown() throws Exception {
		 em.close();
		plantCollection = null;
	 }
	 
	 @Test
	 void test_PlantCollection_basic_mappings() {
//		 assertEquals("soemthing", plantComment.getUsername());
		assertNotNull(plantCollection);
		 
	 }
//	 @Test
//		void test_PlantCollection_UserPlant_MTM_mapping() {
//			assertNotNull(plantCollection);
//			assertNotNull(plantCollection.getUserPlantCollections());
//			assertTrue(plantCollection.getUserPlantCollections().size()  > 0);
//		}

	 @Test
		void test_PlantCollection_User_MTO_mapping() {
			assertNotNull(plantCollection.getUser());
		}

}
