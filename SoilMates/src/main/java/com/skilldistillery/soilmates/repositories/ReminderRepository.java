package com.skilldistillery.soilmates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.soilmates.entities.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
List<Reminder> findByUserPlant_User_UsernameAndUserPlant_IdAndEnabledTrue(String username, int userPlantId);
Reminder findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(String username, int userPlantId, int reminderId);

}
