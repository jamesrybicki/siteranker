create user 'siteranker_user'@'localhost' identified by 'foo123';
create user 'siteranker_user'@'%' identified by 'foo123';
grant all privileges on siteranker_db.* to 'siteranker_user'@'localhost' with grant option;
grant all privileges on siteranker_db.* to 'siteranker_user'@'%' with grant option;
