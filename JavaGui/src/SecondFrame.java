import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecondFrame extends JFrame {

	//DB Connection variables
	static Connection connection = null;
	static String databaseName = "";
	static String url = "jdbc:mysql://localhost:3306/usersdb";
	static String username = "root";
	static String password = "ciorba1998";
	
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondFrame frame = new SecondFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public SecondFrame() throws ParseException {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		ArrayList<Counters> counterList = new ArrayList<>();		
		ArrayList<Users> userList = new ArrayList<>();
		
		
		JDateChooser dateChooser = new JDateChooser();		
		dateChooser.setDateFormatString("MMMM yyyy");
		    
		long millis=System.currentTimeMillis();  
	    java.sql.Date date = new java.sql.Date(millis);  
		dateChooser.setDate(date);
		dateChooser.setBounds(98, 107, 127, 20);
		contentPane.add(dateChooser);
		
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("MMMM yyyy");
		dateChooser_1.setDate(date);
		dateChooser_1.setBounds(348, 107, 127, 20);
		contentPane.add(dateChooser_1);
		
		
		int[] users= {0,0,0,0,0,0,0,0,0};
		java.sql.PreparedStatement myStmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			myStmt = connection.prepareStatement("SELECT * FROM counters WHERE date_of_change >= '2020-09-01 00:00:00.000000' AND date_of_change <= '2020-09-30 00:00:00.000000'");
			ResultSet rs= myStmt.executeQuery();
			Counters counter;
			while(rs.next()) {
				counter= new Counters(rs.getInt("id"),rs.getInt("type"),rs.getString("counter_name"),rs.getInt("counter_value"),rs.getDate("date_of_change"),rs.getInt("user_id"));
				users[rs.getInt("user_id")]++;
				counterList.add(counter);
			}
		 }
		 catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			for(int j=0;j<users.length;j++)
			  if(users[j]>0)
				{	
				 myStmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
				 myStmt.setInt(1, j);
			
				ResultSet rs= myStmt.executeQuery();
				Users user;
				while(rs.next()) {
					user= new Users(rs.getInt("id"),rs.getString("login_name"),rs.getString("first_name"),rs.getString("last_name"));
					userList.add(user);
					}
			}
		 }
		 catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		JLabel lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setBounds(125, 91, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("End Date");
		lblNewLabel_1.setBounds(385, 91, 50, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 133, 377, 117);
		contentPane.add(scrollPane);
		
		JButton btnNewButton_0 = new JButton("Return to 1st Frame");
		btnNewButton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI fisrtFrame;
					fisrtFrame = new GUI();
					fisrtFrame.setVisible(true);
					dispose();
			
			}
		});
		btnNewButton_0.setBounds(10, 491, 163, 23);
		contentPane.add(btnNewButton_0);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] columnNames = {"Login","First Name","Last Name"};
		table.setModel(new DefaultTableModel(columnNames,counterList.size()));
		for(int i=0;i<userList.size();i++)  
		{
		table.setValueAt(userList.get(i).getLogin_name(),i,0);
		table.setValueAt(userList.get(i).getFirst_name(),i,1);
		table.setValueAt(userList.get(i).getLast_name(),i,2);
		}
		
