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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.Fromage;
import modele.Fromages;
import modele.TypeLait;

public class NosFromages extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxType;
    private JList<String> listFromages;
    private Fromages fromages;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        fromages = new Fromages();
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane);

        listFromages = new JList<>();
        scrollPane.setViewportView(listFromages);

        listFromages.setListData(fromages.getFromages().stream()
                .map(Fromage::getDésignation).toArray(String[]::new));

        listFromages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedValue = listFromages.getSelectedValue();
                    Fromage fromageSelectionnee = fromages.getFromages().stream()
                            .filter(f -> f.getDésignation().equals(selectedValue))
                            .findFirst()
                            .orElse(null);
                    if (fromageSelectionnee != null) {
                        listFromages.setVisible(true);
                    }
                }
            }
        });

        JPanel haut = new JPanel();
        contentPane.add(haut, BorderLayout.NORTH);
        haut.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        haut.add(panel);

        JLabel lblNewLabel = new JLabel("Nos fromages ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Fromageprepareepixel.png"));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel_1);

        JButton btnPanier = new JButton("0.00€");
//        btnPanier.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                VotrePanier frame = new VotrePanier();
//                frame.setVisible(true);
//            }
//        });

        btnPanier.setBackground(new Color(255, 128, 0));
        btnPanier.setForeground(new Color(0, 0, 0));
        btnPanier.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Image_Pasted_2023-23-05_at_16.45.png"));

        btnPanier.setHorizontalAlignment(SwingConstants.RIGHT);
        btnPanier.setBounds(new Rectangle(20, 0, 0, 0));
        btnPanier.setAlignmentX(1.0f);
        haut.add(btnPanier, BorderLayout.EAST);

        JPanel bas = new JPanel();
        contentPane.add(bas, BorderLayout.SOUTH);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 0), 2), "Filtre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 0)));
        bas.add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\oscar\\git\\repo_fromage\\programmation_SAE_S2-01_GD_6\\src\\main\\resources\\images\\fromages\\Fromage.png"));
        panel_1.add(lblNewLabel_2);

        this.comboBoxType = new JComboBox<>();
        this.comboBoxType.addItem("Tous les fromages");
        for (TypeLait t : TypeLait.values()) {
            this.comboBoxType.addItem(t.getTypeDeLait());
        }
        panel_1.add(this.comboBoxType);

        JButton Bouton = new JButton("Quitter");
        Bouton.setHorizontalAlignment(SwingConstants.RIGHT);
        bas.add(Bouton);
        Bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
