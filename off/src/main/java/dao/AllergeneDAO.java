package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Allergene;

public class AllergeneDAO implements Idao<Allergene>{

	private EntityManager entityManager;

	public AllergeneDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public Optional<Allergene> get(long id) {
		return Optional.ofNullable(entityManager.find(Allergene.class, id));
	}

	public List<Allergene> getAll() {
		TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Additif a", Allergene.class);
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