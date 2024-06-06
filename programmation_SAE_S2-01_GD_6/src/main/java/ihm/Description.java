package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import modele.Article;
import modele.Fromage;
import modele.Panier;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Description extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Fromage fromage;
	private Panier panier;
	private JSpinner spinner;
	private JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 */
	public Description(Fromage leFromage, Panier lePanier) {
		this.fromage = leFromage;
		this.panier = lePanier;
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelClic = new JPanel();
		contentPane.add(panelClic, BorderLayout.SOUTH);
		panelClic.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelComboSpin = new JPanel();
		panelClic.add(panelComboSpin);
		
		DefaultComboBoxModel<String> modele = new DefaultComboBoxModel<>();
        for (Article article : this.fromage.getArticles()) {
            modele.addElement(article.toString());
        }
        comboBox = new JComboBox<>(modele);
		panelComboSpin.add(comboBox);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, this.fromage.getArticles().get(0).getQuantitéEnStock(), 1));
		panelComboSpin.add(spinner);
		
		JPanel panelBtn = new JPanel();
		panelClic.add(panelBtn);
		
		JButton btnAjoutPanier = new JButton("Ajouter au panier");
		btnAjoutPanier.addActionListener(ajoutArticlePanier());
		panelBtn.add(btnAjoutPanier);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(annulationFermetureFen());
		panelBtn.add(btnAnnuler);
		
		JPanel panelImgDesc = new JPanel();
		contentPane.add(panelImgDesc, BorderLayout.CENTER);
		panelImgDesc.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelImage = new JPanel();
		panelImgDesc.add(panelImage);
		panelImage.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNomFromage = new JLabel("");
		lblNomFromage.setText(this.fromage.getDésignation());
		lblNomFromage.setForeground(new Color(255, 128, 0));
		lblNomFromage.setVerticalAlignment(SwingConstants.TOP);
		lblNomFromage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomFromage.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		panelImage.add(lblNomFromage, BorderLayout.NORTH);
		
		JLabel lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\hauteur200\\" + this.fromage.getNomImage() + ".jpg"));
		panelImage.add(lblImage, BorderLayout.CENTER);
		
		JPanel panelDescription = new JPanel();
		panelDescription.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 64, 0)));
		panelImgDesc.add(panelDescription);
		panelDescription.setLayout(new CardLayout(2, 2));
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText(this.fromage.getDescription());
		panelDescription.add(textArea, "name_1190427149665900");
	}

	private ActionListener ajoutArticlePanier() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemSelec = (String) comboBox.getSelectedItem();
				Article art = null;
        		for (Article article: fromage.getArticles()) {
                    if (article.toString().equals(itemSelec)) {
                    	art = article;
                    }
                }
        		if (art != null) {
        			panier.ajouterPanier(art, (int) spinner.getValue());
        		}
				dispose();
			}
		};
	}

	private ActionListener annulationFermetureFen() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	}

}
