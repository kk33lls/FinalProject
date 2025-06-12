import { SpeciesComment } from "./species-comment";
import { UserPlant } from "./user-plant";

export class PlantSpecies {
  id: number;
  genus: string;
  species: string;
  commonNames: string;
  description: string;
  lightRequirements: string;
  waterFrequesncy: string;
  nativeTo: string;
  temperatureRange: string;
  imageUrl: string;
  enabled: boolean;
  careDifficulty: CareDifficulty[];
  userPlants: UserPlant[];
  speciesComments: SpeciesComment[];

  constructor(
  id: number = 0,
  genus: string = '',
  species: string = '',
  commonNames: string = '',
  description: string = '',
  lightRequirements: string = '',
  waterFrequesncy: string = '',
  nativeTo: string = '',
  temperatureRange: string = '',
  imageUrl: string = '',
  enabled: boolean = true,
  careDifficulty: CareDifficulty[] = [],
  userPlants: UserPlant[] = [],
  speciesComments: SpeciesComment[] = [],
  ){
  this.id = id;
  this.genus = genus;
  this.species = species
  this.commonNames = commonNames;
  this.description = description;
  this.lightRequirements = lightRequirements;
  this.waterFrequesncy = waterFrequesncy;
  this.nativeTo = nativeTo;
  this.temperatureRange = temperatureRange;
  this.imageUrl = imageUrl;
  this.enabled = enabled;
  this.careDifficulty = careDifficulty;
  this.userPlants = userPlants;
  this.speciesComments = speciesComments;
  }

}
