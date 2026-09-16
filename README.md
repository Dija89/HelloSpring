# HelloSpring - Gestion des entreprises

Projet réalisé avec Spring Boot dans le cadre de l'exercice de gestion des entreprises.

## Objectif

Créer une ressource `Entreprise` permettant de :

1. Lister toutes les entreprises
2. Rechercher une entreprise par sa raison sociale
3. Créer une nouvelle entreprise
4. Modifier une entreprise
5. Supprimer une entreprise

Au démarrage de l'application, si aucune entreprise n'existe en base de données, 5 entreprises sont automatiquement créées.

## Technologies utilisées

- Java 25
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL 8
- Lombok
- Swagger / OpenAPI
- Maven

## Structure

```text
src/main/java/com/groupeisi/HelloSpring
├── controllers
│   └── EntrepriseController.java
├── entities
│   └── Entreprise.java
├── init
│   └── Demarrage.java
├── repositories
│   └── EntrepriseRepository.java
└── services
    └── EntrepriseService.java
