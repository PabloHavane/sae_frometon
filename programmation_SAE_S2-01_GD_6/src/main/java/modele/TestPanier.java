package modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPanier {

	private Panier panier;
	private static Fromages mesArticles;

	@Before
	public void setUp() {
		this.panier = new Panier();
	}

	@After
	public void tearDown() {
		this.panier = null;
		mesArticles = null;
	}

	@Test
	public void testSetMontant() {
		this.panier.setMontant(14);
		assertEquals(this.panier.getMontant(), 14, 0);
	}

	@Test
	public void testFraisDeLivraisonChronopost() {
		assertEquals(this.panier.fraisDeLivraison("chronopost"), 12, 0);
	}

	@Test
	public void testFraisDeLivraisonAutre() {
		assertEquals(this.panier.fraisDeLivraison("c"), 9, 0);
	}

	@Test
	public void testFraisDeLivraisonMontantPlusQue120() {
		this.panier.setMontant(150);
		assertEquals(this.panier.fraisDeLivraison("chronopost"), 0, 0);
	}

	@Test
	public void testViderPanier() {
		assertEquals(this.panier.getMontant(), 0, 0);
		assertEquals(this.panier.getPanier().size(), 0);
		assertEquals(this.panier.getQuantité().size(), 0);
	}

	@Test
	public void testTotalAvecExpédition() {
		this.panier.setMontant(30);
		assertEquals(this.panier.totalAvecExpedition("chronopost"), 42, 0);
	}

	@Test
	public void testTotalAvecExpéditionSupérieur120() {
		this.panier.setMontant(300);
		assertEquals(this.panier.totalAvecExpedition("chronopost"), 300, 0);
	}

	@Test
	public void testBeaucoupDArticle() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		Article B = new Article(new Fromage("Test2"), "f", 5.0f);
		Article C = new Article(new Fromage("Test3"), "g", 5.0f);
		Article D = new Article(new Fromage("Test4"), "h", 8.0f);
		A.setQuantitéEnStock(193);
		B.setQuantitéEnStock(123);
		C.setQuantitéEnStock(192);
		D.setQuantitéEnStock(150);
		this.panier.ajouterPanier(A, 4);
		this.panier.ajouterPanier(B, 4);
		this.panier.ajouterPanier(C, 4);
		this.panier.ajouterPanier(D, 5);
		assertEquals(this.panier.getMontant(), 100, 0);
		assertEquals(this.panier.totalAvecExpedition("chronopost"), 112, 0);
	}

	@Test
	public void testAjouterPanier() {
		this.panier = new Panier();
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(1020);
		this.panier.ajouterPanier(A, 4);
		assertEquals(this.panier.getMontant(), 20, 0);
		assertEquals(this.panier.getQuantité().size(), 1);
		assertEquals(this.panier.getPanier().size(), 1);
	}

	@Test
	public void testAjouterPanierPlusieursArticle() {
		this.panier = new Panier();
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(1020);
		this.panier.ajouterPanier(A, 4);
		Article B = new Article(new Fromage("Test2"), "f", 5.0f);
		B.setQuantitéEnStock(1020);
		this.panier.ajouterPanier(B, 4);
		Article C = new Article(new Fromage("Test3"), "g", 5.0f);
		C.setQuantitéEnStock(1020);
		this.panier.ajouterPanier(C, 4);
		Article D = new Article(new Fromage("Test4"), "h", 5.0f);
		D.setQuantitéEnStock(1020);
		this.panier.ajouterPanier(D, 4);
		assertEquals(this.panier.getPanier().size(), 4);
	}

	@Test
	public void testAjouterPanierArticleEnCommun() {
		this.panier = new Panier();
		Article A = new Article(new Fromage("Test2"), "h", 5.0f);
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(134);
		B.setQuantitéEnStock(393);
		this.panier.ajouterPanier(B, 1);
		this.panier.ajouterPanier(A, 3);
		this.panier.ajouterPanier(B, 3);
		this.panier.ajouterPanier(B, 3);
		assertEquals(this.panier.getPanier().size(), 2);
	}

	@Test
	public void testAjouterPanierPlusieursArticleEnCommun() {
		this.panier = new Panier();
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		Article A = new Article(new Fromage("Test2"), "o", 5.0f);
		A.setQuantitéEnStock(192);
		B.setQuantitéEnStock(192);
		this.panier.ajouterPanier(B, 1);
		this.panier.ajouterPanier(A, 3);
		this.panier.ajouterPanier(B, 3);
		this.panier.ajouterPanier(B, 3);
		this.panier.ajouterPanier(A, 3);
		this.panier.ajouterPanier(A, 1);
		assertEquals(this.panier.getPanier().size(), 2);
	}

	@Test
	public void testModificationQuantitéEnStock() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 3);
		assertEquals(B.getQuantitéEnStock(), 27);
	}

	@Test
	public void testModificationQuantitéEnStockPlusieursFoisIdentiques() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 9);
		this.panier.ajouterPanier(B, 2);
		this.panier.ajouterPanier(B, 10);
		assertEquals(B.getQuantitéEnStock(), 9);
	}

	@Test
	public void testModificationQuantitéEnStockPlusieursFoisIdentiquesEtQuantitéSupérieurALaQuantitéEnStock() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 9);
		this.panier.ajouterPanier(B, 2);
		this.panier.ajouterPanier(B, 10);
		this.panier.ajouterPanier(B, 10);
		assertEquals(21.0f, this.panier.getQuantité().get(0), 0);
	}

	@Test
	public void testisPanierEmpty() {
		assertTrue(this.panier.isPanierEmpty());
	}

	@Test
	public void testisPanierEmptyFaux() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(40);
		this.panier.ajouterPanier(B, 3);
		assertFalse(this.panier.isPanierEmpty());
	}

}
