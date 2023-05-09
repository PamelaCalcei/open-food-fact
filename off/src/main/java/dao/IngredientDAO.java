package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Ingredient;

public class IngredientDAO implements Idao<Ingredient>{
	private EntityManager entityManager;

	public IngredientDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<Ingredient> get(long id) {
		return Optional.ofNullable(entityManager.find(Ingredient.class, id));
	}

	public List<Ingredient> getAll() {
		TypedQuery<Ingredient> query = entityManager.createQuery("SELECT a FROM Additif a", Ingredient.class);
		return query.getResultList();
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
