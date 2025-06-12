package com.skilldistillery.soilmates.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class CareTypeTest {


	private static EntityManagerFactory emf; 
	private EntityManager em;
	private CareType careType; 

	@BeforeAll //executes once at beginning
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SoilMates"); 
	}

	@AfterAll // executes once after all
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach // executes before every test
	void setUp() throws Exception {
		em = emf.createEntityManager(); 
		careType = em.find(CareType.class, 1);
	}

	@AfterEach //executes after every test
	void tearDown() throws Exception {
		em.close();
		careType = null; 
	}
//	@Test
//	void test_Object_entity_mapping() {
//	assertEquals ("info", CareType.method()); 
//	
//	}
//	@Test
//	void test_Object_entity_mapping() {
//	    assertNotNull(careType);
//	    assertEquals("info", careType.mehtod());
//	}
//
//	@Test
//	void test_Object_has_associated_object()) {
//	    assertNotNull(userPlant);
//	    assertNotNull(userPlant.method());
//	    assertFalse(userPlant.method().isEmpty());
//	}

}

