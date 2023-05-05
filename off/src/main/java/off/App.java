package off;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import model.Categorie;
import model.JPAUtils;
import model.Produit;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
	
    	//Lire fichier avec instanciation
	    Path pathFile = Paths.get("D:\\srping tools\\open-food-fact\\open-food-facts.csv");
	    List<String> lines = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
	    List<String> data = new ArrayList<>();
	    
        for(String line: lines) {
        	Produit produit = new Produit();
            String[] values = line.split("\\|");
	    for(int i = 0 ; i < values.length;  i++) {
	    	switch(i) {
	    		case 0:
	    			Categorie categorie = new Categorie();
	    			break;
	    		default: 
	    			break;
	    		
	    	}
	    	}
	    }
        
      

        EntityManager em = JPAUtils.getInstance().getEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close(); 
    }
}
