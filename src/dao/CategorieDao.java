package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CategorieDao extends AbstractDao {
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction transaction = em.getTransaction();
	
	public void insertCat() {
		
	}

}
