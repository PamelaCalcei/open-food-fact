package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
	 private NutriScore nutriscore;
	 private int energie;
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
	 private double vitA;
	 private double vitD;
	 private double vitE;
	 private double vitK;
	 private double vitC;
	 private double vitB1;
	 private double vitB2;
	 private double vitPP;
	 private double vitB6;
	 private double vitB9;
	 private double vitB12;
	 private double calcium;
	 private double magnesium;
	 private double iron;
	 private double fer;
	 private double betaCarotene;
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
	public int getEnergie() {
		return energie;
	}
	public void setEnergie(int energie) {
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
	public double getVitA() {
		return vitA;
	}
	public void setVitA(double vitA) {
		this.vitA = vitA;
	}
	public double getVitD() {
		return vitD;
	}
	public void setVitD(double vitD) {
		this.vitD = vitD;
	}
	public double getVitE() {
		return vitE;
	}
	public void setVitE(double vitE) {
		this.vitE = vitE;
	}
	public double getVitK() {
		return vitK;
	}
	public void setVitK(double vitK) {
		this.vitK = vitK;
	}
	public double getVitC() {
		return vitC;
	}
	public void setVitC(double vitC) {
		this.vitC = vitC;
	}
	public double getVitB1() {
		return vitB1;
	}
	public void setVitB1(double vitB1) {
		this.vitB1 = vitB1;
	}
	public double getVitB2() {
		return vitB2;
	}
	public void setVitB2(double vitB2) {
		this.vitB2 = vitB2;
	}
	public double getVitPP() {
		return vitPP;
	}
	public void setVitPP(double vitPP) {
		this.vitPP = vitPP;
	}
	public double getVitB6() {
		return vitB6;
	}
	public void setVitB6(double vitB6) {
		this.vitB6 = vitB6;
	}
	public double getVitB9() {
		return vitB9;
	}
	public void setVitB9(double vitB9) {
		this.vitB9 = vitB9;
	}
	public double getVitB12() {
		return vitB12;
	}
	public void setVitB12(double vitB12) {
		this.vitB12 = vitB12;
	}
	public double getCalcium() {
		return calcium;
	}
	public void setCalcium(double calcium) {
		this.calcium = calcium;
	}
	public double getMagnesium() {
		return magnesium;
	}
	public void setMagnesium(double magnesium) {
		this.magnesium = magnesium;
	}
	public double getIron() {
		return iron;
	}
	public void setIron(double iron) {
		this.iron = iron;
	}
	public double getFer() {
		return fer;
	}
	public void setFer(double fer) {
		this.fer = fer;
	}
	public double getBetaCarotene() {
		return betaCarotene;
	}
	public void setBetaCarotene(double betaCarotene) {
		this.betaCarotene = betaCarotene;
	}
	public boolean isHuilPalme() {
		return huilPalme;
	}
	public void setHuilPalme(boolean huilPalme) {
		this.huilPalme = huilPalme;
	}
	 
	 
}
