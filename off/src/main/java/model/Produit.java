package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany
	private List<Marque> marques = new ArrayList<>();
	private String nom;
    @Enumerated(EnumType.STRING)
	private NutriScore nutriscore;
	private float energie;
	private float quantiteGraisse;
	@ManyToMany
	private List<Ingredient> ingredients = new ArrayList<>();
	@ManyToMany
	private List<Additif> additifs = new ArrayList<>();
	@ManyToOne
	private Categorie categorie;
	@ManyToMany
	private List<Allergene> allergenes = new ArrayList<>();
	private float sucre;
	private float fibres;
	private float proteines;
	private float vitA;
	private float vitD;
	private float vitE;
	private float vitK;
	private float vitC;
	private float vitB1;
	private float vitB2;
	private float vitPP;
	private float vitB6;
	private float vitB9;
	private float vitB12;
	private float calcium;
	private float magnesium;
	private float iron;
	private float fer;
	private float betaCarotene;
	private boolean huilPalme;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Marque> getMarque() {
		return marques;
	}

	public void setMarque(List<Marque> marque) {
		this.marques = marque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public NutriScore getNutriscore() {
		return nutriscore;
	}

	public void setNutriscore(NutriScore nutriscore) {
		this.nutriscore = nutriscore;
	}

	public float getEnergie() {
		return energie;
	}

	public void setEnergie(float energie) {
		this.energie = energie;
	}

	public float getQuantiteGraisse() {
		return quantiteGraisse;
	}

	public void setQuantiteGraisse(float quantiteGraisse) {
		this.quantiteGraisse = quantiteGraisse;
	}

	public List<Ingredient> getIngredient() {
		return ingredients;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredients = ingredient;
	}

	public List<Additif> getAdditif() {
		return additifs;
	}

	public void setAdditif(List<Additif> additif) {
		this.additifs = additif;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Allergene> getAllergene() {
		return allergenes;
	}

	public void setAllergene(List<Allergene> allergene) {
		allergenes = allergene;
	}

	public float getSucre() {
		return sucre;
	}

	public void setSucre(float sucre) {
		this.sucre = sucre;
	}

	public float getFibres() {
		return fibres;
	}

	public void setFibres(float fibres) {
		this.fibres = fibres;
	}

	public float getProteines() {
		return proteines;
	}

	public void setProteines(float proteines) {
		this.proteines = proteines;
	}

	public float getVitA() {
		return vitA;
	}

	public void setVitA(float vitA) {
		this.vitA = vitA;
	}

	public float getVitD() {
		return vitD;
	}

	public void setVitD(float vitD) {
		this.vitD = vitD;
	}

	public float getVitE() {
		return vitE;
	}

	public void setVitE(float vitE) {
		this.vitE = vitE;
	}

	public float getVitK() {
		return vitK;
	}

	public void setVitK(float vitK) {
		this.vitK = vitK;
	}

	public float getVitC() {
		return vitC;
	}

	public void setVitC(float vitC) {
		this.vitC = vitC;
	}

	public float getVitB1() {
		return vitB1;
	}

	public void setVitB1(float vitB1) {
		this.vitB1 = vitB1;
	}

	public float getVitB2() {
		return vitB2;
	}

	public void setVitB2(float vitB2) {
		this.vitB2 = vitB2;
	}

	public float getVitPP() {
		return vitPP;
	}

	public void setVitPP(float vitPP) {
		this.vitPP = vitPP;
	}

	public float getVitB6() {
		return vitB6;
	}

	public void setVitB6(float vitB6) {
		this.vitB6 = vitB6;
	}

	public float getVitB9() {
		return vitB9;
	}

	public void setVitB9(float vitB9) {
		this.vitB9 = vitB9;
	}

	public float getVitB12() {
		return vitB12;
	}

	public void setVitB12(float vitB12) {
		this.vitB12 = vitB12;
	}

	public float getCalcium() {
		return calcium;
	}

	public void setCalcium(float calcium) {
		this.calcium = calcium;
	}

	public float getMagnesium() {
		return magnesium;
	}

	public void setMagnesium(float magnesium) {
		this.magnesium = magnesium;
	}

	public float getIron() {
		return iron;
	}

	public void setIron(float iron) {
		this.iron = iron;
	}

	public float getFer() {
		return fer;
	}

	public void setFer(float fer) {
		this.fer = fer;
	}

	public float getBetaCarotene() {
		return betaCarotene;
	}

	public void setBetaCarotene(float betaCarotene) {
		this.betaCarotene = betaCarotene;
	}

	public boolean isHuilPalme() {
		return huilPalme;
	}

	public void setHuilPalme(boolean huilPalme) {
		this.huilPalme = huilPalme;
	}

}
