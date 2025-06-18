package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareType;
import com.skilldistillery.soilmates.entities.Reminder;
import com.skilldistillery.soilmates.entities.UserPlant;
import com.skilldistillery.soilmates.repositories.CareTypeRepository;
import com.skilldistillery.soilmates.repositories.ReminderRepository;
import com.skilldistillery.soilmates.repositories.UserPlantRepository;

@Service
public class ReminderServiceImpl implements ReminderService {

	@Autowired
	ReminderRepository reminderRepo;

	@Autowired
	UserPlantRepository userPlantRepo;
	
	@Autowired
	CareTypeRepository careTypeRepo;

	@Override
	public List<Reminder> getReminders(String username) {
		return reminderRepo.findByUserPlant_User_UsernameAndEnabledTrue(username);
	}

	@Override
	public Reminder getReminder(String usernamer, int userPlantId, int reminderId) {
		return reminderRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(usernamer, userPlantId,
				reminderId);
	}

	@Override
	public Reminder createReminder(String username, int userPlantId, int careTypeId, Reminder newReminder) {
		UserPlant userPlant = userPlantRepo.findByIdAndUser_UsernameAndEnabledTrue(userPlantId, username);
		CareType careType = careTypeRepo.findById(careTypeId).orElse(null);
		if (userPlant != null && careType != null) {
			newReminder.setUserPlant(userPlant);
			newReminder.setCareType(careType);
			newReminder.setEnabled(true);
			newReminder.setCompleted(false);
			return reminderRepo.saveAndFlush(newReminder);
		}
		return null;
	}

	@Override
	public boolean deleteReminder(String username, int userPlantId, int reminderId) {
		Reminder reminderToDelete = reminderRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(username, userPlantId, reminderId);
		
		if(reminderToDelete != null) {
			reminderToDelete.setEnabled(false);
			reminderRepo.saveAndFlush(reminderToDelete);
			return true;
		}
		return false;
	}

	@Override
	public Reminder udpateReminder(String username, int userPlantId, int reminderId, int careTypeId, Reminder updatedReminder) {
		Reminder managedReminder = reminderRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(username, userPlantId, reminderId);
		CareType careType = careTypeRepo.findById(careTypeId).orElse(null);
		UserPlant userPlant = userPlantRepo.findByIdAndUser_UsernameAndEnabledTrue(userPlantId, username);
		if(managedReminder != null && careType != null) {
			managedReminder.setCompleted(updatedReminder.getCompleted());
			managedReminder.setCareType(careType);
			managedReminder.setNotes(updatedReminder.getNotes());
			managedReminder.setReminderDate(updatedReminder.getReminderDate());
			managedReminder.setTitle(updatedReminder.getTitle());
			managedReminder.setUserPlant(userPlant);
			
			reminderRepo.saveAndFlush(managedReminder);
			
			return managedReminder;
		}
		return null;
	}

}
