import { User } from "./user";

export class PlantCollection {
  id: number;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  name: string;
  description: string;
  userPlantCollection: UserPlantCollection[];
  user: User;

  constructor(
  id: number = 0,
  createdAt: string = '',
  updatedAt: string = '',
  imageUrl: string = '',
  name: string = '',
  description: string = '',
  userPlantCollection: UserPlantCollection[] = [],
  user: User = null,
  ){
  this.id = id;
  this.createdAt =createdAt;
  this.updatedAt =updatedAt;
  this.imageUrl =imageUrl;
  this.name =name;
  this.description = description;
  this.userPlantCollection = userPlantCollection;
  this.user =user;
  }
}
