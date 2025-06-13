import { User } from "./user";
import { UserPlant } from "./user-plant";

export class PlantCollection {
  id: number;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  name: string;
  description: string;
  userPlants: UserPlant[];
  user: User;

  constructor(
  id: number = 0,
  createdAt: string = '',
  updatedAt: string = '',
  imageUrl: string = '',
  name: string = '',
  description: string = '',
  userPlants: UserPlant[] = [],
  user: User = new User(),
  ){
  this.id = id;
  this.createdAt =createdAt;
  this.updatedAt =updatedAt;
  this.imageUrl =imageUrl;
  this.name =name;
  this.description = description;
  this.userPlants = userPlants;
  this.user =user;
  }
}
