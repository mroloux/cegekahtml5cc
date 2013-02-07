#Eventualizr - domain

##useful maven commando's for database
See <http://www.dbmaintain.org/overview.html> for more info on dbmaintain.  
_Make sure you are in the eventualizr-domain root folder!_

###clearDatabase
Task that removes all database items (from localdatabase) like tables, views etc from the database and empties the DBMAINTAIN_SCRIPTS table.

    mvn generate-sources -Pdev -Ddb=clearDatabase

###updateDatabase
Task that updates the local database to the latest version with the scripts located in the dbscripts folder.  
It uses the DBMAINTAIN_SCRIPTS table to see which scripts need to be executed.

    mvn generate-sources -Pdev -Ddb=updateDatabase

###markErrorScriptReverted
Task that indicates that the failed script was manually reverted. The script will be run again in the next update. No scripts will be executed by this task.  
When a script fails during updateDatabase you can:  
Fix the script, revert committed changes of the script (if any) and call the markErrorScriptReverted task.

    mvn generate-sources -Pdev -Ddb=markErrorScriptReverted

###markErrorScriptPerformed
Task that indicates that the failed script was manually performed. The script will NOT be run again in the next update. No scripts will be executed by this task.  
When a script fails during updateDatabase you can:  
Fix the script, manually perform the changes of the script and call the markErrorScriptPerformed task.

    mvn generate-sources -Pdev -Ddb=markErrorScriptPerformed
    

