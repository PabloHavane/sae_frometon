package ihm;
import modele.Panier;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VotrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VotrePanier frame = new VotrePanier();
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
	public VotrePanier() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Votre panier");
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "Produit", "Prix", "Quantité", "total"},
				{"","E","C","D","G"},
			}, 
			new String[] {
				"", "Produit", "Prix", "Quantité", "total"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		panel_1.add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		panel_1.add(scrollBar);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setAlignmentY(0.0f);
		panel_2_1.setAlignmentX(1.0f);
		panel_2.add(panel_2_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.BLACK);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Transporteur", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
		panel_4.setBackground(UIManager.getColor("Button.background"));
		panel_2_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("frais de ports offert à partir de 120€");
		lblNewLabel_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_2 = new JLabel();
		panel_6.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		panel_6.add(comboBox);
		
		JPanel panel_5 = new JPanel();
		panel_2_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Sous total");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_7.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_7.add(textField);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		
		JLabel lblNewLabel_4 = new JLabel("Expédition");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_8.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_8.add(textField_1);
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9);
		
		JLabel lblNewLabel_5 = new JLabel("Total");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_9.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(Color.YELLOW);
		panel_9.add(textField_2);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JButton btnNewButton_1 = new JButton("Valider le panier");
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vider le panier");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		panel_3.add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Continuer les achats");
		btnNewButton_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		panel_3.add(btnNewButton_3);
	}

}