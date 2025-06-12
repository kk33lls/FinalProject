import { UserPlant } from "./user-plant";

export class Reminder {
  id: number;
  createdAt: string;
  reminderDate: string;
  title: string;
  notes: string;
  completed: boolean;
  imageUrl: string;
  enabled: boolean;
  userPlant: UserPlant;
  // careType: CareType;

  constructor(
  id: number = 0,
  createdAt: string = "",
  reminderDate: string = "",
  title: string = "",
  notes: string = "",
  completed: boolean = true,
  imageUrl: string = "",
  enabled: boolean = true,
  userPlant: UserPlant = new UserPlant(),
  // careType: CareType = "",

  ){
  this.id = id;
  this.createdAt =createdAt;
  this.reminderDate = reminderDate;
  this.title = title;
  this.notes = notes;
  this.completed = completed;
  this.imageUrl = imageUrl;
  this.enabled = enabled;
  this.userPlant = userPlant;
  // this.careType = careType;



  }



}
