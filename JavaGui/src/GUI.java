import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JMonthChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Font;
public class GUI extends JFrame{

	//DB Connection variables
	static Connection connection = null;
	static String databaseName = "";
	static String url = "jdbc:mysql://localhost:3306/usersdb";
	static String username = "root";
	static String password = "ciorba1998";
	
////Extracting Counter list from the MySQL database
	public ArrayList<Counters> counterList(){
		ArrayList<Counters> counterList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			String query0="SELECT * FROM counters";
			Statement st= connection.createStatement();
			ResultSet rs= st.executeQuery(query0);
			Counters counter;
			while(rs.next()) {
				counter= new Counters(rs.getInt("id"),rs.getInt("type"),rs.getString("counter_name"),rs.getInt("counter_value"),rs.getDate("date_of_change"),rs.getInt("user_id"));
				counterList.add(counter);
			}
		 }
		 catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return counterList;
	}
	
	
	
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JMonthChooser monthChooser;	
	
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creating the frame.
	 */
	@SuppressWarnings("deprecation")
	public GUI() {
		ArrayList<Counters> list = counterList();	
		Month sep = new Month(0,0);
		Month oct = new Month(0,0);
		Month nov = new Month(0,0);
		Month dec = new Month(0,0);
		for(int i=0;i<list.size();i++)
		{
			switch (list.get(i).getDate_of_change().getMonth()){
			case 8:
				{
				if(list.get(i).getCounter_name().equals("nbOfDaysUsed"))
					sep.setNbOfDaysUsed(list.get(i).getCounter_value());
				sep.users[list.get(i).getUser_id()]+=1;
				if(sep.users[list.get(i).getUser_id()]==1)
					sep.nbOfUsers++;
				break;}
			case 9:
				{
				if(list.get(i).getCounter_name().equals("nbOfDaysUsed"))
					oct.setNbOfDaysUsed(list.get(i).getCounter_value());
				oct.users[list.get(i).getUser_id()]+=1;
				if(oct.users[list.get(i).getUser_id()]==1)
					oct.nbOfUsers++;
				break;}
			case 10:
			    {
			    if(list.get(i).getCounter_name().equals("nbOfDaysUsed"))
			    	nov.setNbOfDaysUsed(list.get(i).getCounter_value());
				nov.users[list.get(i).getUser_id()]+=1;
				if(nov.users[list.get(i).getUser_id()]==1)
					nov.nbOfUsers++;
				break;}
			case 11:
				{
				if(list.get(i).getCounter_name().equals("nbOfDaysUsed"))
					dec.setNbOfDaysUsed(list.get(i).getCounter_value());
				dec.users[list.get(i).getUser_id()]+=1;
				if(dec.users[list.get(i).getUser_id()]==1)
					dec.nbOfUsers++;
				break;}
			}
		}

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Month = new JLabel(" Month:");
		Month.setBounds(159, 116, 53, 20);
		contentPane.add(Month);


		//////////////////////////////////////     Nb of users current month    /////////////////
		JLabel lblNewLabel = new JLabel(" Users this month:");
		lblNewLabel.setBounds(110, 153, 102, 14);
		contentPane.add(lblNewLabel);
		textField_1 = new JTextField(String.valueOf(sep.nbOfUsers));
		textField_1.setBounds(210, 150, 240, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		////////////////////////////////////// Nb of days used in current month //////////////////
		JLabel lblNewLabel_1 = new JLabel("Days used:");
		lblNewLabel_1.setBounds(142, 184, 66, 14);
		contentPane.add(lblNewLabel_1);
		textField_2 = new JTextField(String.valueOf(sep.nbOfDaysUsed));
		textField_2.setBounds(210, 181, 240, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		monthChooser = new JMonthChooser();
		monthChooser.getMonth();
		monthChooser.setBounds(210, 116, 116, 20);
		contentPane.add(monthChooser);

		
		
		/////////////////////////////////////////////////////    Month Changed Event /////////////////
		monthChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent Month) {
				switch(monthChooser.getMonth()) {
				case 8:
					{
					textField_1.setText(String.valueOf(sep.nbOfUsers));
					textField_2.setText(String.valueOf(sep.nbOfDaysUsed));
					break;
					}
				case 9:
					{
					textField_1.setText(String.valueOf(oct.nbOfUsers));
					textField_2.setText(String.valueOf(oct.nbOfDaysUsed));
					break;
					}
				case 10:
					{
					textField_1.setText(String.valueOf(nov.nbOfUsers));
					textField_2.setText(String.valueOf(nov.nbOfDaysUsed));
					break;}
				case 11:
					{
					textField_1.setText(String.valueOf(dec.nbOfUsers));
					textField_2.setText(String.valueOf(dec.nbOfDaysUsed));
					break;}
				}
			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////
			
		
		JButton btnNewButton = new JButton("Show Users");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				switch(monthChooser.getMonth()) {
				case 8:
					{
					Popup nw = new Popup(sep.users);
					nw.NewScreen(sep.users);
					break;
					}
				case 9:
					{
					Popup nw = new Popup(oct.users);
					nw.NewScreen(oct.users);
					break;
					}
				case 10:
					{
					Popup nw = new Popup(nov.users);
					nw.NewScreen(nov.users);
					break;}
				case 11:
					{
					Popup nw = new Popup(dec.users);
					nw.NewScreen(dec.users);
					break;}
				}
			}
		});
		btnNewButton.setBounds(220, 223, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Switch to 2nd frame");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecondFrame secondFrame;
				try {
					secondFrame = new SecondFrame();
					secondFrame.setVisible(true);
					dispose();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnNewButton_1.setBounds(10, 262, 163, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Section 1");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblNewLabel_2.setBounds(190, 21, 179, 62);
		contentPane.add(lblNewLabel_2);
		
	}
}