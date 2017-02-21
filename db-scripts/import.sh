#!/bin/bash

# alter table site drop primary key;
# mysqlimport --local --delete --ignore-lines=1 --columns=period_end_date,url,total_visits --fields-terminated-by='|' --lines-terminated-by='\n' -u root -p siteranker_db site.csv
# UPDATE site SET id=(SELECT unhex(replace(uuid(),'-','')));
# alter table site add primary key(id);
