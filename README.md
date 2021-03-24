# BatchJPA
## Description
Le batch va lire dans une table puis transformer les données pour les dans une autre table
## Caractèristiques du batch
Ce batch ne contient :
* un job : __jobPrincipal__
* un step. __step1__
## Caractéristique du step
* reader : phase de lecture des données en DB 
##Lancement
ClassMain
```java
org.springframework.batch.core.launch.support.CommandLineJobRunner
```
Arguments
```java
com.stef.spring.batch.jpa.config.BatchConfig jobPrincipal
```
ligne de lancement compléte
```shell
java -jar BatchJpa-0.0.1-SNAPSHOT.jar org.springframework.batch.core.launch.support.CommandLineJobRunner com.stef.spring.batch.jpa.config.BatchConfig jobPrincipal
```