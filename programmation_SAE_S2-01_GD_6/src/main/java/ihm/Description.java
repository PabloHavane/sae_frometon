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
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class Description extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Description frame = new Description();
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
	public Description() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelImage = new JPanel();
		contentPane.add(panelImage, BorderLayout.WEST);
		panelImage.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Le frometon");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelImage.add(lblNewLabel);
		
		JLabel lblImage = new JLabel("New label");
		lblImage.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\hauteur200\\bleu_d_auvergne.jpg"));
		panelImage.add(lblImage);
		
		JPanel panelDescription = new JPanel();
		panelDescription.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 64, 0)));
		contentPane.add(panelDescription, BorderLayout.CENTER);
		panelDescription.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		textField.setEditable(false);
		panelDescription.add(textField);
		textField.setColumns(10);
		
		JPanel panelClic = new JPanel();
		contentPane.add(panelClic, BorderLayout.SOUTH);
		panelClic.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelComboSpin = new JPanel();
		panelClic.add(panelComboSpin);
		
		JComboBox comboBox_1 = new JComboBox();
		panelComboSpin.add(comboBox_1);
		
		JSpinner spinner_1 = new JSpinner();
		panelComboSpin.add(spinner_1);
		
		JPanel panelBtn = new JPanel();
		panelClic.add(panelBtn);
		
		JButton btnAjoutPanier = new JButton("Ajouter au panier");
		panelBtn.add(btnAjoutPanier);
		
		JButton btnAnnuler = new JButton("Annuler");
		panelBtn.add(btnAnnuler);
	}

}
