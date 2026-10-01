# Application de gestion de formations

## Présentation

### Etat actuel de l'application

Cette application Java permet de gérer et 
consulter des formations enregistrées dans 
une base de données.

Elle utilise un architécture en plsusieurs couches :

- le modéle d edonnées
- l'accés à la base de données
- la logique metier
- l'affichage dans la console
- le lancement et les les interactiosn avec l'utilisateur

L'application permet notament pour un utilisateur non authentifié
de consulter les formations et d'effectuer des recherche par mot-clé 
ou par modalitées d'enseignement, c'est a dire, si la formation
et en presentiel ou en distanciel ou les deux.

### Etat à venir de l'application

Elle permettra dans l'avenir au utilisateur authentifiés
en tant qu'acheteur de passer commande de formations pour
leurs clients. Il y aura un panier de commande et un passage
de commande.

## Fonctionalités

L'application permet plusieurs opérations sur les formations :

- Create : ajouter une formation
- Read : récupérer un formation par son identifiant
- Read All : afficher toutes les formations
- Update : modifier un formation
- Delete : supprimer un formation
- by key word : recherche par mot clé
- by modality : recherche par modalitée d'enseignement

## Strucure 

src
|- fr/
|   |- app/
|   |   |_ main.java
|   |- buisness/
|   |   |_ formation.java
|   |- dao/
|   |   |- Dao.java
|   |   |_ CourseDao.java
|   |- ihm/
|   |   |_ Console.java
|   |- model/
|   |   |_Course.java