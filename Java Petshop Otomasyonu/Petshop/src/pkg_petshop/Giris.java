package pkg_petshop;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;


@SuppressWarnings("serial")
public class Giris extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JButton btnEnter;
	
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String DBUSER = "t";
	public static final String DBPASS = "q";
	private JButton btnAbout;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giris frame = new Giris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void GirisKontrol(String KAdi, String KSifre)
	{
		try{
			
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from USERS where USERNAME='"+KAdi+"' and PASSWORD='"+KSifre+"'");
			
			if(rs.next())
			{
				String DBKAdi=rs.getString("USERNAME");
				String DBSifre=rs.getString("PASSWORD");
				if (KAdi.equals(DBKAdi)  &&  KSifre.equals(DBSifre))
				{
					dispose();
					Menu me=new Menu();
					me.setVisible(true);
				}
				
				else
		    	{
		    		
		    		JOptionPane.showMessageDialog(null, "Login failed. Please enter your username and password correctly.", "Error", 2);
		    	
		    	}
			}
			
			 else
			    {
		    		JOptionPane.showMessageDialog(null, "Login failed. Please enter your username and password correctly.", "Error", 2);
			    }
			
			rs.close();
			stm.close();
			con.close();
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Giris() {
		setResizable(false);
		setTitle("PETSHOP AUTOMATION");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entrance Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(107, 79, 67, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(107, 124, 67, 14);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(184, 76, 127, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		btnEnter = new JButton("Enter");
		btnEnter.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\enter.png"));
		btnEnter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				
				
				GirisKontrol(txtUsername.getText(), String.valueOf(txtPassword.getPassword()));
				
				
			}
		});
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setBackground(Color.WHITE);
		btnEnter.setBounds(222, 165, 89, 23);
		contentPane.add(btnEnter);
		
		btnAbout = new JButton("About");
		
		
		
		btnAbout.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\about.png"));
		btnAbout.setForeground(Color.BLACK);
		btnAbout.setBackground(Color.WHITE);
		btnAbout.setBounds(107, 165, 89, 23);
		contentPane.add(btnAbout);
		
		JLabel lblAbout = new JLabel("Designed and Created by Taylan GELEKÇİ");
		lblAbout.setEnabled(false);
		lblAbout.setBounds(107, 218, 256, 14);
		contentPane.add(lblAbout);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(184, 121, 127, 20);
		contentPane.add(txtPassword);
		lblAbout.setVisible(false);
		
		
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblAbout.setVisible(!lblAbout.isVisible());
				revalidate();
				repaint();


			}
		});
		
	}
}
