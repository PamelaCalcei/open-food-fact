package off;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import dao.IngredientDAO;
import dao.MarqueDAO;
import dao.ProduitDAO;
import jakarta.persistence.EntityManager;
import model.Categorie;
import model.Ingredient;
import model.JPAUtils;
import model.Marque;
import model.NutriScore;
import model.Produit;

public class App {
	public static void main(String[] args) throws IOException {

		Path pathFile = Paths.get("D:\\srping tools\\open-food-fact\\open-food-facts.csv");
		List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);

		String firstLine = lines.get(0);
		lines.remove(0);
		int j = 0;
		for (String line : lines) {
			if (j > 99) {
				break;
			}
			Produit produit = new Produit();
			String[] values = line.split("\\|");
			for (int i = 0; i < values.length; i++) {
				switch (i) {
				case 0:
					Categorie categorie = null;
					categorie = CategorieDAO.getInstance().getByName(values[0]);
					if (categorie == null) {
						categorie = new Categorie(values[0]);
						CategorieDAO.getInstance().save(categorie);
					}
					produit.setCategorie(categorie);
					break;
				case 1:
					String[] marqueNom = values[1].split(",");
					List<Marque> marques = new ArrayList<>();
					for (String nom : marqueNom) {
						Marque marque = null;
						marque = MarqueDAO.getInstance().getByName(nom.trim());
						if (marque == null) {
							marque = new Marque(nom.trim());
							MarqueDAO.getInstance().save(marque);
						}
						marques.add(marque);
					}
					produit.getMarque().addAll(marques);
					break;
				case 2:
					produit.setNom(values[2]);
					break;
				case 3:
					NutriScore nutriScore = NutriScore.valueOf(values[3].trim().toUpperCase());
					produit.setNutriscore(nutriScore);
					break;
				case 4:
					String[] ingredientNom = values[4].split(",");
					List<Ingredient> ingredients = new ArrayList<>();
					for (String nom : ingredientNom) {
						Ingredient ingredient = null;
						ingredient = IngredientDAO.getInstance().getByName(nom.trim());
						if (ingredient == null) {
							ingredient = new Ingredient(nom.trim().replace("_", "").replace("%", "").replace("*", "").replace(",", "").replace(".", "").replace(";", "").replace(":", ""));
							IngredientDAO.getInstance().save(ingredient);
						}
						ingredients.add(ingredient);
					}
					produit.getIngredient().addAll(ingredients);
					break;
				case 5:
					produit.setEnergie(Float.parseFloat(values[5]));
					break;
				default:
					break;
				}
			}
			ProduitDAO.getInstance().save(produit);
			j++;
		}

		EntityManager em = JPAUtils.getInstance().getEntityManager();
		em.close();
	}
}
