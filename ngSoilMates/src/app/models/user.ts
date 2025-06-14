import { UserPlant } from './user-plant';
  export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  email: string;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  userPlants: UserPlant[];

  constructor(
    id: number = 0,
    username: string = "",
    password: string = "",
    enabled: boolean = true,
    role: string = "",
    firstName: string = "",
    lastName: string = "",
    email: string = "",
    createdAt: string = "",
    updatedAt: string = "",
    imageUrl: string = "",
    userPlants: UserPlant [] = [],

  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.firstName =  firstName;
    this.lastName = lastName;
    this.email = email;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.imageUrl = imageUrl;
    this.userPlants = userPlants
  }
}
