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

class CareTypeTest {


	private static EntityManagerFactory emf; 
	private EntityManager em;
	private CareType careType; 

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
		careType = em.find(CareType.class, 1);
	}

	@AfterEach //executes after every test
	void tearDown() throws Exception {
		em.close();
		careType = null; 
	}
	@Test
	void test_CareType_mapping() {
	assertNotNull (careType); 
	}
	@Test
	void test_CareType_Reminder_OTM_mapping() {
		assertNotNull(careType.getReminders());
		assertTrue(careType.getReminders().size() > 0);
	}
	@Test
	void test_CareType_CareLog_OTM_mapping() {
		assertNotNull(careType.getCareLogs());
		assertTrue(careType.getCareLogs().size() > 0);
	}


}

