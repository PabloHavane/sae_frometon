package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class RuptureDeStock extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public RuptureDeStock() {
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
		
		JLabel lblRDS = new JLabel("<html>Vous ne pouvez plus ajouter ce produit à votre panier,"
				+ "<br>la quantité maximale a été atteinte.</html>");
		lblRDS.setHorizontalAlignment(SwingConstants.CENTER);
		panelLbl.add(lblRDS);
		
		JPanel panelWarning = new JPanel();
		contentPane.add(panelWarning, BorderLayout.NORTH);
		
		JLabel lblIconeWarning = new JLabel(" ");
		lblIconeWarning.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\warning.png"));
		panelWarning.add(lblIconeWarning);
	}

}