////////////////////////////////////////////////////////		 Counters Section 			///////////////////////////////////////////////////////
		
		table_1 = new JTable();	
		String[] columnNames_1 = {"Counter Name","Counter Value"};
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(98, 261, 377, 108);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(table_1);		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("All Users");
		chckbxNewCheckBox.setBounds(10, 346, 80, 23);
		contentPane.add(chckbxNewCheckBox);		
		
		
		JList list = new JList();
		list.setBackground(SystemColor.textHighlightText);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Version", "nbOfDaysUsed", "nbOfComments", "usedFeatures"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(final int index) {
				return values[index];
			}
		});
	
		list.setToolTipText("");
		list.setBounds(208, 422, 100, 80);
		contentPane.add(list);
		

		JList list_1 = new JList();
		list_1.setBackground(SystemColor.textHighlightText);
		DefaultListModel demoList = new DefaultListModel();
		list_1.setBounds(435, 422, 100, 80);
		contentPane.add(list_1);
		
		JLabel lblNewLabel_2 = new JLabel("Availible Counters:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(208, 397, 120, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Selected Counters:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(424, 397, 127, 14);
		contentPane.add(lblNewLabel_2_1);		
		
		
		JButton btnNewButton_2 = new JButton("Add >>");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = list.getSelectedIndex();
				String y = (String) list.getModel().getElementAt(x);
				if(!demoList.contains(y))
					demoList.addElement(y);
				list_1.setModel(demoList);
			}
		});
		btnNewButton_2.setBounds(328, 422, 89, 20);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Remove");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = list_1.getSelectedIndex();
				demoList.remove(x);
				list_1.setModel(demoList);
			}
		});
		btnNewButton_2_1.setBounds(328, 464, 89, 20);
		contentPane.add(btnNewButton_2_1);
		
		
		JButton btnNewButton = new JButton("Show Selected Counters");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()) 
				{
					String[] columnNames_0 = new String[list_1.getModel().getSize()+1];
					columnNames_0[0]="Login";
					for(int q=0; q<list_1.getModel().getSize();q++)
						columnNames_0[q+1]=(String) list_1.getModel().getElementAt(q);
						
					
					table_1.setModel(new DefaultTableModel(columnNames_0,userList.size()));   
					for(int i=0;i<userList.size();i++)  
					{
					  table_1.setValueAt(userList.get(i).getLogin_name(),i,0);						
					  for(int j=0;j<list_1.getModel().getSize();j++)
						{
					
						java.sql.PreparedStatement myStmt = null;
						try {
							Class.forName("com.mysql.jdbc.Driver").newInstance();
							connection = DriverManager.getConnection(url, username, password);
							myStmt = connection.prepareStatement("SELECT * FROM counters WHERE user_id = ?  AND counter_name = ?");
							myStmt.setInt(1, userList.get(i).getId());
							myStmt.setString(2, (String) list_1.getModel().getElementAt(j));
							ResultSet rs= myStmt.executeQuery();
							int value=0;
							while(rs.next()) {
								int res = rs.getInt("counter_value");
								switch(rs.getInt("type")) {	
								case 0:	//////////////////////////////* SUM *////////////////////////////                                  
								{
									value = value + res;
									break;
									}
								case 1:	/////////////////////////////* MAX */////////////////////////////										
									{
								    if (res>value)
								    	value=res;
									break;
									}
								case 2:	/////////////////////////////* SET */////////////////////////////
									{
									value = value + res;
									break;}
								case 3:	////////////////////////////* LAST */////////////////////////////
									{
									value = res;
									break;}
									}
								}
							if(value!=0)
								table_1.setValueAt(value,i,j+1);
							else
								table_1.setValueAt("N/A",i,j+1);
						 	}
						 catch(Exception e) {
							JOptionPane.showMessageDialog(null, e);
						 	}
						}
					 }
					SelectedCounters.NewScreen(table_1);
				  }
				else {
					 int k = table.getSelectedRow();
					 int p=-1;
					 String[] columnNames_2 = {"Counter Name","Counter Value"};
					 table_1.setModel(new DefaultTableModel(columnNames_2,counterList.size()));
					 for(int i=0;i<userList.size();i++)
						 if(userList.get(i).getLogin_name().equals(table.getValueAt(k,0)))
							 for(int j=0;j<counterList.size();j++)
								 if((counterList.get(j).getUser_id()==userList.get(i).getId()) & demoList.contains(counterList.get(j).getCounter_name()))
								 	{
									 ++p;
									 table_1.setValueAt(counterList.get(j).getCounter_name(),p,0);
									 table_1.setValueAt(counterList.get(j).getCounter_value(),p,1);
								 	}		
					}
			 }
		});
		
		
		btnNewButton.setBounds(10, 419, 162, 23);
		contentPane.add(btnNewButton);		
		JButton btnNewButton_1 = new JButton("Show ALL Counters");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					 int k = table.getSelectedRow();
					 int p=-1;
					 String[] columnNames_2 = {"Counter Name","Counter Value"};
					 table_1.setModel(new DefaultTableModel(columnNames_2,counterList.size()));
					 for(int i=0;i<userList.size();i++)
						 if(userList.get(i).getLogin_name().equals(table.getValueAt(k,0)))
							 for(int j=0;j<counterList.size();j++)
								 if(counterList.get(j).getUser_id()==userList.get(i).getId())
								 	{
									 ++p;
									 table_1.setValueAt(counterList.get(j).getCounter_name(),p,0);
									 table_1.setValueAt(counterList.get(j).getCounter_value(),p,1);
				}
			}
		});
		btnNewButton_1.setBounds(10, 388, 162, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Section 2");
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblNewLabel_3.setBounds(209, 25, 170, 55);
		contentPane.add(lblNewLabel_3);
		

		
		chckbxNewCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxNewCheckBox.isSelected())
					btnNewButton_1.setEnabled(false);
				else
					btnNewButton_1.setEnabled(true);
			}
		});
		
		
