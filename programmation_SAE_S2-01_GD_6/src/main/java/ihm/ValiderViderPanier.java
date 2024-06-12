package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class ValiderViderPanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VotrePanier votrePanier;
	private NosFromages nosFromages;

	/**
	 * Create the frame.
	 */
	public ValiderViderPanier(VotrePanier vp, NosFromages nf) {
		this.votrePanier = vp;
		this.nosFromages = nf;
		
		setTitle("La Cave à Frometon");
    	ImageIcon img = new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\cave.png");
    	setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLbl = new JPanel();
		contentPane.add(panelLbl, BorderLayout.CENTER);
		
		JLabel lblRDS = new JLabel("<html>Voulez-vous vraiment supprimer le panier ?"
				+ "<br></html>");
		lblRDS.setHorizontalAlignment(SwingConstants.CENTER);
		panelLbl.add(lblRDS);
		
		JPanel panelWarning = new JPanel();
		contentPane.add(panelWarning, BorderLayout.NORTH);
		
		JLabel lblIconeWarning = new JLabel(" ");
		lblIconeWarning.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\warning.png"));
		panelWarning.add(lblIconeWarning);
		
		JPanel panelBtn = new JPanel();
		contentPane.add(panelBtn, BorderLayout.SOUTH);
		
		JButton btnOui = new JButton("Oui");
		btnOui.addActionListener(viderLePanier());
		panelBtn.add(btnOui);
		
		JButton btnNon = new JButton("Non");
		btnNon.addActionListener(fermertureFenVVPEtVP());
		panelBtn.add(btnNon);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(annulationViderPanierEtFermerFen());
		panelBtn.add(btnAnnuler);
	}

	private ActionListener fermertureFenVVPEtVP() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				votrePanier.dispose();
			}
		};
	}

	private ActionListener viderLePanier() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) votrePanier.getTable().getModel();
				model.setRowCount(0);
				votrePanier.getPanier().viderPanier();
				nosFromages.getBtnPanier().setText(formatFloat(votrePanier.getPanier().getMontant()) + " €");
				votrePanier.getTextFieldSousTotal().setText(formatFloat(votrePanier.getPanier().getMontant()) + " €");
				votrePanier.getTextFieldExpedition().setText(formatFloat(votrePanier.getPanier().fraisDeLivraison((String) votrePanier.getComboBoxTransporteur().getSelectedItem())) + " €");
				votrePanier.getTextFieldTotal().setText(formatFloat(votrePanier.getPanier().totalAvecExpedition((String) votrePanier.getComboBoxTransporteur().getSelectedItem())) + " €");
				dispose();
				votrePanier.dispose();
			}
		};
	}

	private ActionListener annulationViderPanierEtFermerFen() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	}
	
	private String formatFloat(float value) {
	    NumberFormat formatter = new DecimalFormat("#0.00");
	    return formatter.format(value);
	}

}
