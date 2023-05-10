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

	private static MarqueDAO instance = null;
	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	private MarqueDAO() {
	}

	public static MarqueDAO getInstance() {
		if (instance == null) {
			instance = new MarqueDAO();
		}
		return instance;
	}

	public Optional<Marque> get(long id) {
		return Optional.ofNullable(entityManager.find(Marque.class, id));
	}

	public List<Marque> getAll() {
		TypedQuery<Marque> query = entityManager.createQuery("SELECT a FROM Additif a", Marque.class);
		return query.getResultList();
	}

	public Marque getByName(String nom) {
		TypedQuery<Marque> query = entityManager.createQuery("SELECT a FROM Marque a WHERE a.nom = :nom", Marque.class);
		query.setParameter("nom", nom);
		List<Marque> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
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
