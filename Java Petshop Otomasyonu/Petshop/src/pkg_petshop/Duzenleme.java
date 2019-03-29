package pkg_petshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Duzenleme extends JFrame {

	private JPanel contentPane;
	

	
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String DBUSER = "t";
	public static final String DBPASS = "q";
	private JTable tblAnimalsEdit;
	private JTextField txtGenus;
	private JTextField txtSpecies;
	private JTextField txtAge;
	private JTextField txtColor;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	
	public void HayvanDuzenle(String _Genus, String _Species, int _Age, String _Color, int _Price, int _Quantity, int _ID)
	{
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection bg=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement dm=bg.createStatement();
			String sql="UPDATE ANIMALS "+
						"SET "+
						"GENUS='"+_Genus+"', "+
						"SPECIES='"+_Species+"', "+
						"AGE="+_Age+", "+
						"COLOR='"+_Color+"', "+
						"\""+"UNIT PRICE\""+"="+_Price+", "+
						"QUANTITY="+_Quantity+" "+
						"WHERE ID="+_ID+" ";
						
			dm.executeUpdate(sql);
			
			dm.close();
			bg.close();
		}
		catch(Exception e3){
			e3.printStackTrace();
		}
	}
	
	public void Listele()
	{
			try{
			
			String sql="select * from ANIMALS";
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection ct=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement st=ct.createStatement();
			ResultSet rt=st.executeQuery(sql);
			
			
			int colcount=rt.getMetaData().getColumnCount();
			DefaultTableModel dtm=new DefaultTableModel();
			
			for(int i=1; i<=colcount; i++)
			{

				dtm.addColumn(rt.getMetaData().getColumnName(i));
			}
			
			while(rt.next())
			{
				Object[] row=new Object[colcount];
				for(int i=1; i<=colcount; i++)
					row[i-1]=rt.getObject(i);
				dtm.addRow(row);
			}
			
			tblAnimalsEdit.setShowVerticalLines(true);
			tblAnimalsEdit.setModel(dtm);
			
			st.close();
			ct.close();
			
		}
		catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Duzenleme frame = new Duzenleme();
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
	public Duzenleme() {
		setTitle("PETSHOP AUTOMATION");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(null, "Edit Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblAnimalsEdit = new JTable();
		tblAnimalsEdit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tblAnimalsEdit.getSelectedRow();
				txtGenus.setText(tblAnimalsEdit.getValueAt(row, 0).toString());		
				txtSpecies.setText(tblAnimalsEdit.getValueAt(row, 1).toString());	
				txtAge.setText(tblAnimalsEdit.getValueAt(row, 2).toString());	
				txtColor.setText(tblAnimalsEdit.getValueAt(row, 3).toString());	
				txtPrice.setText(tblAnimalsEdit.getValueAt(row, 4).toString());	
				txtQuantity.setText(tblAnimalsEdit.getValueAt(row, 5).toString());	
				txtID.setText(tblAnimalsEdit.getValueAt(row, 6).toString());
			}
		});
		tblAnimalsEdit.setShowVerticalLines(true);
		tblAnimalsEdit.setBounds(200, 23, 228, 197);
		contentPane.add(tblAnimalsEdit);
		
		txtGenus = new JTextField();
		txtGenus.setBounds(104, 20, 86, 20);
		contentPane.add(txtGenus);
		txtGenus.setColumns(10);
		
		txtSpecies = new JTextField();
		txtSpecies.setBounds(104, 54, 86, 20);
		contentPane.add(txtSpecies);
		txtSpecies.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(104, 85, 86, 20);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(104, 116, 86, 20);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(104, 147, 86, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(104, 180, 86, 20);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mx=new Menu();
				mx.setVisible(true);
				dispose();
			}
		});
		btnBack.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\back.png"));
		btnBack.setBounds(210, 232, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblGenus = new JLabel("Genus:");
		lblGenus.setBounds(10, 23, 46, 14);
		contentPane.add(lblGenus);
		
		JLabel lblSpecies = new JLabel("Species:");
		lblSpecies.setBounds(10, 57, 62, 14);
		contentPane.add(lblSpecies);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(10, 88, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 119, 46, 14);
		contentPane.add(lblColor);
		
		JLabel lblPrice = new JLabel("Unit Price:");
		lblPrice.setBounds(10, 150, 62, 14);
		contentPane.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(10, 183, 62, 14);
		contentPane.add(lblQuantity);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dias=JOptionPane.showConfirmDialog(null, "Are you sure you want to edit the chosen animal?", "Save", 0, 2);
				if(dias==JOptionPane.YES_OPTION)
				{
					
					HayvanDuzenle(txtGenus.getText(), txtSpecies.getText(), Integer.parseInt(txtAge.getText()), txtColor.getText(), Integer.parseInt(txtPrice.getText()), Integer.parseInt(txtQuantity.getText()), Integer.parseInt(txtID.getText()));
					JOptionPane.showMessageDialog(null, "The animal successfully edited.", "Edit", 1);
				}
			}
		});
		btnSave.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\save.png"));
		btnSave.setBounds(339, 232, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 214, 46, 14);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(104, 211, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		Listele();
	}
}
