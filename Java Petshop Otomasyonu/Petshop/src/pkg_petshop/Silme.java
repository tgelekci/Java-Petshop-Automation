package pkg_petshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Silme extends JFrame {

	private JPanel contentPane;
	private JTable tblAnimalsRemove;
	private JTextField txtID;
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String DBUSER = "t";
	public static final String DBPASS = "q";
	/**
	 * Launch the application.
	 */
	public void HepsiniSil(int _ID)
	{
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection co=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement sta=co.createStatement();
			String sql="DELETE FROM ANIMALS WHERE ID="+_ID;
			sta.executeUpdate(sql);
			
			sta.close();
			co.close();
		}
		catch(Exception h){
			h.printStackTrace();
		}
	}
	
	public void BiriniSil(int _ID)
	{
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection co=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement sta=co.createStatement();
			String sql="UPDATE ANIMALS SET "+"QUANTITY=(QUANTITY-1) WHERE ID="+_ID;
			sta.executeUpdate(sql);
			
			sta.close();
			co.close();
		}
		catch(Exception h){
			h.printStackTrace();
		}
		
	}
	public void Listele()
	{
		try{
			
			String sql="select * from ANIMALS";
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection b=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement d=b.createStatement();
			ResultSet s=d.executeQuery(sql);
			
			
			int colcount=s.getMetaData().getColumnCount();
			DefaultTableModel dtm=new DefaultTableModel();
			
			for(int i=1; i<=colcount; i++)
			{

				dtm.addColumn(s.getMetaData().getColumnName(i));
			}
			
			while(s.next())
			{
				Object[] row=new Object[colcount];
				for(int i=1; i<=colcount; i++)
					row[i-1]=s.getObject(i);
				dtm.addRow(row);
			}
			
			tblAnimalsRemove.setShowVerticalLines(true);
			tblAnimalsRemove.setModel(dtm);
			
			d.close();
			b.close();
			
		}
		catch(Exception e4){
			e4.printStackTrace();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Silme frame = new Silme();
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
	public Silme() {
		setTitle("PETSHOP AUTOMATION");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(null, "Remove Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mu=new Menu();
				mu.setVisible(true);
				dispose();
			}
		});
		btnBack.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\back.png"));
		btnBack.setBounds(293, 237, 135, 23);
		contentPane.add(btnBack);
		
		tblAnimalsRemove = new JTable();
		tblAnimalsRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tblAnimalsRemove.getSelectedRow();
				txtID.setText(tblAnimalsRemove.getValueAt(row, 6).toString());
			}
		});
		tblAnimalsRemove.setShowVerticalLines(true);
		tblAnimalsRemove.setBounds(10, 21, 418, 141);
		contentPane.add(tblAnimalsRemove);
		
		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int diar=JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the animal?", "Remove", 0, 2);
				if(diar==JOptionPane.YES_OPTION)
				{
					
					HepsiniSil(Integer.parseInt(txtID.getText()));
					JOptionPane.showMessageDialog(null, "The animal successfully removed.", "Remove", 1);
				}
			}
		});
		btnRemoveAll.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\remove2.png"));
		btnRemoveAll.setBounds(293, 203, 135, 23);
		contentPane.add(btnRemoveAll);
		
		JButton btnRemoveOne = new JButton("Remove One");
		btnRemoveOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int di=JOptionPane.showConfirmDialog(null, "Are you sure you want to remove only one animal?", "Remove", 0, 2);
				if(di==JOptionPane.YES_OPTION)
				{
					
					BiriniSil(Integer.parseInt(txtID.getText()));
					JOptionPane.showMessageDialog(null, "The animal successfully removed.", "Remove", 1);
				}
			}
		});
		btnRemoveOne.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\remove2.png"));
		btnRemoveOne.setBounds(293, 173, 135, 23);
		contentPane.add(btnRemoveOne);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 207, 25, 14);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(45, 204, 79, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		Listele();
	}
}
