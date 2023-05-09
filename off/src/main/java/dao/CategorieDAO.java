package dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Categorie;

public class CategorieDAO {

	private EntityManager entityManager;

	public CategorieDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<Categorie> get(long id) {
		return Optional.ofNullable(entityManager.find(Categorie.class, id));
	}

	public List<Categorie> getAll() {
		Query query = entityManager.createQuery("SELECT c FROM Categorie c");
		return query.getResultList();
	}

	public void save(Categorie categorie) {
		executeInsideTransaction(entityManager -> entityManager.persist(categorie));
	}

	public void update(Categorie categorie, String[] params) {
		categorie.setNom(params[0]);
		executeInsideTransaction(entityManager -> entityManager.merge(categorie));
	}

	public void delete(Categorie categorie) {
		executeInsideTransaction(entityManager -> entityManager.remove(categorie));
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}
}
