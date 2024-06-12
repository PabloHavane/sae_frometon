package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.Article;
import modele.Fromage;
import modele.Fromages;
import modele.GenerationFromages;
import modele.Panier;
import modele.TypeLait;

public class NosFromages extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Fromages LISTING_ALL_FROMAGES = GenerationFromages.générationBaseFromages();
    
    private JPanel contentPane;
    private JComboBox<String> comboBoxType;
    private JList<String> listFromages;
    private Fromages fromages;
    private String typeCourant;
    private Panier panier;
    private JButton btnPanier;
    private JLabel lblIcone;

	/**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NosFromages frame = new NosFromages();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public NosFromages() {
    	setTitle("La Cave à Frometon");
    	ImageIcon img = new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png");
    	setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 452, 424);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        fromages = new Fromages();
        fromages.regénérationDuStock();
        panier = new Panier();
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane);

        // Ajouter le nom des fromages dans la liste
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Fromage fromage : LISTING_ALL_FROMAGES.getFromages()) {
            listModel.addElement(fromage.getDésignation());
        }
		listFromages = new JList<>(listModel);
        scrollPane.setViewportView(listFromages);
        
        // Ouvrir une fenetre Description
        listFromages.addMouseListener(ouvrirFromageDescription(LISTING_ALL_FROMAGES, listFromages, this));

        JPanel haut = new JPanel();
        contentPane.add(haut, BorderLayout.NORTH);
        haut.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        haut.add(panelTitre);

        JLabel lblTitre = new JLabel("Nos fromages ");
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setForeground(new Color(255, 128, 0));
        lblTitre.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        panelTitre.add(lblTitre);

        lblIcone = new JLabel("");
        lblIcone.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Fromageprepareepixel.png"));
        lblIcone.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitre.add(lblIcone);

        btnPanier = new JButton();
        getBtnPanier().setText(formatFloat(this.panier.getMontant()) + " €");
        getBtnPanier().addActionListener(ouvrirFenVotrePanier(this));

        getBtnPanier().setBackground(new Color(255, 128, 0));
        getBtnPanier().setForeground(new Color(0, 0, 0));
        getBtnPanier().setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\panier.png"));

        getBtnPanier().setHorizontalAlignment(SwingConstants.RIGHT);
        getBtnPanier().setBounds(new Rectangle(20, 0, 0, 0));
        getBtnPanier().setAlignmentX(1.0f);
        haut.add(getBtnPanier(), BorderLayout.EAST);

        JPanel bas = new JPanel();
        contentPane.add(bas, BorderLayout.SOUTH);

        JPanel panelFiltre = new JPanel();
        panelFiltre.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Filtre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
        bas.add(panelFiltre);
        panelFiltre.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));

        JLabel lblIconeBas = new JLabel("");
        lblIconeBas.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Fromage.png"));
        panelFiltre.add(lblIconeBas);

        this.comboBoxType = new JComboBox<>();
        this.comboBoxType.addItem("Tous les fromages");
        for (TypeLait t : TypeLait.values()) {
            this.comboBoxType.addItem(t.getTypeDeLait());
        }
        comboBoxType.addActionListener(trierListeSelonType());
        panelFiltre.add(this.comboBoxType);

        JButton Bouton = new JButton("Quitter");
        Bouton.setHorizontalAlignment(SwingConstants.RIGHT);
        bas.add(Bouton);
        Bouton.addActionListener(fermetureApp());
    }

	private ActionListener fermetureApp() {
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
	}

	private ActionListener trierListeSelonType() {
		return new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String typeLaitSelec = (String) comboBoxType.getSelectedItem();
        		typeCourant = typeLaitSelec;
        		if (typeCourant.equals("Vache")) {
        			lblIcone.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\vache.png"));
        		} else if (typeCourant.equals("Brebis")) {
        			lblIcone.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\brebis.png"));
        		} else if (typeCourant.equals("Chèvre")) {
        			lblIcone.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\chevre.png"));
        		} else {
        			lblIcone.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Fromageprepareepixel.png"));;
        		}
        		majListeFromage();
        	}
        };
	}
    
    private String formatFloat(float value) {
	    NumberFormat formatter = new DecimalFormat("#0.00");
	    return formatter.format(value);
	}

	private ActionListener ouvrirFenVotrePanier(NosFromages nf) {
		return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!panier.isPanierEmpty()) {
            		getBtnPanier().setText(formatFloat(panier.getMontant()) + " €"); // A SUPPR
            		VotrePanier framePanier = new VotrePanier(panier, nf);
            		framePanier.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    framePanier.setVisible(true);
            	} else {
            		PanierVide panierVide = new PanierVide();
                    panierVide.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    panierVide.setVisible(true);
            	}
            }
        };
	}
    
    private void majListeFromage() {
    	DefaultListModel<String> listModel = (DefaultListModel<String>) listFromages.getModel();
    	listModel.clear();
    	
    	for (Fromage fromage : LISTING_ALL_FROMAGES.getFromages()) {
    		if (typeCourant.equals("Tous les fromages") || fromage.getTypeFromage().getTypeDeLait().equals(typeCourant)) {
    			listModel.addElement(fromage.getDésignation());
    		}
        }
    	
    	listFromages = new JList<>(listModel);
    }

	private MouseAdapter ouvrirFromageDescription(Fromages listingFromages, JList<String> listFromages, NosFromages nf) {
		return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Fromage fromageDesc = null;
                if (e.getClickCount() == 2) {
                    String selectedValue = listFromages.getSelectedValue();
                    for (Fromage fromage : listingFromages.getFromages()) {
                        if (fromage.getDésignation() == selectedValue) {
                        	fromageDesc = fromage;
                        }
                    }
                    if (fromageDesc != null) {
                    	Description description = new Description(fromageDesc, panier, nf);
	                    description.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	                    description.setVisible(true);
                    }
                }
            }
        };
	}

	public JButton getBtnPanier() {
		return btnPanier;
	}
}
