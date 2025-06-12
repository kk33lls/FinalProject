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

class ReminderTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Reminder reminder;

	@BeforeAll // executes once at beginning
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
		reminder = em.find(Reminder.class, 1);
	}

	@AfterEach // executes after every test
	void tearDown() throws Exception {
		em.close();
		reminder = null;
	}

	@Test
	void test_Reminder_UserPlant_MTO_mapping() {
		assertNotNull(reminder.getUserPlant());
	}

	@Test
	void test_Reminder_CareType_MTO_mapping() {
		assertNotNull(reminder.getCareType());
	}

	@Test
	void test_Reminder_entity_mapping() {
		assertNotNull(reminder);

	}
}
