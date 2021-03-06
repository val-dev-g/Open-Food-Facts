package fr.diginamic.openfoodfacts.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ALLERGENE")
public class Allergenes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nom_allergene")
	private String nom;

	@ManyToMany
	@JoinTable(name = "allergene_produit", joinColumns = @JoinColumn(name = "id_allergene", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "ID"))
	private List<Produits> produits;

	public Allergenes(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Allergenes [id=" + id + ", nom=" + nom + "]";
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

}
