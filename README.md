# siteranker

Ranks web sites by number of visits in a given week.

## Live Demo
* This code is deployed to Pivotal Cloud Foundry <a href="https://siteranker.cfapps.io">here</a>.

## Prerequisites
* MySQL Community Edition
* npm
* node.js
* brew
* Java 8
* Maven 3

## Steps to deploy locally
0. Clone this repo and change directory to its base
1. mysql -u root -p < db-scripts/siteranker_db.sql
2. mysql -u root -p < db-scripts/create_users.sql
3. mvn clean install
4. cd modules/siteranker-web
5. mvn -Dspring.profiles.active=dev jetty:run
6. Launch FireFox or Chrome and hit http://localhost:8080

