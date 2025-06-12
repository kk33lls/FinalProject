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

class PlantCommentTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PlantComment plantComment;
	
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
		 plantComment = em.find(PlantComment.class, 1);
	 }


	 @AfterEach
	 void tearDown() throws Exception {
		 em.close();
		plantComment = null;
	 }
	 
	 @Test
	 void test_PlantComment_basic_mappings() {
//		 assertEquals("soemthing", plantComment.getUsername());
		 assertNotNull(plantComment);
		 assertTrue(plantComment.isEnabled());
	 }
	 
	 @Test
		void test_PlantComment_User_MTO_mapping() {
			assertNotNull(plantComment.getUser());
		}
	 
//	 @Test
//	 void test_PlantComment_UserPlant_MTO_mapping() {
//		 assertNotNull(plantComment.getUserPlant());
//	 }
	 
//	 @Test
//		void test_PlantComment_OTM_self_join_replies_mapping() {
//			assertNotNull(plantComment);
//			assertNotNull(plantComment.getReplies());
//		}
	 

}
