package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.*;
import javax.swing.SwingConstants;

public class VosCoordonnees extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldAdresse;
	private JTextField textFieldAdresseComp;
	private JTextField textFieldCP;
	private JTextField textFieldVille;
	private JTextField textFieldTelephone;
	private JTextField textFieldMail;
	private JRadioButton CarteDeCredit;
	private JRadioButton Paypal;
	private JRadioButton PaiementParCheque;
	private JRadioButton Oui;
	private JRadioButton Non;
	@SuppressWarnings("unused")
	private String moyenDePaiement;
	@SuppressWarnings("unused")
	private Panier panier;
	private VotrePanier votrePanier;

	/**
	 * Create the frame.
	 */
	public VosCoordonnees(Panier lePanier, VotrePanier vp) {
		this.panier = lePanier;
		this.votrePanier = vp;
		setTitle("La Cave à Frometon");
		ImageIcon img = new ImageIcon(
				"C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png");
		setIconImage(img.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 400);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelTitre = new JPanel();
		this.contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel TitreCoordonées = new JLabel("Vos Coordonnées ");
		TitreCoordonées.setForeground(new Color(255, 128, 0));
		TitreCoordonées.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		panelTitre.add(TitreCoordonées);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Maison.png"));
		panelTitre.add(lblNewLabel);

		JPanel panelChamps = new JPanel();
		this.contentPane.add(panelChamps, BorderLayout.CENTER);
		panelChamps.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblNom);

		this.textFieldNom = new JTextField();
		textFieldNom.setHorizontalAlignment(SwingConstants.LEFT);
		this.textFieldNom.setMinimumSize(new Dimension(10, 19));
		panelChamps.add(this.textFieldNom);
		this.textFieldNom.setColumns(10);

		JLabel lblPrenom = new JLabel("Prénom :");
		lblPrenom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblPrenom);

		this.textFieldPrenom = new JTextField();
		panelChamps.add(this.textFieldPrenom);
		this.textFieldPrenom.setColumns(10);

		JLabel lblAdresse = new JLabel("Adresse 1 : ");
		lblAdresse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblAdresse);

		this.textFieldAdresse = new JTextField();
		panelChamps.add(this.textFieldAdresse);
		this.textFieldAdresse.setColumns(10);

		JLabel lblAdresseComp = new JLabel("Adresse complémentaire : ");
		lblAdresseComp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdresseComp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresseComp.setMinimumSize(new Dimension(5, 7));
		panelChamps.add(lblAdresseComp);

		this.textFieldAdresseComp = new JTextField();
		panelChamps.add(this.textFieldAdresseComp);
		this.textFieldAdresseComp.setColumns(10);

		JLabel lblCP = new JLabel("Code Postal :");
		lblCP.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblCP);

		this.textFieldCP = new JTextField();
		panelChamps.add(this.textFieldCP);
		this.textFieldCP.setColumns(10);

		JLabel lblVille = new JLabel("Ville :");
		lblVille.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVille.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblVille);

		this.textFieldVille = new JTextField();
		panelChamps.add(this.textFieldVille);
		this.textFieldVille.setColumns(10);

		JLabel lblTelephone = new JLabel("Téléphone :");
		lblTelephone.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelephone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblTelephone);

		this.textFieldTelephone = new JTextField();
		panelChamps.add(this.textFieldTelephone);
		this.textFieldTelephone.setColumns(10);

		JLabel lblNewLabelMail = new JLabel("Mail :");
		lblNewLabelMail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabelMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelChamps.add(lblNewLabelMail);

		this.textFieldMail = new JTextField();
		panelChamps.add(this.textFieldMail);
		this.textFieldMail.setColumns(10);

		JPanel panelBtn = new JPanel();
		this.contentPane.add(panelBtn, BorderLayout.SOUTH);
		panelBtn.setLayout(new BorderLayout(0, 0));

		JPanel MoyenPaiement = new JPanel();
		MoyenPaiement.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Moyen de paiement",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
		panelBtn.add(MoyenPaiement, BorderLayout.NORTH);
		MoyenPaiement.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.CarteDeCredit = new JRadioButton("Carte de crédit");
		this.CarteDeCredit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VosCoordonnees.this.CarteDeCredit.isSelected()) {
					VosCoordonnees.this.Paypal.setSelected(false);
					VosCoordonnees.this.PaiementParCheque.setSelected(false);
					VosCoordonnees.this.moyenDePaiement = "carte de crédit";
				} else {
					VosCoordonnees.this.moyenDePaiement = null;
				}
			}
		});
		MoyenPaiement.add(this.CarteDeCredit);

		this.Paypal = new JRadioButton("Paypal");
		this.Paypal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VosCoordonnees.this.Paypal.isSelected()) {
					VosCoordonnees.this.CarteDeCredit.setSelected(false);
					VosCoordonnees.this.PaiementParCheque.setSelected(false);
					VosCoordonnees.this.moyenDePaiement = "Paypal";
				} else {
					VosCoordonnees.this.moyenDePaiement = null;
				}
			}
		});
		MoyenPaiement.add(this.Paypal);

		this.PaiementParCheque = new JRadioButton("Paiement par chèque");
		this.PaiementParCheque.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VosCoordonnees.this.PaiementParCheque.isSelected()) {
					VosCoordonnees.this.CarteDeCredit.setSelected(false);
					VosCoordonnees.this.Paypal.setSelected(false);
					VosCoordonnees.this.moyenDePaiement = "chèque";
				} else {
					VosCoordonnees.this.moyenDePaiement = null;
				}
			}
		});
		MoyenPaiement.add(this.PaiementParCheque);

		JPanel Newsletter = new JPanel();
		Newsletter.setBorder(
				new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Abonnement \u00E0 notre Newsletter",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
		panelBtn.add(Newsletter, BorderLayout.CENTER);

		this.Oui = new JRadioButton("Oui");
		this.Oui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VosCoordonnees.this.Oui.isSelected()) {
					VosCoordonnees.this.Non.setSelected(false);
				}
			}
		});
		Newsletter.add(this.Oui);

		this.Non = new JRadioButton("Non");
		this.Non.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VosCoordonnees.this.Non.isSelected()) {
					VosCoordonnees.this.Oui.setSelected(false);
				}
			}

		});
		Newsletter.add(this.Non);

		JPanel Boutons = new JPanel();
		Boutons.setBounds(new Rectangle(3, 3, 3, 3));
		FlowLayout fl_Boutons = (FlowLayout) Boutons.getLayout();
		fl_Boutons.setAlignment(FlowLayout.RIGHT);
		panelBtn.add(Boutons, BorderLayout.SOUTH);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(genererFacture());
		btnOK.setToolTipText("OK");
		Boutons.add(btnOK);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(fermerFenCoor());
		Boutons.add(btnAnnuler);
	}

	private ActionListener fermerFenCoor() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VosCoordonnees.this.dispose();
			}
		};
	}

	private boolean champsRemplis() {
		return !textFieldNom.getText().isEmpty() && !textFieldPrenom.getText().isEmpty() && !textFieldAdresse.getText().isEmpty()
				&& !textFieldCP.getText().isEmpty() && !textFieldVille.getText().isEmpty()
				&& !textFieldTelephone.getText().isEmpty() && !textFieldMail.getText().isEmpty()
				&& VosCoordonnees.this.moyenDePaiement != null
				&& (VosCoordonnees.this.Non.isSelected() || VosCoordonnees.this.Oui.isSelected());
	}

	private ActionListener genererFacture() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (champsRemplis()) {
					String nom = VosCoordonnees.this.textFieldNom.getText();
					String prenom = VosCoordonnees.this.textFieldPrenom.getText();
					String adresse1 = VosCoordonnees.this.textFieldAdresse.getText();
					String adresse2 = VosCoordonnees.this.textFieldAdresseComp.getText();
					String CP = VosCoordonnees.this.textFieldCP.getText();
					String Ville = VosCoordonnees.this.textFieldVille.getText();
					String Telephone = VosCoordonnees.this.textFieldTelephone.getText();
					String Mail = VosCoordonnees.this.textFieldMail.getText();
					VotreFacture frame = new VotreFacture(nom, prenom, adresse1, adresse2, CP, Ville, Telephone, Mail,
							VosCoordonnees.this.moyenDePaiement, panier, votrePanier);
					frame.setVisible(true);
				} else {
					String messageErreur = new String("Au moins un champ n'a pas été rempli correctement \n (à l'exception d'adresse complémentaire)");
					JOptionPane.showMessageDialog(VosCoordonnees.this, messageErreur, "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
}