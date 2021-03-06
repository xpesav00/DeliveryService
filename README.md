# Delivery Service

This is a team project for advanced Java course at Masaryk University.

## Technology and setup requirements

* __Server__ - Apache Tomcat
* __Framework__ - Spring
* __Database__ - JavaDB, port 1527
* __Database name__ - DeliveryServiceDB
* __Database user__ - pa165
* __Database user password__ - pa165
* __Web application context__ - http://localhost:8080/pa165

## Modules
Project is divided in two modules:
#### 1. _DeliveryServiceParent_ 
Contains submodules with core functionality:

* __DeliveryService-Model__ - DAO layer entities, interfaces and their implementations.
* __DeliveryService-API__ - Service layer DTO´s and interfaces.
* __DeliveryService-ServiceLayer__ - Implementation of Service layer.
* __DeliveryService-Web__ - Web presentation layer, uses Spring MVC.
* __DeliveryService-REST-Client__ - DTO´s and interfaces used by REST client.

#### 2. _DeliveryServiceRestGuiClient_ 
Contains a REST client for Delivery Service.

## Building and Testing

Project uses maven, to build Web interface or REST Gui use:
```
mvn install
```
in respective directory.

Run DeliveryService-Web module from its directory:
```
mvn tomcat7:run
```
Application should be available on URL:
http://localhost:8080/pa165/

Run DeliveryServiceRestGuiClient module from its directory:
```
mvn exec:java
```
