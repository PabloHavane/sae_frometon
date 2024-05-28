package modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	
	private float montant;
	private List<Article> panier;
	private List<Integer> quantité;
	
	public Panier () {
		this.montant = 0;
		this.panier = new ArrayList<>();
		this.quantité = new ArrayList<>();
	}
	
	public float getMontant () {
		return this.montant;
	}
	
	public List<Article> getPanier () {
		return this.panier;
	}
	
	public List<Integer> getQuantité () {
		return this.quantité;
	}
	
	public void setMontant (float montant) {
		this.montant = montant;
	}
	
	public void ajouterPanier (Article article, int quantité) {
		for (int i = 0; i < panier.size(); i++) {
			if (panier.get(i) == article ) {
				this.montant += article.getPrixTTC();
				int temporaire = this.quantité.get(i);
				this.quantité.remove(i);
				this.quantité.add(temporaire+quantité);
				this.panier.add(article);
				this.panier.get(i).setQuantitéEnStock(this.panier.get(i).getQuantitéEnStock()-quantité);
			} else {
				this.montant += article.getPrixTTC();
				this.quantité.add(quantité);
				this.panier.add(article);
				this.panier.get(i).setQuantitéEnStock(this.panier.get(i).getQuantitéEnStock()-quantité);
			}
		}
	}
	
	public float calculerPanier () {
		return this.montant;
	}
	
	public float fraisDeLivraison (String livreur) {
		if (this.montant < 120.0f) {
			if (livreur == "chronopost") {
				return 12.0f;
			} else {
				return 9.0f;
			}
		} else {
			return 0.0f;
		}
	}
	
	public void viderPanier () {
		for (int i = 0; i< this.panier.size(); i++) {
			this.panier.get(i).setQuantitéEnStock(this.quantité.get(i)+this.panier.get(i).getQuantitéEnStock());
		}
		this.montant=0;
		this.panier=null;
		this.quantité=null;
	}
	
	public float totalAvecExpedition(String livreur) {
		return this.fraisDeLivraison(livreur) + this.montant;
	}
	
	public String[][] générerPanier () {
		String[][] tableau= new String[10000][5];
		for (int i = 0; i< this.panier.size(); i++) {
			tableau[i][0] = "";
			tableau[i][1] = this.panier.get(i).getFromage().getDésignation();
			tableau[i][2]= String.format("%.0f",this.panier.get(i).getPrixTTC());
			tableau[i][3]= String.format("%.0f",this.quantité.get(i));
			tableau[i][4] =String.format("%.0f", this.panier.get(i).getPrixTTC() * this.quantité.get(i));
		}
		return tableau;
	}
}
