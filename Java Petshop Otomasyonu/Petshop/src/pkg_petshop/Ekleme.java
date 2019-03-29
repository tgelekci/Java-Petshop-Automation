package pkg_petshop;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Ekleme extends JFrame {

	private JPanel contentPane;
	private JTextField txtSpecies;
	private JTextField txtGenus;
	private JTextField txtAge;
	private JTextField txtColor;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String DBUSER = "t";
	public static final String DBPASS = "q";
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ekleme frame = new Ekleme();
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
	public void HayvanKaydet(String _Genus, String _Species, int _Age, String _Color, int _Price, int _Quantity, int _ID)
	{
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection bag=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement drm=bag.createStatement();
			String sql="INSERT INTO ANIMALS VALUES ('"+_Genus+"', '"+_Species+"', "+_Age+", '"+_Color+"', "+_Price+", "+_Quantity+", "+_ID+")";
			drm.executeUpdate(sql);
			
			drm.close();
			bag.close();
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		
		
	}
	public Ekleme() {
		setTitle("PETSHOP AUTOMATION");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(null, "Add Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSpecies = new JLabel("Species:");
		lblSpecies.setBounds(65, 48, 68, 14);
		contentPane.add(lblSpecies);
		
		JLabel lblGenus = new JLabel("Genus:");
		lblGenus.setBounds(65, 17, 68, 14);
		contentPane.add(lblGenus);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(65, 83, 68, 14);
		contentPane.add(lblAge);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(65, 114, 68, 14);
		contentPane.add(lblColor);
		
		JLabel lblUPrice = new JLabel("Unit Price:");
		lblUPrice.setBounds(65, 145, 68, 14);
		contentPane.add(lblUPrice);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(65, 177, 68, 14);
		contentPane.add(lblQuantity);
		
		txtGenus = new JTextField();
		txtGenus.setBounds(166, 14, 157, 20);
		contentPane.add(txtGenus);
		txtGenus.setColumns(10);
		
		txtSpecies = new JTextField();
		txtSpecies.setBounds(166, 45, 157, 20);
		contentPane.add(txtSpecies);
		txtSpecies.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(166, 80, 157, 20);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(166, 111, 157, 20);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(166, 142, 157, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(166, 174, 157, 20);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m=new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		btnBack.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\back.png"));
		btnBack.setBounds(65, 237, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int diad=JOptionPane.showConfirmDialog(null, "Are you sure you want to save the new animal?", "Save", 0, 2);
				if(diad==JOptionPane.YES_OPTION)
				{
					
					HayvanKaydet(txtGenus.getText(), txtSpecies.getText(), Integer.parseInt(txtAge.getText()), txtColor.getText(), Integer.parseInt(txtPrice.getText()), Integer.parseInt(txtQuantity.getText()), Integer.parseInt(txtID.getText()));
					JOptionPane.showMessageDialog(null, "The animal successfully added.", "Add", 1);
				}
			}
		});
		btnSave.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\save.png"));
		btnSave.setBounds(234, 237, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(65, 202, 46, 14);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(166, 205, 157, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
	}
}
