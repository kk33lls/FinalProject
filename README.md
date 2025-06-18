
<img src="images/logo.png" alt="logo" width="150" height="150" />

## Description

Soil Mates is a full-stack plant care tracking application designed to help users manage their personal plant collections with ease. Built with modern web technologies, it empowers plant lovers to log care activities, set reminders, comment on plant progress, and explore species-level plant data — all within a beautiful, intuitive interface.

## technologies used

<table>
  <tr>
    <td valign="top">

- Java  
- Eclipse  
- Git/GitHub  
- Sublime Text Editor  
- zsh  
- Spring Boot  
- Postman  

</td>
    <td valign="top">

- SQL Workbench  
- JPA/Hibernate  
- Gradle  
- JavaScript  
- Bootstrap  
- HTML/CSS  
- Angular  

</td>
  </tr>
</table>

 ## SQL Diagram

![diagram](images/diagram.png)

 ## Entities & Relationships

### 🔗 Entity Relationships (Compact)

<table>
  <tr>
    <td valign="top">

- **user**  
  - ⇨ user_plant  
  - ⇨ plant_collection  
  - ⇨ plant_comment  
  - ⇨ species_comment  

- **user_plant**  
  - ⇦ user  
  - ⇦ plant_species  
  - ⇨ care_log  
  - ⇨ reminder  
  - ⇨ plant_comment  
  - ⇨ collection_has_plant  

- **plant_species**  
  - ⇨ user_plant  
  - ⇨ species_comment  
  - ⇦ care_difficulty  

- **plant_collection**  
  - ⇦ user  
  - ⇨ collection_has_plant  

- **collection_has_plant**  
  - ⇄ plant_collection / user_plant  

</td>
    <td valign="top">

- **care_log**  
  - ⇦ user_plant  
  - ⇦ care_type  

- **reminder**  
  - ⇦ user_plant  
  - ⇦ care_type  

- **plant_comment**  
  - ⇦ user  
  - ⇦ user_plant  
  - ⤷ optional reply (self-reference)  

- **species_comment**  
  - ⇦ user  
  - ⇦ plant_species  
  - ⤷ optional reply (self-reference)  

- **care_type**  
  - ⇨ care_log  
  - ⇨ reminder  

- **care_difficulty**  
  - ⇨ plant_species  

</td>
  </tr>
</table>

 ## REST API Endpoints

### 🔐 AuthController
| Method | Endpoint        | Description                      |
|--------|-----------------|----------------------------------|
| POST   | `/register`     | Register a new user              |
| GET    | `/authenticate` | Authenticate a user via Basic Auth |

### 🌱 UserPlantController
| Method | Endpoint                                       | Description                            |
|--------|------------------------------------------------|----------------------------------------|
| GET    | `/api/plants`                                  | Get all user plants for current user   |
| GET    | `/api/plants/{userPlantId}`                    | Get a specific user plant by ID        |
| POST   | `/api/plants/plantSpecies/{plantSpeciesId}`    | Create a new user plant from a species |
| PUT    | `/api/plants/{userPlantId}`                    | Update a user plant                    |
| DELETE | `/api/plants/{userPlantId}`                    | Delete a user plant                    |

### 🪴 CareLogController
| Method | Endpoint                                             | Description                     |
|--------|------------------------------------------------------|---------------------------------|
| GET    | `/api/userPlants/{userPlantId}/careLogs`             | Get all care logs for a plant   |
| GET    | `/api/userPlants/{userPlantId}/careLogs/{careLogId}` | Get care log by ID              |
| POST   | `/api/userPlants/{userPlantId}/careLogs`             | Create a care log               |
| PUT    | `/api/userPlants/{userPlantId}/careLogs/{careLogId}` | Update a care log               |
| DELETE | `/api/userPlants/{userPlantId}/careLogs/{careLogId}` | Delete (soft delete) a care log |

### 🧪 CareTypeController
| Method | Endpoint         | Description        |
|--------|------------------|--------------------|
| GET    | `/api/careTypes` | Get all care types |

### 🌿 PlantSpeciesController
| Method | Endpoint                                | Description                     |
|--------|-----------------------------------------|---------------------------------|
| GET    | `/api/plantSpecies`                     | Get all plant species           |
| GET    | `/api/plantSpecies/{id}`                | Get plant species by ID         |
| GET    | `/api/plantSpecies/search/{keyword}`    | Search plant species by keyword |

### 👤 UserController
| Method | Endpoint          | Description        |
|--------|-------------------|--------------------|
| GET    | `/api/users/{id}` | Get user by ID     |
| PUT    | `/api/users/{id}` | Update user by ID  |
 ## Lessons Learned


 ## Notes


 ## How to Run


