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

public class AdditifDAO implements Idao<Additif> {

	private static AdditifDAO instance = null;

	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	private AdditifDAO() {
	}

	public static AdditifDAO getInstance() {
		if (instance == null) {
			instance = new AdditifDAO();
		}
		return instance;
	}

	public Optional<Additif> get(long id) {
		return Optional.ofNullable(entityManager.find(Additif.class, id));
	}

	public List<Additif> getAll() {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a", Additif.class);
		return query.getResultList();
	}

	public Additif getByName(String nom) {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a WHERE a.nom = :nom",
				Additif.class);
		query.setParameter("nom", nom);
		List<Additif> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}
	
	public Additif getByCode(String code) {
	    TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a WHERE a.code = :code", Additif.class);
	    query.setParameter("code", code);
	    List<Additif> result = query.getResultList();
	    return result.isEmpty() ? null : result.get(0);
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
