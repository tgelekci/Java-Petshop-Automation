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
public class Satis extends JFrame {

	private JPanel contentPane;
	private JTable tblAnimalsSell;
	private JTextField txtID;
	private JTextField txtCName;
	private JTextField txtCSurname;
	private JTextField txtPurAn;
	private JTextField txtPurAnNum;
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String DBUSER = "t";
	public static final String DBPASS = "q";
	/**
	 * Launch the application.
	 */
	
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
			
			tblAnimalsSell.setShowVerticalLines(true);
			tblAnimalsSell.setModel(dtm);
			
			st.close();
			ct.close();
			
		}
		catch(Exception eh){
			eh.printStackTrace();
		}
	}
	public void HayvanSat(int _ID, String _PurAn, int _PurAnNum)
	{
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection bg=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement dm=bg.createStatement();
			String sql="UPDATE ANIMALS "+
						"SET "+
						"QUANTITY=(QUANTITY-"+_PurAnNum+") "+
						"WHERE ID="+_ID;
			
			String sql2="INSERT INTO CUSTOMERS VALUES ('"+txtCName.getText()+"' , '"+txtCSurname.getText()+"' , '"+_PurAn+"' , "+_PurAnNum+")";
						
			dm.executeUpdate(sql);
			dm.executeUpdate(sql2);
			
			dm.close();
			bg.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Satis frame = new Satis();
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
	public Satis() {
		setResizable(false);
		setTitle("PETSHOP SCREEN");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 345);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(null, "Sale Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		btnBack.setBounds(316, 276, 89, 23);
		contentPane.add(btnBack);
		
		tblAnimalsSell = new JTable();
		tblAnimalsSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=tblAnimalsSell.getSelectedRow();
				txtID.setText(tblAnimalsSell.getValueAt(row, 6).toString());		
				txtPurAn.setText(tblAnimalsSell.getValueAt(row, 1).toString());	
				txtPurAnNum.setText(tblAnimalsSell.getValueAt(row, 5).toString());	
			}
		});
		tblAnimalsSell.setShowVerticalLines(true);
		tblAnimalsSell.setBounds(184, 29, 306, 236);
		contentPane.add(tblAnimalsSell);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 29, 34, 14);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(41, 26, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblCName = new JLabel("Customer Name:");
		lblCName.setBounds(10, 54, 117, 14);
		contentPane.add(lblCName);
		
		JLabel lblCSurname = new JLabel("Customer Surname:");
		lblCSurname.setBounds(10, 110, 117, 14);
		contentPane.add(lblCSurname);
		
		txtCName = new JTextField();
		txtCName.setBounds(10, 79, 117, 20);
		contentPane.add(txtCName);
		txtCName.setColumns(10);
		
		txtCSurname = new JTextField();
		txtCSurname.setBounds(10, 135, 117, 20);
		contentPane.add(txtCSurname);
		txtCSurname.setColumns(10);
		
		JLabel lblPurAn = new JLabel("Purchased Animal:");
		lblPurAn.setBounds(10, 166, 117, 14);
		contentPane.add(lblPurAn);
		
		JLabel lblPurAnNum = new JLabel("Purchased Animal Number:");
		lblPurAnNum.setBounds(10, 220, 158, 14);
		contentPane.add(lblPurAnNum);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int diar=JOptionPane.showConfirmDialog(null, "Are you sure you want to sell the animal?", "Sale", 0, 2);
				if(diar==JOptionPane.YES_OPTION)
				{
					
					HayvanSat(Integer.parseInt(txtID.getText()), txtPurAn.getText(), Integer.parseInt(txtPurAnNum.getText()));
					JOptionPane.showMessageDialog(null, "The animal successfully sold.", "Sale", 1);
				}
			}
		});
		btnSell.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\sell2.png"));
		btnSell.setBounds(415, 276, 89, 23);
		contentPane.add(btnSell);
		
		txtPurAn = new JTextField();
		txtPurAn.setBounds(10, 189, 117, 20);
		contentPane.add(txtPurAn);
		txtPurAn.setColumns(10);
		
		txtPurAnNum = new JTextField();
		txtPurAnNum.setBounds(10, 245, 117, 20);
		contentPane.add(txtPurAnNum);
		txtPurAnNum.setColumns(10);
		
		Listele();
	}
}
