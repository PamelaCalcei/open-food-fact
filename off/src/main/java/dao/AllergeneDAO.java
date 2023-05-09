package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Allergene;
import model.JPAUtils;

public class AllergeneDAO {

	private EntityManager entityManager;

	public AllergeneDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AllergeneDAO() {
		entityManager = JPAUtils.getInstance().getEntityManager();
	}

	public Optional<Allergene> get(long id) {
		return Optional.ofNullable(entityManager.find(Allergene.class, id));
	}

	public List<Allergene> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Allergene a");
		return query.getResultList();
	}

	public void save(Allergene allergene) {
		executeInsideTransaction(entityManager -> entityManager.persist(allergene));
	}

	public void update(Allergene allergene, String[] params) {
		allergene.setNom(Objects.requireNonNull(params[0], "Name cannot be null"));
		executeInsideTransaction(entityManager -> entityManager.merge(allergene));
	}

	public void delete(Allergene allergene) {
		executeInsideTransaction(entityManager -> entityManager.remove(allergene));
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