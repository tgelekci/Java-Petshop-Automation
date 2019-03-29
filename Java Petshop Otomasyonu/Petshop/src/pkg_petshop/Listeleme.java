package pkg_petshop;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.*;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Listeleme extends JFrame {

	private JPanel contentPane;
	private JTable tblAnimalsList;
	
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String DBUSER = "t";
	public static final String DBPASS = "q";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listeleme frame = new Listeleme();
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
	
	
	
	public void HayvanListele()
	{
		try{
			
			String sql="select * from ANIMALS";
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection cnt=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement s=cnt.createStatement();
			ResultSet r=s.executeQuery(sql);
			
			
			int colcount=r.getMetaData().getColumnCount();
			DefaultTableModel dtm=new DefaultTableModel();
			
			for(int i=1; i<=colcount; i++)
			{

				dtm.addColumn(r.getMetaData().getColumnName(i));
			}
			
			while(r.next())
			{
				Object[] row=new Object[colcount];
				for(int i=1; i<=colcount; i++)
					row[i-1]=r.getObject(i);
				dtm.addRow(row);
			}
			
			tblAnimalsList.setShowVerticalLines(true);
			tblAnimalsList.setModel(dtm);
			
			s.close();
			cnt.close();
			
		}
		catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
	
	public Listeleme() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setTitle("PETSHOP AUTOMATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(null, "List Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblAnimalsList = new JTable();
		tblAnimalsList.setEnabled(false);
		tblAnimalsList.setBounds(10, 40, 418, 186);
		contentPane.add(tblAnimalsList);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn=new Menu();
				mn.setVisible(true);
				dispose();
			}
		});
		btnBack.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\back.png"));
		btnBack.setBounds(172, 237, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblGenus = new JLabel("GENUS");
		lblGenus.setForeground(Color.RED);
		lblGenus.setBounds(10, 21, 46, 14);
		contentPane.add(lblGenus);
		
		JLabel lblSpecies = new JLabel("SPECIES");
		lblSpecies.setForeground(Color.RED);
		lblSpecies.setBounds(66, 21, 58, 14);
		contentPane.add(lblSpecies);
		
		JLabel lblAge = new JLabel("AGE");
		lblAge.setForeground(Color.RED);
		lblAge.setBounds(134, 21, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblColor = new JLabel("COLOR");
		lblColor.setForeground(Color.RED);
		lblColor.setBounds(190, 21, 46, 14);
		contentPane.add(lblColor);
		
		JLabel lblPrice = new JLabel("UNIT PRICE");
		lblPrice.setForeground(Color.RED);
		lblPrice.setBounds(246, 21, 72, 14);
		contentPane.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setForeground(Color.RED);
		lblQuantity.setBounds(314, 21, 60, 14);
		contentPane.add(lblQuantity);
		
		JLabel lblID = new JLabel("ID");
		lblID.setForeground(Color.RED);
		lblID.setBounds(384, 21, 60, 14);
		contentPane.add(lblID);
		
	
	
		HayvanListele();
	}
}
