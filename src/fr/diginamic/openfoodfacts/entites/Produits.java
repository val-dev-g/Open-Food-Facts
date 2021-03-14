package fr.diginamic.openfoodfacts.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produits {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nom;

	private String nutritionGradeFr;

	@ManyToMany
	@JoinTable(name = "produit_ingredient",

			joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))

	private List<Ingredients> ingredients = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categories categorie;

	@ManyToOne
	@JoinColumn(name = "id_marque")
	private Marques marque;

	public Produits( String nom, String nutrition) {
		super();
		this.nom = nom;
		this.nutritionGradeFr = nutrition;
	}

	@Override
	public String toString() {
		return "Produits [id=" + id + ", nom=" + nom + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNutritionGradeFr() {
		return nutritionGradeFr;
	}

	public void setNutritionGradeFr(String nutritionGradeFr) {
		this.nutritionGradeFr = nutritionGradeFr;
	}

	public List<Ingredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}

	public Categories getCategorie() {
		return categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

	public Marques getMarque() {
		return marque;
	}

	public void setMarque(Marques marque) {
		this.marque = marque;
	}

}
