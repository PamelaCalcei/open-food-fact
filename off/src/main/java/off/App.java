package off;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dao.AdditifDAO;
import dao.AllergeneDAO;
import dao.CategorieDAO;
import dao.IngredientDAO;
import dao.MarqueDAO;
import dao.ProduitDAO;
import jakarta.persistence.EntityManager;
import model.Additif;
import model.Allergene;
import model.Categorie;
import model.Ingredient;
import model.JPAUtils;
import model.Marque;
import model.NutriScore;
import model.Produit;

public class App {
	public static void main(String[] args) throws IOException {
		long debut = System.currentTimeMillis();

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
							ingredient = new Ingredient(nom.trim().replaceAll("_", " ").replaceAll("/\\d+% ?/g", ""));
							IngredientDAO.getInstance().save(ingredient);
						}
						ingredients.add(ingredient);
					}
					produit.getIngredient().addAll(ingredients);
					break;
				case 5:
					if (!values[5].isEmpty()) {
						produit.setEnergie(Float.parseFloat(values[5]));
					}
					break;
				case 6:
					if (!values[6].isEmpty()) {
						produit.setQuantiteGraisse(Float.parseFloat(values[6]));
					}
					break;
				case 7:
					if (!values[7].isEmpty()) {
						produit.setSucre(Float.parseFloat(values[7]));
					}
					break;
				case 8:
					if (!values[8].isEmpty()) {
						produit.setFibres(Float.parseFloat(values[8]));
					}

					break;
				case 9:
					if (!values[9].isEmpty()) {
						produit.setSel(Float.parseFloat(values[9]));
					}
					break;
				case 10:
					if (!values[10].isEmpty()) {
						produit.setVitA(Float.parseFloat(values[10]));
					}

					break;
				case 11:
					if (!values[11].isEmpty()) {
						produit.setVitD(Float.parseFloat(values[11]));
					}
					break;
				case 12:
					if (!values[12].isEmpty()) {
						produit.setVitE(Float.parseFloat(values[12]));
					}
					break;
				case 13:
					if (!values[13].isEmpty()) {
						produit.setVitK(Float.parseFloat(values[13]));
					}

					break;
				case 14:
					if (!values[14].isEmpty()) {
						produit.setVitC(Float.parseFloat(values[14]));
					}

					break;
				case 15:
					if (!values[15].isEmpty()) {
						produit.setVitB1(Float.parseFloat(values[15]));
					}

					break;
				case 16:
					if (!values[16].isEmpty()) {
						produit.setVitB2(Float.parseFloat(values[16]));
					}

					break;
				case 17:
					if (!values[17].isEmpty()) {
						produit.setVitPP(Float.parseFloat(values[17]));
					}

					break;
				case 18:
					if (!values[18].isEmpty()) {
						produit.setVitB6(Float.parseFloat(values[18]));
					}

					break;
				case 19:
					if (!values[19].isEmpty()) {
						produit.setVitB9(Float.parseFloat(values[19]));
					}

					break;
				case 20:
					if (!values[20].isEmpty()) {
						produit.setVitB12(Float.parseFloat(values[20]));
					}

					break;
				case 21:
					if (!values[21].isEmpty()) {
						produit.setCalcium(Float.parseFloat(values[21]));
					}

					break;
				case 22:
					if (!values[22].isEmpty()) {
						produit.setMagnesium(Float.parseFloat(values[22]));
					}
					break;
				case 23:
					if (!values[23].isEmpty()) {
						produit.setIron(Float.parseFloat(values[23]));
					}
					break;
				case 24:
					if (!values[24].isEmpty()) {
						produit.setFer(Float.parseFloat(values[24]));
					}
					break;
				case 25:
					if (!values[25].isEmpty()) {
						produit.setBetaCarotene(Float.parseFloat(values[25]));
					}
					break;
				case 26:
					produit.setHuilPalme(Boolean.parseBoolean(values[25]));
					break;
				case 27:
					String[] allergeneNom = values[27].split(",");
					List<Allergene> allergene = new ArrayList<>();
					for (String nom : allergeneNom) {
						Allergene allergenes = null;
						allergenes = AllergeneDAO.getInstance().getByName(nom.trim());
						if (allergenes == null) {
							allergenes = new Allergene(nom.trim());
							AllergeneDAO.getInstance().save(allergenes);
						}
						allergene.add(allergenes);
					}
					produit.getAllergene().addAll(allergene);
					break;
				case 28:
					String[] additifNames = values[28].split(",");
					List<Additif> additifList = new ArrayList<>();
					for (String additifName : additifNames) {
						additifName = additifName.trim();
						Additif additif = AdditifDAO.getInstance().getByName(additifName);
						if (additif == null) {
							additif = new Additif();
							additif.setNom(additifName);
							AdditifDAO.getInstance().save(additif);
						}
						additifList.add(additif);
					}
					produit.getAdditif().addAll(additifList);
					break;
				default:
					break;
				}
			}
			ProduitDAO.getInstance().save(produit);
			j++;
		}

		EntityManager em = JPAUtils.getInstance().getEntityManager();
		long fin = System.currentTimeMillis();
		System.out.println("Temps écoulé en millisecondes :" + (fin - debut));
		System.out.println("Temps écoulé en minutes :" + (fin - debut) / 60000);

		em.close();
	}
}