///////////////////////////////////////////////////////////			 Date Listeners 		////////////////////////////////////////////////////////////

		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				ArrayList<Counters> counterList0 = new ArrayList<>();		
				ArrayList<Users> userList0 = new ArrayList<>();
				int[] users1= {0,0,0,0,0,0,0,0,0};

				java.util.Date dt = dateChooser.getDate();
				java.util.Date dt1 = dateChooser_1.getDate();
				java.text.SimpleDateFormat sdf = 
				     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(dt);
				String date1 = sdf.format(dt1);
				java.sql.PreparedStatement myStmt = null;
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection(url, username, password);
					myStmt = connection.prepareStatement("SELECT * FROM counters WHERE date_of_change >= ? AND date_of_change <= ?");
					myStmt.setString(1, date);
					myStmt.setString(2, date1);
					ResultSet rs= myStmt.executeQuery();
					Counters counter;
					while(rs.next()) {
						counter= new Counters(rs.getInt("id"),rs.getInt("type"),rs.getString("counter_name"),rs.getInt("counter_value"),rs.getDate("date_of_change"),rs.getInt("user_id"));
						users1[rs.getInt("user_id")]++;
						counterList0.add(counter);
					}
					counterList.clear();
					counterList.addAll(counterList0);
				 }
				 catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection(url, username, password);
					for(int j=0;j<users1.length;j++)
					  if(users1[j]>0)
						{	
						 myStmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
						 myStmt.setInt(1, j);
					
						ResultSet rs= myStmt.executeQuery();
						Users user;
						while(rs.next()) {
							user= new Users(rs.getInt("id"),rs.getString("login_name"),rs.getString("first_name"),rs.getString("last_name"));
							userList0.add(user);
							}
						userList.clear();
						userList.addAll(userList0);
					}
					table.setModel(new DefaultTableModel(columnNames,counterList0.size()));
					for(int i=0;i<userList0.size();i++)  
					{
					table.setValueAt(userList0.get(i).getLogin_name(),i,0);
					table.setValueAt(userList0.get(i).getFirst_name(),i,1);
					table.setValueAt(userList0.get(i).getLast_name(),i,2);
					}
				 }
				 catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			 }
			});
		
		
		dateChooser_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {	
				int[] users2= {0,0,0,0,0,0,0,0,0};
				ArrayList<Counters> counterList1 = new ArrayList<>();		
				ArrayList<Users> userList1 = new ArrayList<>();
				java.util.Date dt = dateChooser.getDate();
				java.util.Date dt1 = dateChooser_1.getDate();
				java.text.SimpleDateFormat sdf = 
				     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(dt);
				String date1 = sdf.format(dt1);
				java.sql.PreparedStatement myStmt = null;
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection(url, username, password);
					myStmt = connection.prepareStatement("SELECT * FROM counters WHERE date_of_change >= ? AND date_of_change <= ?");
					myStmt.setString(1, date);
					myStmt.setString(2, date1);
					ResultSet rs= myStmt.executeQuery();
					Counters counter;
					while(rs.next()) {
						counter= new Counters(rs.getInt("id"),rs.getInt("type"),rs.getString("counter_name"),rs.getInt("counter_value"),rs.getDate("date_of_change"),rs.getInt("user_id"));
						users2[rs.getInt("user_id")]++;
						counterList1.add(counter);
					}
					counterList.clear();
					counterList.addAll(counterList1);

				 }
				 catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection(url, username, password);
					for(int j=0;j<users2.length;j++)
					  if(users2[j]>0)
						{	
						 myStmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
						 myStmt.setInt(1, j);
					
						ResultSet rs= myStmt.executeQuery();
						Users user;
						while(rs.next()) {
							user= new Users(rs.getInt("id"),rs.getString("login_name"),rs.getString("first_name"),rs.getString("last_name"));
							userList1.add(user);
							}	
						userList.clear();
						userList.addAll(userList1);
					}
					table.setModel(new DefaultTableModel(columnNames,counterList1.size()));
					for(int i=0;i<userList1.size();i++)  
					{
					table.setValueAt(userList1.get(i).getLogin_name(),i,0);
					table.setValueAt(userList1.get(i).getFirst_name(),i,1);
					table.setValueAt(userList1.get(i).getLast_name(),i,2);
					}
				 }
				 catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
			
		});
	}
}