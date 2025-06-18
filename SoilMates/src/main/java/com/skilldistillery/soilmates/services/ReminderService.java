package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.Reminder;
@Service
public interface ReminderService {
	List<Reminder> getReminders(String username, int userPlantId);
	Reminder getReminder(String usernamer, int userPlantId, int reminderId);
	Reminder createReminder(String username, int userPlantId, int careTypeId, Reminder newReminder);
	boolean deleteReminder(String username, int userPlantId, int reminderId);
	Reminder udpateReminder(String username, int userPlantId, int reminderId, int careTypeId, Reminder updatedReminder);

}
