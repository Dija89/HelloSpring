# HelloSpring - Gestion des entreprises et des stages

Projet Spring Boot réalisé dans le cadre du cours de Java.

L'application met en œuvre un CRUD complet pour les entités **Entreprise** et **Stage**, avec persistance MySQL, documentation Swagger/OpenAPI et gestion centralisée des erreurs et exceptions.

---

## Objectifs

L'application permet de gérer deux ressources principales :

- Entreprises
- Stages

Pour chaque ressource, les opérations CRUD suivantes sont disponibles :

- Créer
- Lire / Rechercher
- Lister
- Modifier
- Supprimer

L'application intègre également :

- une base de données MySQL ;
- Spring Data JPA / Hibernate ;
- une documentation Swagger/OpenAPI ;
- une gestion globale des exceptions ;
- des codes HTTP adaptés ;
- une initialisation automatique des entreprises au démarrage.

---

## Technologies utilisées

- Java 25
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL 8
- Maven
- Lombok
- Swagger / OpenAPI
- SpringDoc

---

# Architecture du projet

Le projet utilise une architecture en couches :

```text
Requête HTTP
     |
     v
Controller
     |
     v
Service
     |
     v
Repository
     |
     v
JPA / Hibernate
     |
     v
MySQL
```

Structure principale :

```text
src/main/java/com/groupeisi/HelloSpring
|
|-- controllers
|   |-- EntrepriseController.java
|   `-- StageController.java
|
|-- entities
|   |-- Entreprise.java
|   |-- Etudiant.java
|   `-- Stage.java
|
|-- exceptions
|   |-- EntrepriseNotFoundException.java
|   |-- StageNotFoundException.java
|   `-- GlobalExceptionHandler.java
|
|-- init
|   `-- Demarrage.java
|
|-- repositories
|   |-- EntrepriseRepository.java
|   |-- EtudiantRepository.java
|   `-- StageRepository.java
|
`-- services
    |-- EntrepriseService.java
    |-- EtudiantService.java
    `-- StageService.java
```

---

# Entité Entreprise

Une entreprise possède notamment les informations suivantes :

```text
raisonSociale
secteurActivite
adresse
email
telephone
```

La `raisonSociale` est utilisée comme clé primaire de l'entité `Entreprise`.

Exemple :

```json
{
  "raisonSociale": "UNIVAL",
  "secteurActivite": "Communication",
  "adresse": "Dakar",
  "email": "unival@unival.sn",
  "telephone": "338000001"
}
```

---

# Entité Stage

Un stage possède les propriétés suivantes :

```text
id
sujetDefinitif
dateDebut
dateFin
statut
```

L'identifiant `id` est généré automatiquement par la base de données.

Exemple :

```json
{
  "id": 1,
  "sujetDefinitif": "Développement d'une application de gestion des stages",
  "dateDebut": "2026-10-01",
  "dateFin": "2026-12-31",
  "statut": "EN_COURS"
}
```

---

# API Entreprises

## Lister les entreprises

```http
GET /entreprises
```

Réponse :

```text
200 OK
```

---

## Rechercher une entreprise

```http
GET /entreprises/{raisonSociale}
```

Exemple :

```http
GET /entreprises/UNIVAL
```

Réponses possibles :

```text
200 OK
404 Not Found
```

---

## Créer une entreprise

```http
POST /entreprises
```

Exemple de corps JSON :

```json
{
  "raisonSociale": "NatuReina",
  "secteurActivite": "Beaute",
  "adresse": "Thies",
  "email": "natureina@beaute.sn",
  "telephone": "778945612"
}
```

Réponse :

```text
201 Created
```

---

## Modifier une entreprise

```http
PUT /entreprises/{raisonSociale}
```

Exemple :

```http
PUT /entreprises/NatuReina
```

Corps JSON :

```json
{
  "raisonSociale": "NatuReina",
  "secteurActivite": "Cosmetique et beaute",
  "adresse": "Dakar",
  "email": "contact@natureina.sn",
  "telephone": "771234567"
}
```

Réponses possibles :

```text
200 OK
404 Not Found
```

---

## Supprimer une entreprise

```http
DELETE /entreprises/{raisonSociale}
```

Réponses possibles :

```text
204 No Content
404 Not Found
```

---

# API Stages

## Lister les stages

```http
GET /stages
```

Réponse :

```text
200 OK
```

Si aucun stage n'existe :

```json
[]
```

---

## Rechercher un stage

```http
GET /stages/{id}
```

Exemple :

```http
GET /stages/1
```

Réponses possibles :

```text
200 OK
404 Not Found
```

---

## Créer un stage

```http
POST /stages
```

Exemple de corps JSON :

```json
{
  "sujetDefinitif": "Développement d'une application de gestion des stages",
  "dateDebut": "2026-10-01",
  "dateFin": "2026-12-31",
  "statut": "EN_COURS"
}
```

Réponse :

```text
201 Created
```

---

## Modifier un stage

```http
PUT /stages/{id}
```

Exemple :

```http
PUT /stages/1
```

Corps JSON :

```json
{
  "sujetDefinitif": "Développement d'une plateforme de gestion des stages",
  "dateDebut": "2026-10-01",
  "dateFin": "2027-01-31",
  "statut": "VALIDE"
}
```

Réponses possibles :

```text
200 OK
404 Not Found
```

---

## Supprimer un stage

```http
DELETE /stages/{id}
```

Réponses possibles :

```text
204 No Content
404 Not Found
```

---

# Gestion des erreurs et exceptions

L'application utilise des exceptions personnalisées :

```text
EntrepriseNotFoundException
StageNotFoundException
```

Elles sont traitées de manière centralisée par :

```text
GlobalExceptionHandler
```

grâce à :

```java
@RestControllerAdvice
```

Lorsqu'une entreprise inexistante est demandée, l'API retourne par exemple :

```json
{
  "date": "2026-09-18T10:56:21",
  "status": 404,
  "error": "Not Found",
  "message": "Entreprise introuvable : NatuReina"
}
```

Lorsqu'un stage inexistant est demandé :

```json
{
  "date": "2026-09-18T10:47:09",
  "status": 404,
  "error": "Not Found",
  "message": "Stage introuvable avec l'id : 1"
}
```

Cela permet d'éviter de retourner `null` ou une réponse HTTP 200 lorsqu'une ressource n'existe pas.

---

# Codes HTTP utilisés

| Action | Code HTTP |
|---|---:|
| Lecture réussie | 200 OK |
| Modification réussie | 200 OK |
| Création réussie | 201 Created |
| Suppression réussie | 204 No Content |
| Ressource inexistante | 404 Not Found |

---

# Documentation Swagger / OpenAPI

La documentation de l'API est disponible avec Swagger UI.

Après le démarrage du projet :

```text
http://localhost:8080/swagger-ui.html
```

Swagger permet de tester directement :

```text
Entreprises
    GET
    POST
    PUT
    DELETE

