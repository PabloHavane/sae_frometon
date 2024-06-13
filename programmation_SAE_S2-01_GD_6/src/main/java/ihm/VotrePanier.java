package ihm;
import modele.Article;
import modele.Panier;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class VotrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSousTotal;
	private JTextField textFieldExpedition;
	private JTextField textFieldTotal;
	private DefaultTableModel modeleTable;
	private JTable table;
	private Panier panier;
	private NosFromages nosFromages;
	private JComboBox<String> comboBoxTransporteur;

	/**
	 * Create the frame.
	 */
	public VotrePanier(Panier lePanier, NosFromages nf) {
		this.panier = lePanier;
		this.nosFromages = nf;
		
		setTitle("La Cave à Frometon");
    	ImageIcon img = new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png");
    	setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitre = new JPanel();
		contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelTitreEtIcone = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panelTitreEtIcone.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelTitre.add(panelTitreEtIcone);
		
		JLabel lblIconePanier = new JLabel(" ");
		lblIconePanier.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\panier.png"));
		panelTitreEtIcone.add(lblIconePanier);

		JLabel lblTitre = new JLabel("Votre panier");
		lblTitre.setForeground(new Color(255, 128, 64));
		lblTitre.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		panelTitreEtIcone.add(lblTitre);
		
		JPanel panelRafraichir = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRafraichir.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelTitre.add(panelRafraichir);
		
		JButton btnRafraichir = new JButton("");
		btnRafraichir.addActionListener(rafraichirLePanier());
		btnRafraichir.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\rafraichir.png"));
		panelRafraichir.add(btnRafraichir);
		
		JButton btnSupprLignePanier = new JButton(" ");
		btnSupprLignePanier.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\poubelle.png"));
		btnSupprLignePanier.addActionListener(supprimerLignePanier());
		panelRafraichir.add(btnSupprLignePanier);
		
		JScrollPane scrollPane = new JScrollPane();
        this.contentPane.add(scrollPane, BorderLayout.CENTER);

        this.modeleTable = new DefaultTableModel(
                new Object[][] { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, },
                new String[] { "Produit", "Prix", "Quantit\u00E9", "Total" });
        this.table = new JTable(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		" ", "Produit", "Prix", "Quantit\u00E9", "Total"
        	}
        ));
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionBackground(Color.YELLOW);
        table.setSelectionForeground(Color.RED);

        
        // Afficher l'image dans le JTable
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                	JLabel label = new JLabel((ImageIcon) value);
                    table.setRowHeight(row, ((ImageIcon) value).getIconHeight());
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        
        recupArticlesPanier();
        
        // Centrer le texte des cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        this.table.setEnabled(true);
        scrollPane.setViewportView(this.table);
		
		JPanel panelTotalEtBoutons = new JPanel();
		contentPane.add(panelTotalEtBoutons, BorderLayout.SOUTH);
		panelTotalEtBoutons.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_total = new JPanel();
		panel_total.setAlignmentY(0.0f);
		panel_total.setAlignmentX(1.0f);
		panelTotalEtBoutons.add(panel_total, BorderLayout.CENTER);
		panel_total.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelTransporteur = new JPanel();
		panelTransporteur.setForeground(Color.BLACK);
		panelTransporteur.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Transporteur", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
		panelTransporteur.setBackground(UIManager.getColor("Button.background"));
		panel_total.add(panelTransporteur);
		panelTransporteur.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblFdp = new JLabel("Frais de port offert à partir de 120€");
		lblFdp.setForeground(new Color(0, 0, 0));
		lblFdp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelTransporteur.add(lblFdp);
		
		JPanel panelChoixTrans = new JPanel();
		panelTransporteur.add(panelChoixTrans);
		
		JLabel lblIconeTransporteur = new JLabel();
		lblIconeTransporteur.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\colissimo.png"));
		panelChoixTrans.add(lblIconeTransporteur);
		
		comboBoxTransporteur = new JComboBox<String>();
		comboBoxTransporteur.addActionListener(changementIconeTransEtFdp(lblIconeTransporteur, comboBoxTransporteur));
		comboBoxTransporteur.setModel(new DefaultComboBoxModel(new String[] {"Colissimo", "Chronorelais", "Chronofresh"}));
		panelChoixTrans.add(comboBoxTransporteur);
		
		JPanel panelPrix = new JPanel();
		panel_total.add(panelPrix);
		panelPrix.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSousTotal = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelSousTotal.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelPrix.add(panelSousTotal);
		
		JLabel lblSousTotal = new JLabel("Sous-total :");
		lblSousTotal.setFont(UIManager.getFont("Label.font"));
		panelSousTotal.add(lblSousTotal);
		
		textFieldSousTotal = new JTextField();
		textFieldSousTotal.setBackground(new Color(254, 250, 216));
		textFieldSousTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldSousTotal.setEditable(false);
		textFieldSousTotal.setColumns(10);
		textFieldSousTotal.setText(formatFloat(this.panier.getMontant()) + " €");
		panelSousTotal.add(textFieldSousTotal);
		
		JPanel panelExpedition = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelExpedition.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panelPrix.add(panelExpedition);
		
		JLabel lblExpedition = new JLabel("Expédition :");
		lblExpedition.setHorizontalAlignment(SwingConstants.TRAILING);
		lblExpedition.setFont(UIManager.getFont("Label.font"));
		panelExpedition.add(lblExpedition);
		
		textFieldExpedition = new JTextField();
		textFieldExpedition.setBackground(new Color(254, 250, 216));
		textFieldExpedition.setEditable(false);
		textFieldExpedition.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldExpedition.setColumns(10);
		textFieldExpedition.setText(formatFloat(this.panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
		panelExpedition.add(textFieldExpedition);
		
		JPanel panelTotal = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelTotal.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelPrix.add(panelTotal);
		
		JLabel lblTotal = new JLabel("TOTAL A PAYER :");
		lblTotal.setForeground(new Color(244, 164, 96));
		lblTotal.setBackground(new Color(255, 255, 255));
		lblTotal.setFont(UIManager.getFont("Label.font"));
		panelTotal.add(lblTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);
		textFieldTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldTotal.setBackground(new Color(245, 218, 171));
		textFieldTotal.setText(formatFloat(this.panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
		panelTotal.add(textFieldTotal);
		
		JPanel panel_button = new JPanel();
		panelTotalEtBoutons.add(panel_button, BorderLayout.SOUTH);
		panel_button.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnValider = new JButton("Valider le panier");
		btnValider.addActionListener(validationDuPanier(this));
		panel_button.add(btnValider);
		
		JButton btnVider = new JButton("Vider le panier");
		btnVider.addActionListener(validerViderPanier());
		panel_button.add(btnVider);
		
		JButton btnContinuer = new JButton("Continuer mes achats");
		btnContinuer.addActionListener(continuerAchatsFermeturePanier());
		panel_button.add(btnContinuer);
	}
	
	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the comboBoxTransporteur
	 */
	public JComboBox<String> getComboBoxTransporteur() {
		return comboBoxTransporteur;
	}

	private ActionListener supprimerLignePanier() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Article article = panier.getPanier().get(selectedRow);
                    panier.supprimerUnArticlePanier(article);
                    if (panier.isPanierEmpty()) {
                    	nosFromages.getBtnPanier().setText(formatFloat(panier.getMontant()) + " €");
                    	dispose();
                    }
                    recupArticlesPanier();
                    textFieldSousTotal.setText(formatFloat(panier.getMontant()) + " €");
    				textFieldExpedition.setText(formatFloat(panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
    				textFieldTotal.setText(formatFloat(panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
    				nosFromages.getBtnPanier().setText(formatFloat(panier.getMontant()) + " €");
                } else {
                    JOptionPane.showMessageDialog(VotrePanier.this, "Aucun article sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
			}
		};
	}

	private ActionListener validationDuPanier(VotrePanier vp) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!panier.isPanierEmpty()) {
            		VosCoordonnees frameCoor = new VosCoordonnees(panier, vp);
            		frameCoor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    frameCoor.setVisible(true);
            	}
			}
		};
	}
	
	private String formatFloat(float value) {
	    NumberFormat formatter = new DecimalFormat("#0.00");
	    return formatter.format(value);
	}



	private ActionListener rafraichirLePanier() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recupArticlesPanier();
				textFieldSousTotal.setText(formatFloat(panier.getMontant()) + " €");
				textFieldExpedition.setText(formatFloat(panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
				textFieldTotal.setText(formatFloat(panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
			}
		};
	}
	
	private void recupArticlesPanier() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Réinitialiser le modèle de table

	    for (int i = 0; i < this.panier.getPanier().size(); i++) {
	        Article article = this.panier.getPanier().get(i);
	        
	        // Ajouter une nouvelle ligne au modèle de table avec les informations de l'article
	        model.addRow(new Object[] {
	            new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\hauteur40\\" + article.getFromage().getNomImage() + ".jpg"),
	            article.getFromage().getDésignation() + " " + article.getClé(),
	            formatFloat(article.getPrixTTC()) + " €",
	            this.panier.getQuantité().get(i),
	            formatFloat(article.getPrixTTC() * this.panier.getQuantité().get(i)) + " €"
	        });
	    }
	}


	private ActionListener validerViderPanier() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(VotrePanier.this, "Voulez-vous vraiment vider le panier ?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (choix == JOptionPane.YES_OPTION) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					panier.viderPanier();
					nosFromages.getBtnPanier().setText(formatFloat(panier.getMontant()) + " €");
					textFieldSousTotal.setText(formatFloat(panier.getMontant()) + " €");
					textFieldExpedition.setText(formatFloat(panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
					textFieldTotal.setText(formatFloat(panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
					dispose();
				} else if (choix == JOptionPane.NO_OPTION) {
				    dispose();
				} else if (choix == JOptionPane.CANCEL_OPTION) {
				    // Ne rien faire
				}
			}
		};
	}

	private ActionListener continuerAchatsFermeturePanier() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	}

	private ActionListener changementIconeTransEtFdp(JLabel lblIconeTransporteur,
			JComboBox<String> comboBoxTransporteur) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxTransporteur.getSelectedIndex()) {
					case 0 :
						lblIconeTransporteur.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\colissimo.png"));
						textFieldExpedition.setText(formatFloat(panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
						textFieldTotal.setText(formatFloat(panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
						break;
					case 1 :
						lblIconeTransporteur.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\chronorelais.png"));
						textFieldExpedition.setText(formatFloat(panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
						textFieldTotal.setText(formatFloat(panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
						break;
					case 2 :
						lblIconeTransporteur.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\chronofresh.png"));
						textFieldExpedition.setText(formatFloat(panier.fraisDeLivraison((String) comboBoxTransporteur.getSelectedItem())) + " €");
						textFieldTotal.setText(formatFloat(panier.totalAvecExpedition((String) comboBoxTransporteur.getSelectedItem())) + " €");
						break;
				}
			}
		};
	}

}