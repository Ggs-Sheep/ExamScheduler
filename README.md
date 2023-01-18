# EXAM SCHEDULER READ ME /FR

## Contexte

Ce projet est réalisé dans le cadre d'un projet scolaire. Il porte sur la création d'un planificateur de sessions d'examen.

<br>

Le projet est composé d'une API Java Spring Boot avec un système de base de données ***MYSQL*** ou ***RDS MYSQL*** en fonction de la configuration.
La partie Front du site est réalisée grâce à Angular et est initialisé avec l'utilisation de l'api en localhost.

## Utilisation de l'API

Afin d'utiliser cette API vous devez tout d'abord modifier le fichier de configuration (***YAML*** ou ***.PROPERTIES*** au choix) avec vos identifiants de base de données.
Vous pouvez démarrer l'api en démarrant le fichier ExamSchedulerApiLauncher via votre IDE ou en utilisant le commande suivante à la racine du projet :

```
maven clean install -DskipTests
```

ou
 
```
mvn clean install -DskipTests
```

Cette commande va générer un ***.JAR*** que vous pourez donc lancer afin de démarrer l'API.
Vous pouvez voir toutes les commandes possibles de l'api en installant l'application POSTAMN et en chargeant le fichier postamn json.

## Utilisation de Front

Afin d'utiliser le front, il vous suffit de lancer la commande suivante à la racine du projet :

```
npm install
ng serve
```

ou alors

```
npm install                  
ng build
```

Puis de lancer le fichier index.html dans le dossier /dist/exam_scheduler.
