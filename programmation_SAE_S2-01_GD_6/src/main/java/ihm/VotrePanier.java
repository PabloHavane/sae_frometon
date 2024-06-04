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
import javax.swing.JScrollPane;
import java.awt.CardLayout;

public class VotrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSousTotal;
	private JTextField textFieldExpedition;
	private JTextField textFieldTotal;
	private DefaultTableModel modeleTable;
	private JTable table;

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
		setBounds(100, 100, 634, 412);
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
		
		JScrollPane scrollPane = new JScrollPane();
        this.contentPane.add(scrollPane, BorderLayout.CENTER);

        this.modeleTable = new DefaultTableModel(
                new Object[][] { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, },
                new String[] { "Produit", "Prix", "Quantit\u00E9", "Total" });
        this.table = new JTable(new DefaultTableModel(
        	new Object[][] {
        		{"Image", "frometon", "trop cher", "beaucoup", "energie"},
        	},
        	new String[] {
        		" ", "Produit", "Prix", "Quantit\u00E9", "Total"
        	}
        ));
        this.table.setEnabled(false);
        scrollPane.setViewportView(this.table);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_total = new JPanel();
		panel_total.setAlignmentY(0.0f);
		panel_total.setAlignmentX(1.0f);
		panel_2.add(panel_total, BorderLayout.CENTER);
		panel_total.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		panelChoixTrans.add(lblIconeTransporteur);
		
		JComboBox comboBoxTransporteur = new JComboBox();
		panelChoixTrans.add(comboBoxTransporteur);
		
		JPanel panel_5 = new JPanel();
		panel_total.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSousTotal = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelSousTotal.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_5.add(panelSousTotal);
		
		JLabel lblSousTotal = new JLabel("Sous-total :");
		lblSousTotal.setFont(UIManager.getFont("Label.font"));
		panelSousTotal.add(lblSousTotal);
		
		textFieldSousTotal = new JTextField();
		textFieldSousTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSousTotal.setEditable(false);
		textFieldSousTotal.setColumns(10);
		panelSousTotal.add(textFieldSousTotal);
		
		JPanel panelExpedition = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelExpedition.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_5.add(panelExpedition);
		
		JLabel lblExpedition = new JLabel("Expédition :");
		lblExpedition.setHorizontalAlignment(SwingConstants.TRAILING);
		lblExpedition.setFont(UIManager.getFont("Label.font"));
		panelExpedition.add(lblExpedition);
		
		textFieldExpedition = new JTextField();
		textFieldExpedition.setEditable(false);
		textFieldExpedition.setColumns(10);
		panelExpedition.add(textFieldExpedition);
		
		JPanel panelTotal = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelTotal.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_5.add(panelTotal);
		
		JLabel lblTotal = new JLabel("TOTAL :");
		lblTotal.setFont(UIManager.getFont("Label.font"));
		panelTotal.add(lblTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);
		textFieldTotal.setBackground(Color.YELLOW);
		panelTotal.add(textFieldTotal);
		
		JPanel panel_button = new JPanel();
		panel_2.add(panel_button, BorderLayout.SOUTH);
		panel_button.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnValider = new JButton("Valider le panier");
		panel_button.add(btnValider);
		
		JButton btnVider = new JButton("Vider le panier");
		panel_button.add(btnVider);
		
		JButton btnContinuer = new JButton("Continuer mes achats");
		panel_button.add(btnContinuer);
	}

}