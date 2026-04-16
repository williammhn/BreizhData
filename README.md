# BreizhData

**BreizhData** est une application JavaFX dédiée à la centralisation et à la visualisation de données statistiques et géographiques de la région Bretagne. L'outil permet d'explorer l'attractivité des communes bretonnes à travers divers indicateurs tels que la démographie, les infrastructures de transport (gares, aéroports) et les investissements culturels.

## Fonctionnalités principales

L'application offre une interface complète pour naviguer dans les données bretonnes :
* **Visualisation Cartographique** : Une carte interactive permettant de comparer les données exploitées par département ou par ville.
* **Gestion des Infrastructures** : Consultation détaillée des gares (fret et voyageurs) et des aéroports de la région.
* **Indicateurs Socio-économiques** : Accès aux données annuelles incluant le nombre de logements (maisons/appartements), les prix moyens de l'immobilier, le taux d'inflation et le budget total des communes.
* **Calcul de Statistiques** : Génération de rapports incluant la densité de population, le ratio des dépenses culturelles par habitant et l'équilibre budgétaire des communes.
* **Espace Utilisateur et Administration** : Système complet de connexion et d'inscription avec des droits d'administration pour la gestion des données et des utilisateurs.

## Technologies utilisées

* **Langage** : Java
* **Interface Graphique** : JavaFX avec FXML pour la gestion des vues.
* **Base de Données** : MySQL (utilisant le connecteur JDBC `com.mysql.cj.jdbc.Driver`).
* **Architecture** : Modèle DAO (Data Access Object) pour la séparation des données et de la logique métier.

## Structure du projet

* `src/model/data/` : Contient les classes métiers (entités) telles que `Commune`, `Departement`, `Aeroport`, `Gare`, etc.
* `src/model/dao/` : Gère les interactions avec la base de données MySQL pour chaque entité.
* `src/control/` : Contrôleurs JavaFX assurant le lien entre l'interface utilisateur et la logique métier.
* `src/view/` : Point d'entrée de l'application (`BreizhDataApp.java`).
* `resources/` : Fichiers FXML, images et styles CSS de l'application.

## Configuration et Installation

### Prérequis
1.  **Java JDK** (version 8 ou supérieure recommandée pour la compatibilité FXML).
2.  **Bibliothèques JavaFX** configurées dans votre IDE.
3.  **Serveur MySQL** local avec une base de données nommée `commBretonnes`.

### Paramètres de la base de données
Par défaut, l'application se connecte avec les identifiants suivants (modifiables dans `DAO.java`) :
* **URL** : `jdbc:mysql://localhost:3306/commBretonnes`
* **Utilisateur** : `admin`
* **Mot de passe** : `admin`

## Lancement

Exécutez la classe principale :
`view.BreizhDataApp`

