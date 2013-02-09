package be.cegeka.eventualizr.domain.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dbmaintain.launch.task.UpdateDatabaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DBManager {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UpdateDatabaseTask updateDatabaseTask;

	private boolean dbUpdated = false;
	
	@Transactional
	public void seedData(Object... entities) {
		for (Object entity : entities) {
			entityManager.persist(entity);
		}
		entityManager.flush();
		entityManager.clear();
	}
	
	public void updateDatabase(){
		if(!dbUpdated ){
			updateDatabaseTask.execute();
			dbUpdated = true;
		}
	}
	
	@Transactional
	public void truncateTables(String... tableNames){
		disableForeignKeys(); 
        
        truncate(tableNames);
  
        enableForeignKeys();   
	}

	private void truncate(String... tableNames) {
		for (String tableName : tableNames) {
        	Query query = entityManager.createNativeQuery("TRUNCATE TABLE " + tableName);
        	query.executeUpdate();
        	entityManager.flush();
        	entityManager.clear();
        }
	}

	private void enableForeignKeys() {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}

	private void disableForeignKeys() {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
	}
	
}
