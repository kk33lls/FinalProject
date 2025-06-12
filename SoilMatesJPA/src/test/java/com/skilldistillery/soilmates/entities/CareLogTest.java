package com.skilldistillery.soilmates.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class CareLogTest {


	private static EntityManagerFactory emf; 
	private EntityManager em;
	private CareLog careLog; 

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
		careLog = em.find(CareLog.class, 1);
	}

	@AfterEach //executes after every test
	void tearDown() throws Exception {
		em.close();
		careLog = null; 
	}
//	@Test
//	void test_Object_entity_mapping() {
//	assertEquals ("info", CareLog.method()); 
//	
//	}
//	@Test
//	void test_Object_entity_mapping() {
//	    assertNotNull(careLog);
//	    assertEquals("info", careLog.mehtod());
//	}
//
//	@Test
//	void test_Object_has_associated_object()) {
//	    assertNotNull(careLog);
//	    assertNotNull(careLog.method());
//	    assertFalse(careLog.method().isEmpty());
//	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
