# siteranker

Ranks web sites by number of visits in a given week.

## Prerequisites
* MySQL Community Edition
* npm
* node.js
* brew
* Java 8
* Maven 3

## Steps to deploy
0. Clone this repo and change directory to its base.
1. mysql -u root -p < db-scripts/siteranker_db.sql
2. mysql -u root -p < db-scripts/create_users.sql
3. Access the instance using mysql and execute: 'alter table site drop primary key;'
4. mysqlimport --local --delete --ignore-lines=1 --columns=period_end_date,url,total_visits --fields-terminated-by='|' --lines-terminated-by='\n' -u root -p siteranker_db db-scripts/site.csv
5. Access the instance using mysql and execute: 'UPDATE site SET id=(SELECT unhex(replace(uuid(),'-','')));'
6. Access the instance using mysql and execute: 'alter table site add primary key(id);'
7. mvn clean install
8. cd modules/siteranker-web
9. mvn -Dspring.profiles.active=dev jetty:run
10. Launch FireFox or Chrome and hit http://localhost:8080

