package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Additif;
import model.JPAUtils;

public class AdditifDAO implements Idao<Additif>{

	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();


	public Optional<Additif> get(long id) {
		return Optional.ofNullable(entityManager.find(Additif.class, id));
	}

	public List<Additif> getAll() {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a", Additif.class);
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
