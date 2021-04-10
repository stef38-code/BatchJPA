# BatchJPA

## Description
Le batch va lire dans une table puis transformer les données pour les 
copier dans une autre table. 
De plus le batch continu le traitement dans le cas d'exception précises 
mais donne une limite(nombre) pour une autre exception.

## Librairies spring :
* spring-boot-starter-parent : 2.2.2.RELEASE
* spring-boot-starter-batch
* spring-boot-starter-data-jpa
* spring-boot-starter-aop

## Caractèristiques du batch
Ce batch ne contient :
* Un job : __jobPrincipal__
* Un step: __step1__

## Caractéristique du job [BatchConfig](./src/main/java/com/stef/spring/batch/jpa/config/BatchConfig.java)
```java
public Job jobPrincipal(Step step, JobListener jobListener) {
    ....
}
```
* Listener [JobListener](./src/main/java/com/stef/spring/batch/jpa/listener/job/JobListener.java) : listener du job
* Step : suite de traitement reader,processor et writer

## Caractéristique du step [BatchConfig](./src/main/java/com/stef/spring/batch/jpa/config/BatchConfig.java)
```java
public Step step1(JpaItemReader reader,
                            JpaItemWriter writer,
                            Processor processor,
                            StepListener stepListener,
                            ReaderListener readerListener,
                            ProcessListener processListener,
                            WriterListener writerListener
    ){
        .....
}
```
### Gestion de la tolérance aux erreurs.
* [PersonnalSkipPolicy](./src/main/java/com/stef/spring/batch/jpa/policy/PersonnalSkipPolicy.java)

### Lecture des données
* reader [JpaItemReader](./src/main/java/com/stef/spring/batch/jpa/reader/JpaItemReader.java) : phase de lecture des données en DB 
* Listener [ReaderListener](./src/main/java/com/stef/spring/batch/jpa/listener/reader/ReaderListener.java): listener sur la partie lecture
### Traitement des données
* processor [Processor](./src/main/java/com/stef/spring/batch/jpa/processor/Processor.java): phase de transformation des données
* Listener [ProcessListener](./src/main/java/com/stef/spring/batch/jpa/listener/processor/ProcessListener.java): listener sur la partie transformation des données
### Ecriture des données trasformées
* writer [JpaItemWriter](./src/main/java/com/stef/spring/batch/jpa/writer/JpaItemWriter.java): phase d'écriture des données trabsformées
* listener [WriterListener](./src/main/java/com/stef/spring/batch/jpa/listener/writer/WriterListener.java):listener sur la partie écritures des données

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

#Ecriture d'un batch
0. Modification de la classe [BatchConfig.java](src/main/java/com/stef/spring/batch/jpa/config/BatchConfig.java)
* ligne 118 : inscrire le nom de la classe pour le reader puis pour le writer
1. Modification du reader [JpaItemReader.java](src/main/java/com/stef/spring/batch/jpa/reader/JpaItemReader.java)
2. Modification du processor [Processor.java](src/main/java/com/stef/spring/batch/jpa/processor/Processor.java)
3. Modification du writer [JpaItemWriter.java](src/main/java/com/stef/spring/batch/jpa/writer/JpaItemWriter.java)
4. 