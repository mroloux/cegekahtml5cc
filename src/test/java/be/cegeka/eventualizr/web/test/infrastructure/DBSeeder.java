package be.cegeka.eventualizr.web.test.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class DBSeeder {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void seedData(Object... entities) {
		for (Object entity : entities) {
			entityManager.persist(entity);
		}
		entityManager.flush();
		entityManager.clear();
	}

}
