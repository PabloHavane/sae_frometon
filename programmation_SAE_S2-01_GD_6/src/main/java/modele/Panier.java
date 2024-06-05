package modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private float montant;
	private List<Article> panier;
	private List<Integer> quantité;

	public Panier() {
		this.montant = 0;
		this.panier = new ArrayList<>();
		this.quantité = new ArrayList<>();
	}

	public float getMontant() {
		return this.montant;
	}

	public List<Article> getPanier() {
		return this.panier;
	}

	public List<Integer> getQuantité() {
		return this.quantité;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public boolean isPanierEmpty() {
		return this.panier.size() == 0;
	}

	public void ajouterPanier(Article article, int quantité) {
		int indice = 0;
		//if ((article.getQuantitéEnStock()) - quantité >= 0) {
			for (int i = 0; i < this.panier.size(); i++) {
				if (this.panier.get(i).equals(article)) {
					indice = i;
					this.panier.get(indice).setQuantitéEnStock(this.panier.get(indice).getQuantitéEnStock() - quantité);
					this.quantité.set(indice, quantité + this.quantité.get(indice));
				}
			}
			if (indice == 0) {
				this.setMontant(this.montant + quantité * article.getPrixTTC());
				this.panier.add(article);
				this.quantité.add(quantité);
				article.setQuantitéEnStock(article.getQuantitéEnStock() - quantité);
			}
			if (this.panier.size() > 1) {
				Article articl = this.panier.get(0);
				for (int i = 1; i < this.panier.size(); i++) {
					if (articl.equals(this.panier.get(i))) {
						this.quantité.set(i, this.quantité.get(0));
						article.setQuantitéEnStock(article.getQuantitéEnStock() + quantité);
						this.panier.remove(0);
						this.quantité.remove(0);
						i += this.panier.size();
					}
				}
			}
		}
	//}

	public float fraisDeLivraison(String livreur) {
		if (livreur.equals("Chronofresh")) {
			if (this.montant < 50) {
				return 23.8f;
			} else if (this.montant < 80) {
				return 17.8f;
			} else if (this.montant < 120) {
				return 9.9f;
			} else {
				return 0f;
			}
		} else {
			if (this.montant < 60) {
				return 14.9f;
			} else if (this.montant < 90) {
				return 9.9f;
			} else if (this.montant < 120) {
				return 4.9f;
			} else {
				return 0f;
			}
		}
	}

	public void viderPanier() {
		for (int i = 0; i < this.panier.size(); i++) {
			this.panier.get(i).setQuantitéEnStock(this.quantité.get(i) + this.panier.get(i).getQuantitéEnStock());
		}
		this.montant = 0;
		this.panier = new ArrayList<>();
		this.quantité = new ArrayList<>();
	}

	public float totalAvecExpedition(String livreur) {
		return this.fraisDeLivraison(livreur) + this.montant;
	}
}
