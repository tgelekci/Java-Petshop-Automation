package pkg_petshop;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;


import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("PETSHOP AUTOMATION");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\dog_track.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu Screen", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddAnimal = new JButton("Add Animal");
		btnAddAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ekleme ek=new Ekleme();
				ek.setVisible(true);
				dispose();
			}
		});
		btnAddAnimal.setBackground(Color.YELLOW);
		btnAddAnimal.setForeground(Color.BLACK);
		btnAddAnimal.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\add.png"));
		btnAddAnimal.setBounds(37, 42, 157, 33);
		contentPane.add(btnAddAnimal);
		
		JButton btnDeleteAnimal = new JButton("Remove Animal");
		btnDeleteAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Silme si=new Silme();
				si.setVisible(true);
				dispose();
			}
		});
		btnDeleteAnimal.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\remove.png"));
		btnDeleteAnimal.setForeground(Color.BLACK);
		btnDeleteAnimal.setBackground(Color.YELLOW);
		btnDeleteAnimal.setBounds(37, 110, 157, 33);
		contentPane.add(btnDeleteAnimal);
		
		JButton btnEditAnimal = new JButton("Edit Animal");
		btnEditAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Duzenleme du=new Duzenleme();
				du.setVisible(true);
				dispose();
			}
		});
		btnEditAnimal.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\edit.png"));
		btnEditAnimal.setForeground(Color.BLACK);
		btnEditAnimal.setBackground(Color.YELLOW);
		btnEditAnimal.setBounds(246, 42, 157, 33);
		contentPane.add(btnEditAnimal);
		
		JButton btnListAnimals = new JButton("List Animals");
		btnListAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listeleme l=new Listeleme();
				l.setVisible(true);
				dispose();
			}
		});
		btnListAnimals.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\list.png"));
		btnListAnimals.setForeground(Color.BLACK);
		btnListAnimals.setBackground(Color.YELLOW);
		btnListAnimals.setBounds(246, 110, 157, 33);
		contentPane.add(btnListAnimals);
		
		JButton btnSellAnimal = new JButton("Sell Animal");
		btnSellAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Satis st=new Satis();
				st.setVisible(true);
				dispose();
			}
		});
	
		
		btnSellAnimal.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\sell.png"));
		btnSellAnimal.setForeground(Color.BLACK);
		btnSellAnimal.setBackground(Color.YELLOW);
		btnSellAnimal.setBounds(37, 178, 157, 33);
		contentPane.add(btnSellAnimal);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giris g=new Giris();
				int diam=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", 0, 2);
				if(diam==JOptionPane.YES_OPTION)
				{
					g.setVisible(true);
					dispose();
				}
				
				
				
			}
		});
		btnExit.setIcon(new ImageIcon("D:\\Java Projeleri\\Petshop\\bin\\Pictures\\exit.png"));
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.YELLOW);
		btnExit.setBounds(246, 178, 157, 33);
		contentPane.add(btnExit);
	}
}
