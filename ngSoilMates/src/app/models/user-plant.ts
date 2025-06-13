import { CareLog } from "./care-log";
import { PlantCollection } from "./plant-collection";
import { PlantComment } from "./plant-comment";
import { PlantSpecies } from "./plant-species";
import { Reminder } from "./reminder";
import { User } from "./user";

export class UserPlant {
  id: number;
  name: string;
  acquiredDate: string;
  whereAcquired: string;
  location: string;
  notes: string;
  alive: boolean;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  enabled: boolean;
  plantComments: PlantComment[];
  reminders: Reminder[];
  careLogs: CareLog[];
  plantCollections: PlantCollection[];
  plantSpecies: PlantSpecies;
  user: User;

  constructor(
  id: number = 0,
  name: string = '',
  acquiredDate: string = '',
  whereAcquired: string = '',
  location: string = '',
  notes: string = '',
  alive: boolean = true,
  createdAt: string = '',
  updatedAt: string = '',
  imageUrl: string = '',
  enabled: boolean = true,
  plantComments: PlantComment[] = [],
  reminders: Reminder[] = [],
  careLogs: CareLog[] = [],
  plantCollections: PlantCollection[] = [],
  plantSpecies: PlantSpecies = new PlantSpecies(),
  user: User = new User(),

  ){
  this.id = id,
  this.name = name;
  this.acquiredDate = acquiredDate;
  this.whereAcquired = whereAcquired;
  this.location = location;
  this.notes = notes;
  this.alive = alive;
  this.createdAt = createdAt;
  this.updatedAt = updatedAt;
  this.imageUrl = imageUrl;
  this.enabled = enabled;
  this.plantComments = plantComments;
  this.reminders = reminders;
  this.careLogs = careLogs;
  this.plantCollections = plantCollections;
  this.plantSpecies = plantSpecies;
  this.user = user;
  }

}
