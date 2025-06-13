import { CareType } from "./care-type";
import { UserPlant } from "./user-plant";

export class CareLog {
  id: number;
  notes: string;
  careDate: string;
  imageUrl: string;
  enabled: boolean;
  userPlant: UserPlant;
  careType: CareType

  constructor(
    id: number = 0,
    notes: string = "",
    careDate: string = '',
    imageUrl: string = '',
    enabled: boolean = false,
    userPlant: UserPlant =  new UserPlant(),
    careType: CareType = new CareType()
  ){
    this.id = id;
    this.notes = notes;
    this.careDate = careDate;
    this.imageUrl = imageUrl;
    this.enabled = enabled;
    this.userPlant = userPlant;
    this.careType = careType

  }
}
