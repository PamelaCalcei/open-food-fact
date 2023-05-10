package dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Categorie;
import model.JPAUtils;

public class CategorieDAO implements Idao<Categorie> {
	private static CategorieDAO instance = null;

	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	private CategorieDAO() {
	}

	public static CategorieDAO getInstance() {
		if (instance == null) {
			instance = new CategorieDAO();
		}
		return instance;
	}
	
	public Optional<Categorie> get(long id) {
		return Optional.ofNullable(entityManager.find(Categorie.class, id));
	}

	public List<Categorie> getAll() {
		TypedQuery<Categorie> query = entityManager.createQuery("SELECT a FROM Categorie a", Categorie.class);
		return query.getResultList();
	}
	
	public Categorie getByName(String nom) {
		TypedQuery<Categorie> query = entityManager.createQuery("SELECT a FROM Categorie a WHERE a.nom = :nom",
				Categorie.class);
		query.setParameter("nom", nom);
		List<Categorie> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
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
