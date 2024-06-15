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
		assertEquals(14, this.panier.getMontant(), 0.1);
	}

	@Test
	public void testFraisDeLivraisonChronofreshPanierInférieurA50() {
		assertEquals(23.8f, this.panier.fraisDeLivraison("Chronofresh"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonChronofreshPanierInférieurA80() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(193);
		this.panier.ajouterPanier(A, 12);
		assertEquals(17.8f, this.panier.fraisDeLivraison("Chronofresh"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonChronofreshPanierInférieurA120() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(193);
		this.panier.ajouterPanier(A, 20);
		assertEquals(9.9f, this.panier.fraisDeLivraison("Chronofresh"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonChronofreshPanierSupérieurA120() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(193);
		this.panier.ajouterPanier(A, 30);
		assertEquals(0, this.panier.fraisDeLivraison("Chronofresh"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonAutrePanierInférieurA60() {
		assertEquals(14.9f, this.panier.fraisDeLivraison("c"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonAutrePanierInférieurA90() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(193);
		this.panier.ajouterPanier(A, 13);
		assertEquals(9.9f, this.panier.fraisDeLivraison("c"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonAutrePanierInférieurA120() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(193);
		this.panier.ajouterPanier(A, 20);
		assertEquals(4.9f, this.panier.fraisDeLivraison("c"), 0.1);
	}

	@Test
	public void testFraisDeLivraisonAutrePanierSupérieurA120() {
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(193);
		this.panier.ajouterPanier(A, 30);
		assertEquals(0, this.panier.fraisDeLivraison("c"), 0.1);
	}

	@Test
	public void testViderPanier() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 3);
		this.panier.viderPanier();
		assertEquals(0, this.panier.getMontant(), 0);
		assertEquals(0, this.panier.getPanier().size());
		assertEquals(0, this.panier.getQuantité().size());
	}

	@Test
	public void testTotalAvecExpédition() {
		this.panier.setMontant(30);
		assertEquals(53.8, this.panier.totalAvecExpedition("Chronofresh"), 0.1);
	}

	@Test
	public void testTotalAvecExpéditionSupérieur120() {
		this.panier.setMontant(300);
		assertEquals(300, this.panier.totalAvecExpedition("Chronofresh"), 0.1);
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
		assertEquals(100, this.panier.getMontant(), 0);
		assertEquals(109.9, this.panier.totalAvecExpedition("Chronofresh"), 0.1);
	}

	@Test
	public void testAjouterPanier() {
		this.panier = new Panier();
		Article A = new Article(new Fromage("Test1"), "e", 5.0f);
		A.setQuantitéEnStock(1020);
		this.panier.ajouterPanier(A, 4);
		assertEquals(20, this.panier.getMontant(), 0);
		assertEquals(1, this.panier.getQuantité().size());
		assertEquals(1, this.panier.getPanier().size());
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
		assertEquals(4, this.panier.getPanier().size());
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
		assertEquals(2, this.panier.getPanier().size());
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
		assertEquals(2, this.panier.getPanier().size());
	}

	@Test
	public void testModificationQuantitéEnStock() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 3);
		assertEquals(27, B.getQuantitéEnStock());
	}

	@Test
	public void testModificationQuantitéEnStockPlusieursFoisIdentiques() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 9);
		this.panier.ajouterPanier(B, 2);
		this.panier.ajouterPanier(B, 10);
		assertEquals(9, B.getQuantitéEnStock());
	}

	@Test
	public void testModificationQuantitéEnStockPlusieursFoisIdentiquesEtQuantitéSupérieurALaQuantitéEnStock() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 9);
		this.panier.ajouterPanier(B, 2);
		this.panier.ajouterPanier(B, 10);
		this.panier.ajouterPanier(B, 10);
		assertEquals(30, this.panier.getQuantité().get(0), 0.1);
	}

	@Test
	public void testAjoutPanierQuantitéVide() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(30);
		this.panier.ajouterPanier(B, 0);
		assertEquals(0, this.panier.getPanier().size());
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

	@Test
	public void testSupprimerUnArticlePanierPlusieursArticleIdentiques() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(40);
		this.panier.ajouterPanier(B, 3);
		this.panier.ajouterPanier(B, 15);
		this.panier.supprimerUnArticlePanier(B);
		assertTrue(this.panier.isPanierEmpty());
	}

	@Test
	public void testSupprimerUnArticlePanierDeuxArticleDifférentDansPanier() {
		Article A = new Article(new Fromage("Test2"), "f", 5.0f);
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(40);
		A.setQuantitéEnStock(40);
		this.panier.ajouterPanier(B, 3);
		this.panier.ajouterPanier(A, 15);
		this.panier.ajouterPanier(B, 15);
		this.panier.supprimerUnArticlePanier(B);
		assertEquals(1, this.panier.getPanier().size());
	}

	@Test
	public void testRetirerDuPanier() {
		Article B = new Article(new Fromage("Test1"), "e", 5.0f);
		B.setQuantitéEnStock(45);
		this.panier.ajouterPanier(B, 15);
		this.panier.retirerDuPanier(B, 5);
		assertEquals(35, B.getQuantitéEnStock());
	}
}
