package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import modele.*;

public class VotreFacture extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Fromage fromage;
	@SuppressWarnings("unused")
	private Panier panier;
	private VotrePanier votrePanier;

	public VotreFacture(String nom, String prenom, String adresse1, String adresse2, String CP, String Ville,
			String Telephone, String Mail, String moyenDePaiement, Panier panier, VotrePanier vp) {
		this.panier = panier;
		this.votrePanier = vp;
		setTitle("La Cave à Frometon");
		ImageIcon img = new ImageIcon(
				"C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png");
		setIconImage(img.getImage());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 516, 550);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelTitre = new JPanel();
		panelTitre.setBackground(new Color(255, 255, 255));
		this.contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setPreferredSize(new Dimension(100, 40));
		panelTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTitre = new JLabel("Votre facture");
		lblTitre.setForeground(new Color(0, 128, 0));
		lblTitre.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblTitre.setToolTipText("");
		panelTitre.add(lblTitre);

		JLabel lblIconeFacture = new JLabel("");
		lblIconeFacture.setIcon(new ImageIcon(
				"C:\\Users\\chari\\AppData\\Local\\Microsoft\\Windows\\INetCache\\IE\\F3GSCC8E\\Image_Pasted_2023-23-05_at_16.44.14[1].png"));
		panelTitre.add(lblIconeFacture);

		JScrollPane scrollPane = new JScrollPane();
		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		JTextPane facture = new JTextPane();
		facture.setContentType("text/html");
		facture.setEditable(false);

		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<html><body>");
		htmlContent.append(
				"<img src='file:C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png'>");
		htmlContent.append("<h2>FACTURE La Cave à Frometon<h2>");
		htmlContent.append("<p>Commande du ").append(getCurrentDateTime()).append("</p>");
		htmlContent.append("<h3>INFORMATIONS CLIENT</h3>");
		htmlContent.append("<p>").append(prenom).append(" ").append(nom).append("<br>");
		htmlContent.append("Adresse : <em>").append(adresse1).append("</em><br>");
		htmlContent.append("Adresse complémentaire : <em>").append(adresse2).append("<br>");
		htmlContent.append(CP).append(" ").append(Ville).append("</em><br>");
		htmlContent.append("Téléphone: <em>").append(Telephone).append("</em><br>");
		htmlContent.append("E-mail: <em>").append(Mail).append("</em><br>");
		htmlContent.append("Paiement par ").append(moyenDePaiement).append("</p>");

		htmlContent.append("<h3>VOTRE PANIER</h3>");
		htmlContent.append("<table border='1'><tr>");

		JTable table = this.votrePanier.getTable();

		for (int col = 0; col < table.getColumnCount(); col++) {
			htmlContent.append("<th>").append(table.getColumnName(col)).append("</th>");
		}
		htmlContent.append("</tr>");

		for (int row = 0; row < table.getRowCount(); row++) {
			htmlContent.append("<tr>");
			for (int col = 0; col < table.getColumnCount(); col++) {
				Object value = table.getValueAt(row, col);
				if (value != null && value.toString().endsWith(".jpg")) {
					htmlContent.append("<td><img src='file:").append(value.toString())
							.append("' width='100' height='100'></td>");
				} else {
					htmlContent.append("<td>").append(value).append("</td>");
				}
			}
			htmlContent.append("</tr>");
		}
		htmlContent.append("</table>");

		String expediteur = (String) this.votrePanier.getComboBoxTransporteur().getSelectedItem();
		htmlContent.append("<h3>RÉCAPITULATIF</h3>");
		htmlContent.append("<p>Total Commande TTC : ").append(formatFloat(panier.getMontant())).append("€")
				.append("<br>");
		htmlContent.append("Expédition via ").append(expediteur).append(" : ")
				.append(formatFloat(panier.fraisDeLivraison(expediteur))).append("€").append("<br>");
		htmlContent.append("<strong>PRIX TOTAL TTC : ").append(formatFloat(panier.totalAvecExpedition(expediteur)))
				.append("€").append("</strong></p>");
		htmlContent.append("</body></html>");

		facture.setText(htmlContent.toString());
		scrollPane.setViewportView(facture);

		JPanel panelBoutons = new JPanel();
		panelBoutons.setBackground(new Color(255, 255, 255));
		this.contentPane.add(panelBoutons, BorderLayout.SOUTH);
		panelBoutons.setPreferredSize(new Dimension(100, 40));

		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(imprimerFacture(facture));

		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBoutons.add(btnImprimer);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(fermerApplication());
		panelBoutons.add(btnQuitter);
	}

	private String getCurrentDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy à HH:mm:ss", Locale.FRENCH);
		Date date = new Date();
		return formatter.format(date);
	}

	private ActionListener fermerApplication() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		};
	}

	private String formatFloat(float value) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(value);
	}

	private ActionListener imprimerFacture(JTextPane facture) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					facture.print();
				} catch (PrinterException ex) {
					ex.printStackTrace();
				}
			}
		};
	}
}