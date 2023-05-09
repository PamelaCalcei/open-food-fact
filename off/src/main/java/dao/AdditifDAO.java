package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Additif;

public class AdditifDAO {

	private EntityManager entityManager;

	public AdditifDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<Additif> get(long id) {
		return Optional.ofNullable(entityManager.find(Additif.class, id));
	}

	public List<Additif> getAll() {
		Query query = entityManager.createQuery("SELECT a FROM Additif a");
		return query.getResultList();
	}

	public void save(Additif additif) {
		executeInsideTransaction(entityManager -> entityManager.persist(additif));
	}

	public void update(Additif additif, String[] params) {
		additif.setCode(Objects.requireNonNull(params[0], "Code cannot be null"));
		additif.setNom(Objects.requireNonNull(params[1], "Nom cannot be null"));
		executeInsideTransaction(entityManager -> entityManager.merge(additif));
	}

	public void delete(Additif additif) {
		executeInsideTransaction(entityManager -> entityManager.remove(additif));
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
