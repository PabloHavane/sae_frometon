package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

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
	    if (quantité <= 0) {
	        return; // Quantité invalide
	    }

	    // Ajuster la quantité si le stock est insuffisant
	    int quantitéEffective = Math.min(quantité, article.getQuantitéEnStock());

	    int indice = -1;
	    for (int i = 0; i < this.panier.size(); i++) {
	        if (this.panier.get(i).equals(article)) {
	            indice = i;
	            break;
	        }
	    }

	    if (indice != -1) {
	        this.quantité.set(indice, this.quantité.get(indice) + quantitéEffective);
	    } else {
	        this.panier.add(article);
	        this.quantité.add(quantitéEffective);
	    }

	    article.setQuantitéEnStock(article.getQuantitéEnStock() - quantitéEffective);
	    this.montant += quantitéEffective * article.getPrixTTC();
	}


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
			Article article = this.panier.get(i);
	        article.setQuantitéEnStock(article.getQuantitéEnStock() + this.quantité.get(i));
		}
		this.montant = 0;
		this.panier.clear();
		this.quantité.clear();
	}
	
	public void supprimerUnArticlePanier(Article article) {
	    Iterator<Article> articleIterator = panier.iterator();
	    Iterator<Integer> quantiteIterator = quantité.iterator();
	    int i = 0;

	    while (articleIterator.hasNext()) {
	        Article art = articleIterator.next();
	        quantiteIterator.next(); // Avance l'itérateur de quantité

	        if (art.equals(article)) {
	            art.setQuantitéEnStock(art.getQuantitéEnStock() + this.quantité.get(i));
	            this.montant -= art.getPrixTTC() * this.quantité.get(i);
	            articleIterator.remove();
	            quantiteIterator.remove();
	            break;
	        }
	        i++;
	    }
	}
	
	public float totalAvecExpedition(String livreur) {
		return this.fraisDeLivraison(livreur) + this.montant;
	}
}
