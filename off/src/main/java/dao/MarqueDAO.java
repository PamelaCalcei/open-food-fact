package dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.JPAUtils;
import model.Marque;

public class MarqueDAO implements Idao<Marque> {

	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	public Optional<Marque> get(long id) {
		return Optional.ofNullable(entityManager.find(Marque.class, id));
	}

	public List<Marque> getAll() {
		TypedQuery<Marque> query = entityManager.createQuery("SELECT a FROM Additif a", Marque.class);
		return query.getResultList();
	}

	public void save(Marque marque) {
		executeInsideTransaction(entityManager -> entityManager.persist(marque));
	}

	public void update(Marque marque, String[] params) {
		marque.setNom(params[0]);
		executeInsideTransaction(entityManager -> entityManager.merge(marque));
	}

	public void delete(Marque marque) {
		executeInsideTransaction(entityManager -> entityManager.remove(marque));
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
