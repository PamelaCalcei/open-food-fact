package dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.JPAUtils;
import model.NutriScore;
import model.Produit;

public class ProduitDAO implements Idao<Produit> {

	private EntityManager entityManager = JPAUtils.getInstance().getEntityManager();

	public Optional<Produit> get(long id) {
		return Optional.ofNullable(entityManager.find(Produit.class, id));
	}

	public List<Produit> getAll() {
		TypedQuery<Produit> query = entityManager.createQuery("SELECT a FROM Additif a", Produit.class);
		return query.getResultList();
	}

	public void save(Produit produit) {
		executeInsideTransaction(entityManager -> entityManager.persist(produit));
	}

	public void update(Produit produit, String[] params) {
		produit.setNom(Objects.requireNonNull(params[0], "Nom cannot be null"));
		produit.setNutriscore(Objects.requireNonNull(NutriScore.valueOf(params[1]), "Nutriscore cannot be null"));
		produit.setEnergie(Integer.parseInt(Objects.requireNonNull(params[2], "Energie cannot be null")));
		produit.setSucre(Float.parseFloat(Objects.requireNonNull(params[4], "Sucre cannot be null")));
		produit.setProteines(Float.parseFloat(Objects.requireNonNull(params[6], "Protéines cannot be null")));
		produit.setHuilPalme(Boolean.parseBoolean(Objects.requireNonNull(params[23], "Huile de palme cannot be null")));
		executeInsideTransaction(entityManager -> entityManager.merge(produit));
	}

	public void delete(Produit produit) {
		executeInsideTransaction(entityManager -> entityManager.remove(produit));
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
