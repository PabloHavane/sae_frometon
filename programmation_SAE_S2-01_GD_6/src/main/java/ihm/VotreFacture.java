package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

    public VotreFacture(String nom, String prenom, String adresse1, String adresse2, String CP, String Ville,
                        String Telephone, String Mail, String moyenDePaiement, Panier panier) {
        this.panier = panier;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 516, 362);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));

        JPanel haut = new JPanel();
        haut.setBackground(new Color(255, 255, 255));
        this.contentPane.add(haut, BorderLayout.NORTH);
        haut.setPreferredSize(new Dimension(100, 40));
        haut.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("Votre facture");
        lblNewLabel.setForeground(new Color(0, 128, 0));
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        lblNewLabel.setToolTipText("");
        haut.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\chari\\AppData\\Local\\Microsoft\\Windows\\INetCache\\IE\\F3GSCC8E\\Image_Pasted_2023-23-05_at_16.44.14[1].png"));
        haut.add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        this.contentPane.add(scrollPane, BorderLayout.CENTER);

        JTextPane facture = new JTextPane();
        facture.setEditable(false);
        facture.setText("INFORMATIONS CLIENT: " + nom + " " + prenom + "\n" + adresse1 + "\n" + adresse2 + "\n" + CP
                + " " + Ville + "\n" + "Téléphone: " + Telephone + "\n" + "E-mail: " + Mail + "\n"
                + "Moyen de paiement par " + moyenDePaiement + "\n");

        facture.setText(facture.getText() + "\n" + "Votre Commande : " + panier.getMontant() + "\n"
                + "Expédition (forfait) : " + panier.fraisDeLivraison("chronopost") + "\n" + "Prix Total TTC : " + panier.totalAvecExpedition(moyenDePaiement));
        scrollPane.setViewportView(facture);

        JPanel bas = new JPanel();
        bas.setBackground(new Color(255, 255, 255));
        this.contentPane.add(bas, BorderLayout.SOUTH);
        bas.setPreferredSize(new Dimension(100, 40));

        JButton btnNewButton = new JButton("Imprimer");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    facture.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });

        bas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        bas.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Quitter");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bas.add(btnNewButton_1);
    }
}