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
    	ImageIcon img = new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png");
    	setIconImage(img.getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 516, 362);
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
        lblIconeFacture.setIcon(new ImageIcon("C:\\Users\\chari\\AppData\\Local\\Microsoft\\Windows\\INetCache\\IE\\F3GSCC8E\\Image_Pasted_2023-23-05_at_16.44.14[1].png"));
        panelTitre.add(lblIconeFacture);

        JScrollPane scrollPane = new JScrollPane();
        this.contentPane.add(scrollPane, BorderLayout.CENTER);

//        JTextPane facture = new JTextPane();
//        facture.setEditable(false);
//        facture.setText("INFORMATIONS CLIENT: " + nom + " " + prenom + "\n" + adresse1 + "\n" + adresse2 + "\n" + CP
//                + " " + Ville + "\n" + "Téléphone: " + Telephone + "\n" + "E-mail: " + Mail + "\n"
//                + "Moyen de paiement par " + moyenDePaiement + "\n");
//        // Récupérer le modèle de table et construire la chaîne de caractères
//        JTable table = this.votrePanier.getTable();
//        StringBuilder tableData = new StringBuilder();
//        for (int row = 0; row < table.getRowCount(); row++) {
//            for (int col = 0; col < table.getColumnCount(); col++) {
//                tableData.append(table.getValueAt(row, col)).append(" ");
//            }
//            tableData.append("\n");
//        }
//        facture.setText(facture.getText() + "\n" + tableData.toString());
//        facture.setText(facture.getText() + "\n\n" + "Votre Commande : " + panier.getMontant() + "\n"
//                + "Expédition (forfait) : " + panier.fraisDeLivraison( (String) this.votrePanier.getComboBoxTransporteur().getSelectedItem()) 
//                + "\n" + "Prix Total TTC : " + panier.totalAvecExpedition(moyenDePaiement));
        
        JTextPane facture = new JTextPane();
        facture.setContentType("text/html");
        facture.setEditable(false);

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        htmlContent.append("<h1>FACTURE FROMAGERIE Fro Le Mage<h1>");
        htmlContent.append("<h2>INFORMATIONS CLIENT</h2>");
        htmlContent.append("<p>").append(prenom).append(" ").append(nom).append("<br>");
        htmlContent.append(adresse1).append("<br>");
        htmlContent.append(adresse2).append("<br>");
        htmlContent.append(CP).append(" ").append(Ville).append("<br>");
        htmlContent.append("Téléphone: ").append(Telephone).append("<br>");
        htmlContent.append("E-mail: ").append(Mail).append("<br>");
        htmlContent.append("Paiement par ").append(moyenDePaiement).append("</p>");

        htmlContent.append("<h2>VOTRE PANIER</h2>");
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
                    htmlContent.append("<td><img src='file:").append(value.toString()).append("' width='100' height='100'></td>");
                } else {
                    htmlContent.append("<td>").append(value).append("</td>");
                }
            }
            htmlContent.append("</tr>");
        }
        htmlContent.append("</table>");

        htmlContent.append("<h2>RÉCAPITULATIF</h2>");
        htmlContent.append("<p>Votre Commande : ").append(formatFloat(panier.getMontant())).append("€").append("<br>");
        htmlContent.append("Expédition (forfait) : ").append(formatFloat(panier.fraisDeLivraison((String) this.votrePanier.getComboBoxTransporteur().getSelectedItem()))).append("€").append("<br>");
        htmlContent.append("<strong>Prix Total TTC : ").append(formatFloat(panier.totalAvecExpedition((String) this.votrePanier.getComboBoxTransporteur().getSelectedItem()))).append("€").append("</strong></p>");
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