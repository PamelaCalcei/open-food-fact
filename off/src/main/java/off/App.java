package off;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import jakarta.persistence.EntityManager;
import model.Categorie;
import model.JPAUtils;
import model.Produit;

public class App {
	public static void main(String[] args) throws IOException {

		Path pathFile = Paths.get("D:\\srping tools\\open-food-fact\\open-food-facts.csv");
		List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);

		String firstLine = lines.get(0);
		lines.remove(0);
		int j = 0;
		for (String line : lines) {
			if(j > 99) {
				break;
			}
			Produit produit = new Produit();
			String[] values = line.split("\\|");
			for (int i = 0; i < values.length; i++) {
				switch (i) {
				case 0:
					Categorie categorie = null;
					//categorie = CategorieDAO.getInstance().getByName(values[0]);
					//if(categorie == null){
						//categorie = new Categorie(values[0]);
						//categorie = CategorieDAO.getInstance().save(categorie);
					//}
					produit.setCategorie(categorie);
					break;
				default:
					break;
				}
			}
			//ProduitDAO.getInstance.save(produit);
			j++;
		}

		EntityManager em = JPAUtils.getInstance().getEntityManager();
		em.close();
	}
}
