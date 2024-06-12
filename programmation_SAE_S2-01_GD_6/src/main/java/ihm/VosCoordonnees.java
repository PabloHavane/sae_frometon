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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
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

		JPanel haut = new JPanel();
		this.contentPane.add(haut, BorderLayout.NORTH);
		haut.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel TitreCoordonées = new JLabel("Vos Coordonnées");
		TitreCoordonées.setForeground(new Color(255, 128, 0));
		TitreCoordonées.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		haut.add(TitreCoordonées);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Maison.png"));
		haut.add(lblNewLabel);

		JPanel centre = new JPanel();
		this.contentPane.add(centre, BorderLayout.CENTER);
		centre.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Nom :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblNewLabel_2);

		this.textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField.setMinimumSize(new Dimension(10, 19));
		centre.add(this.textField);
		this.textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Prénom :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblNewLabel_3);

		this.textField_1 = new JTextField();
		centre.add(this.textField_1);
		this.textField_1.setColumns(10);

		JLabel lblAdresse = new JLabel("Adresse 1 : ");
		lblAdresse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblAdresse);

		this.textField_2 = new JTextField();
		centre.add(this.textField_2);
		this.textField_2.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Adresse complémentaire : ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setMinimumSize(new Dimension(5, 7));
		centre.add(lblNewLabel_4);

		this.textField_3 = new JTextField();
		centre.add(this.textField_3);
		this.textField_3.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Code Postal :");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblNewLabel_5);

		this.textField_4 = new JTextField();
		centre.add(this.textField_4);
		this.textField_4.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Ville :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblNewLabel_6);

		this.textField_5 = new JTextField();
		centre.add(this.textField_5);
		this.textField_5.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Téléphone :");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblNewLabel_7);

		this.textField_6 = new JTextField();
		centre.add(this.textField_6);
		this.textField_6.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Mail :");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		centre.add(lblNewLabel_8);

		this.textField_7 = new JTextField();
		centre.add(this.textField_7);
		this.textField_7.setColumns(10);

		JPanel bas = new JPanel();
		this.contentPane.add(bas, BorderLayout.SOUTH);
		bas.setLayout(new BorderLayout(0, 0));

		JPanel MoyenPaiement = new JPanel();
		MoyenPaiement.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Moyen de paiement",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
		bas.add(MoyenPaiement, BorderLayout.NORTH);
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
		bas.add(Newsletter, BorderLayout.CENTER);

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
		bas.add(Boutons, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(genererFacture());
		btnNewButton.setToolTipText("OK");
		Boutons.add(btnNewButton);

		JButton Annuler = new JButton("Annuler");
		Annuler.addActionListener(fermerFenCoor());
		Boutons.add(Annuler);
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
		return !textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()
				&& !textField_4.getText().isEmpty() && !textField_5.getText().isEmpty()
				&& !textField_6.getText().isEmpty() && !textField_7.getText().isEmpty()
				&& VosCoordonnees.this.moyenDePaiement != null
				&& (VosCoordonnees.this.Non.isSelected() || VosCoordonnees.this.Oui.isSelected());
	}

	private ActionListener genererFacture() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (champsRemplis()) {
					String nom = VosCoordonnees.this.textField.getText();
					String prenom = VosCoordonnees.this.textField_1.getText();
					String adresse1 = VosCoordonnees.this.textField_2.getText();
					String adresse2 = VosCoordonnees.this.textField_3.getText();
					String CP = VosCoordonnees.this.textField_4.getText();
					String Ville = VosCoordonnees.this.textField_5.getText();
					String Telephone = VosCoordonnees.this.textField_6.getText();
					String Mail = VosCoordonnees.this.textField_7.getText();
					VotreFacture frame = new VotreFacture(nom, prenom, adresse1, adresse2, CP, Ville, Telephone, Mail,
							VosCoordonnees.this.moyenDePaiement, panier, votrePanier);
					frame.setVisible(true);
				} else {
					ChampManquant cm = new ChampManquant();
					cm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					cm.setVisible(true);
				}
			}
		};
	}
}