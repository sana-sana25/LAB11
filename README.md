# LAB11 — Application Android de Géolocalisation avec PHP/MySQL

## Description du projet

Ce laboratoire consiste à développer une application Android capable de :

- récupérer la position GPS du smartphone ;
- afficher les coordonnées géographiques ;
- envoyer les données vers un serveur PHP ;
- enregistrer les informations dans une base de données MySQL.

Le projet met en œuvre une architecture client/serveur complète entre Android et un backend PHP/MySQL.

---

## Objectifs pédagogiques

À la fin de ce laboratoire, il est possible de :

- utiliser les services de localisation Android ;
- gérer les permissions Android ;
- envoyer des requêtes HTTP avec Volley ;
- développer un backend PHP orienté objet ;
- utiliser PDO avec MySQL ;
- stocker des coordonnées GPS dans une base de données.

---

## Technologies utilisées

### Partie Mobile

| Technologie | Rôle |
|---|---|
| Android Studio | IDE de développement |
| Java | Langage de programmation |
| Volley | Requêtes HTTP |
| GPS Android | Récupération des coordonnées |
| Emulator Android | Tests |

### Partie Serveur

| Technologie | Rôle |
|---|---|
| PHP | Langage backend |
| PDO | Accès à la base de données |
| MySQL | Base de données |
| XAMPP | Serveur local |
| phpMyAdmin | Administration BDD |

---

## Architecture du projet

```
LAB11/
│
├── LocalisationGPSApp/        ← Application Android
│
└── localisation/              ← Backend PHP
```

### Partie Mobile

L'application Android :

- récupère latitude et longitude ;
- affiche les informations GPS ;
- envoie les données au serveur via HTTP POST.

### Partie Serveur

Le serveur PHP :

- reçoit les données envoyées ;
- crée un objet métier `Position` ;
- insère les données dans MySQL.

---

## Structure du backend PHP

```
localisation/
│
├── classe/
│   └── Position.php
│
├── connexion/
│   └── Connexion.php
│
├── dao/
│   └── IDao.php
│
├── service/
│   └── PositionService.php
│
└── createPosition.php
```

---

## Base de données

**Base :** `localisation`  
**Table :** `position`

| Champ | Type |
|---|---|
| id | int |
| latitude | double |
| longitude | double |
| date_position | datetime |
| imei | varchar(50) |

---

## Fonctionnement général

1. Le smartphone détecte une nouvelle position GPS.
2. L'application récupère : latitude, longitude, altitude, précision.
3. Les données sont envoyées au serveur PHP avec Volley.
4. Le serveur reçoit les données via `$_POST`.
5. Le backend PHP enregistre les informations dans MySQL.

---

## Permissions Android

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```

---

## Dépendances

**Volley** (`app/build.gradle`) :

```gradle
implementation 'com.android.volley:volley:1.2.1'
```

---

## Installation et configuration

### Prérequis

- Android Studio installé
- XAMPP installé

### Étapes

1. Installer et démarrer **XAMPP** (Apache + MySQL)
2. Copier le dossier `localisation` dans :
   ```
   C:\xampp\htdocs\
   ```
3. Créer la base de données `localisation` via phpMyAdmin
4. Créer la table `position` avec la structure définie ci-dessus
5. Lancer l'application Android sur l'émulateur

### URL du serveur

```
http://10.0.2.2/localisation/createPosition.php
```

> `10.0.2.2` est l'adresse de la machine hôte vue depuis l'émulateur Android.

---

## Résultats

L'application :

- ✅ récupère correctement les coordonnées GPS ;
- ✅ affiche les informations à l'écran ;
- ✅ envoie les données au serveur ;
- ✅ stocke les positions dans MySQL avec succès.


---

## Captures d'écran
<img width="507" height="819" alt="Screenshot 2026-05-19 180150" src="https://github.com/user-attachments/assets/28a90dcc-e5f1-46a3-89ca-d8500873b7d0" />
<img width="1919" height="889" alt="Screenshot 2026-05-20 095001" src="https://github.com/user-attachments/assets/24a0267c-6542-4d42-8315-fe71771c73dc" />
<img width="636" height="842" alt="Screenshot 2026-05-20 095716" src="https://github.com/user-attachments/assets/063f0df1-1e5f-41fe-bdca-1aa1e4751ef2" />
<img width="1919" height="959" alt="Screenshot 2026-05-20 095737" src="https://github.com/user-attachments/assets/1477166d-48a1-4e91-8cd2-2e1f47053d98" />


---

## Démonstration vidéo

https://github.com/user-attachments/assets/f02f9b16-a969-4e35-9211-8881ccbfed59





---

## Auteur

**ASSEKNOUR Sana**  
4ème année — Génie Cyberdéfense et Systèmes de Télécommunications Embarquées  
ENSA Marrakech
