package fr.diginamic.openfoodfacts;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfacts.entites.Categories;
import fr.diginamic.openfoodfacts.entites.Ingredients;
import fr.diginamic.openfoodfacts.entites.Produits;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
		EntityManager em = emf.createEntityManager();


		Path pathBase = Paths.get("C:\\Users\\PC VALENTIN\\Desktop\\Diginamic\\open-food-facts.csv");
		try {
			
			List<String> lines = Files.readAllLines(pathBase, StandardCharsets.UTF_8);

			lines.remove(0);
			for (int i = 0; i < lines.size(); i++) {
				EntityTransaction transaction = em.getTransaction();
				transaction.begin();
				String[] tab = lines.get(i).split("\\|", -1);
				String cat = tab[0];
				String nomProduit = tab[2];
				String nomNutriment = tab[3];
				List<String> listIngredient = Arrays.asList(tab[4].replace("_", "").split("[,;-]", -1));

				Produits produit = new Produits(nomProduit, nomNutriment);

				for (String ing : listIngredient) {
					System.out.println(ing);
//					produit.getIngredients().add(ing);
				}

				Categories categorie = new Categories(cat);
				TypedQuery<Categories> query = em.createQuery("SELECT c FROM Categories c WHERE c.nom = ?1",
						Categories.class);
				query.setParameter(1, cat);

				List<Categories> categories = query.getResultList();

				if (categories.size() == 0) {
					em.persist(categorie);
					produit.setCategorie(categorie);
				} else {
					Categories categorieBase = categories.get(0);
					produit.setCategorie(categorieBase);
				}
				

				em.persist(produit);
				transaction.commit();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