Stages
    GET
    POST
    PUT
    DELETE
```

Les différentes opérations sont documentées avec :

```java
@Tag
@Operation
@ApiResponse
@ApiResponses
```

---

# Initialisation des entreprises

La classe :

```text
Demarrage.java
```

implémente :

```java
CommandLineRunner
```

Au démarrage de l'application, le nombre d'entreprises présentes dans la base est vérifié :

```java
entrepriseRepository.count();
```

Si aucune entreprise n'existe, cinq entreprises sont automatiquement créées.

Entreprises initialisées :

```text
AFRICA DIGITAL
DAKAR LOGISTICS
SENAGRI
TERANGA TECH
UNIVAL
```

Premier démarrage :

```text
Il existe 0 entreprise(s) en base
Aucune entreprise en base, initialisation de 5 entreprises
Les 5 entreprises ont été créées avec succès
```

Démarrages suivants :

```text
Il existe 5 entreprise(s) en base
Il y a déjà des entreprises en base, aucune initialisation
```

Cela évite la création de doublons.

---

# Base de données MySQL

La base utilisée est :

```text
isi_hello_spring
```

Création de la base :

```sql
CREATE DATABASE IF NOT EXISTS isi_hello_spring
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

Configuration principale :

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/isi_hello_spring
    username: root

  jpa:
    hibernate:
      ddl-auto: update
```

`ddl-auto: update` permet de conserver les données lors des redémarrages de l'application.

---

# Installation Java

Le projet utilise :

```text
Java 25
```

Vérification :

```powershell
java -version
javac -version
```

Sous Windows, Java 25 peut être sélectionné temporairement avec :

```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-25.0.4.1"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

---

# Lancement du projet

Se placer dans le dossier contenant :

```text
pom.xml
mvnw
mvnw.cmd
src
```

Puis lancer :

```powershell
.\mvnw.cmd spring-boot:run
```

Lorsque le projet démarre correctement :

```text
Tomcat started on port 8080
Started HelloSpringApplication
```

L'application est alors accessible sur :

```text
http://localhost:8080
```

---

# Tests réalisés

## Entreprise

```text
GET /entreprises                       -> 200 OK
GET /entreprises/{raisonSociale}       -> 200 OK
POST /entreprises                      -> 201 Created
PUT /entreprises/{raisonSociale}       -> 200 OK
DELETE /entreprises/{raisonSociale}    -> 204 No Content
Entreprise inexistante                 -> 404 Not Found
```

## Stage

```text
GET /stages              -> 200 OK
GET /stages/{id}         -> 200 OK
POST /stages             -> 201 Created
PUT /stages/{id}         -> 200 OK
DELETE /stages/{id}      -> 204 No Content
Stage inexistant         -> 404 Not Found
```

Tous les tests ont été effectués avec Swagger.

---

# Résultat

Le projet comprend :

```text
CRUD Entreprise             OK
CRUD Stage                  OK
Connexion MySQL             OK
Spring Data JPA             OK
Gestion des exceptions      OK
Réponses HTTP adaptées      OK
Documentation Swagger       OK
Initialisation automatique  OK
```
