package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Ingredient;
import model.JPAUtils;
import model.Marque;

public class IngredientDAO implements Idao<Ingredient> {

	private static IngredientDAO instance = null;
	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	private IngredientDAO() {
	}

	public static IngredientDAO getInstance() {
		if (instance == null) {
			instance = new IngredientDAO();
		}
		return instance;
	}

	public Optional<Ingredient> get(long id) {
		return Optional.ofNullable(entityManager.find(Ingredient.class, id));
	}

	public List<Ingredient> getAll() {
		TypedQuery<Ingredient> query = entityManager.createQuery("SELECT a FROM Additif a", Ingredient.class);
		return query.getResultList();
	}

	public Ingredient getByName(String nom) {
		TypedQuery<Ingredient> query = entityManager.createQuery("SELECT a FROM Ingredient a WHERE a.nom = :nom", Ingredient.class);
		query.setParameter("nom", nom);
		List<Ingredient> result = query.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	public void save(Ingredient ingredient) {
		executeInsideTransaction(entityManager -> entityManager.persist(ingredient));
	}

	public void update(Ingredient ingredient, String[] params) {
		ingredient.setNom(Objects.requireNonNull(params[0], "Nom cannot be null"));
		executeInsideTransaction(entityManager -> entityManager.merge(ingredient));
	}

	public void delete(Ingredient ingredient) {
		executeInsideTransaction(entityManager -> entityManager.remove(ingredient));
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
