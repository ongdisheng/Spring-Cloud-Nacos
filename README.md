# Spring Cloud Nacos


## Problem Statement
In a microservice architecture, services often need to communicate with each other. This is typically done using RestTemplate for exchanging HTTP requests. However, hardcoding IP addresses in these interactions leads to two major issues:
1. **Load Imbalance**: When multiple instances of a service provider are available, hardcoding the IP address of a single instance causes the other instances to remain idle. This leads to uneven distribution of traffic, with one instance bearing the brunt of the load.
2. **Single Point of Failure**: If the hardcoded service provider instance goes offline, the microservice loses access to the service, as it cannot dynamically switch to other available instances. This results in reduced reliability and increased downtime.

## Description
To address these challenges, this project leverages Spring Cloud Alibaba Nacos for dynamic service discovery and registration. Nacos simplifies the process of managing microservices by providing a centralized service registry and a robust configuration management system.

![img_nacos](https://github.com/user-attachments/assets/bdb39d84-68dc-4eae-8a99-ba3be3cd04f8)

Based on the above diagram, Nacos allows service providers to register themselves at runtime. Instead of relying on hardcoded IP addresses, services are identified by their logical names. Nacos tracks all available instances of each service, ensuring that service consumer requests are dynamically routed to healthy instances.

## Getting Started

###  :file_folder: File Structure

```
.
├── cart-service
│   ├── src
│   │   ├── main
|   |   |   ├── java/com/example
|   |   |   |   ├── config
|   |   |   |   ├── controller
|   |   |   |   ├── mapper
|   |   |   |   ├── pojo
|   |   |   |   ├── service
|   |   |   |   └── CartServiceApplication.java
|   |   |   └── resources 
│   │   └── test
│   └── pom.xml
|
├── item-service
│   ├── src
│   │   ├── main
|   |   |   ├── java/com/example
|   |   |   |   ├── controller
|   |   |   |   ├── mapper
|   |   |   |   ├── pojo
|   |   |   |   ├── service
|   |   |   |   └── ItemServiceApplication.java
|   |   |   └── resources 
│   │   └── test
│   └── pom.xml
|
├── data
|   └── init.sql
|
├── docker-compose.yml
|
├── pom.xml
|
└── README.md
```

### :wrench: Tools

* Postman
* IntelliJ IDEA
* Docker Desktop
* MySQL Workbench (optional)

### :rocket: Setup
1. Clone the repository
```
git clone https://github.com/ongdisheng/Spring-Cloud-Nacos.git
```
2. Spin up MySQL and Nacos using Docker Compose
```
cd Spring-Cloud-Nacos
docker-compose up -d
```
3. Execute microservice applications

![img_microservice](https://github.com/user-attachments/assets/5ea67b55-335e-4487-89a9-82f3cde7317b)

> Ensure that ItemServiceApplication2 is started on a port that is not currently in use by other services to avoid port conflicts

4. Visit http://localhost:8848/nacos to view the registered item service providers using the credentials:
* Username: nacos
* Password: nacos

![img_nacos_management](https://github.com/user-attachments/assets/0c827e35-e4b4-496e-818e-d63f54dadf37)

5. Simulate the communication between the Cart microservice and the Item microservice using Postman

![img_postman](https://github.com/user-attachments/assets/476372ca-bed8-4afe-a12b-d1362ac8f88b)
