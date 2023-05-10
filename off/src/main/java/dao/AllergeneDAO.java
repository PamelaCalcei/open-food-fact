package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Allergene;
import model.Categorie;
import model.JPAUtils;

public class AllergeneDAO implements Idao<Allergene> {

	private static AllergeneDAO instance = null;

	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	private AllergeneDAO() {
	}

	public static AllergeneDAO getInstance() {
		if (instance == null) {
			instance = new AllergeneDAO();
		}
		return instance;
	}

	public Optional<Allergene> get(long id) {
		return Optional.ofNullable(entityManager.find(Allergene.class, id));
	}

	public List<Allergene> getAll() {
		TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Additif a", Allergene.class);
		return query.getResultList();
	}

	public Allergene getByName(String nom) {
		TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Allergene a WHERE a.nom = :nom",
				Allergene.class);
		query.setParameter("nom", nom);
		List<Allergene> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
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