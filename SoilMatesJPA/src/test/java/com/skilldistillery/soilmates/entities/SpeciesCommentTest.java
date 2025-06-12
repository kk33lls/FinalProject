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

class SpeciesCommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private SpeciesComment speciesComment;

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
		speciesComment = em.find(SpeciesComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	  speciesComment = null;
		 
	}

	 @Test
	 void test_SpeciesComment_basic_mappings() {
//		 assertEquals("soemthing", speciesComment.get);
		 assertNotNull(speciesComment);
		 assertTrue(speciesComment.isEnabled());
	 }
	 
	 @Test
	 void test_SpeciesComment_User_MTO_mapping() {
		 assertNotNull(speciesComment.getUser());
	 }
	 
	 @Test
	 void test_SpeciesComment_PlantSpecies_MTO_mapping() {
		 assertNotNull(speciesComment.getPlantSpecies());
	 }
		
	 @Test
		void test_PlantComment_OTM_self_join_replies_mapping() {
		assertNotNull(speciesComment);
		assertNotNull(speciesComment.getReplies());
		assertTrue(speciesComment.getReplies().size() > 0);
	}
}
