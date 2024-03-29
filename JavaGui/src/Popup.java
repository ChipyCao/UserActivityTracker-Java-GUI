import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Popup {

	private JFrame frame;
	private JTable table;

	//DB Connection variables
	static Connection connection = null;
	static String databaseName = "";
	static String url = "jdbc:mysql://localhost:3306/usersdb";
	static String username = "root";
	static String password = "ciorba1998";
	
	
	//// Extracting User list from the MySQL database
	public ArrayList<Users> userList(){
		ArrayList<Users> userList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
			String query1="SELECT * FROM users";
			Statement st= connection.createStatement();
			ResultSet rs= st.executeQuery(query1);
			Users user;
			while(rs.next()) {
				user= new Users(rs.getInt("id"),rs.getString("login_name"),rs.getString("first_name"),rs.getString("last_name"));
				userList.add(user);
			}
		 }
		 catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return userList;
	}
	/**
	 * Launching the application.
	 */
	public static void NewScreen(int x[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Popup window = new Popup(x);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creating the application.
	 */
	public Popup(int x[]) {
		initialize(x);
	}

	/**
	 * Initializing the contents of the frame.
	 */
	private void initialize(int x[]) {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 300);
		frame.getContentPane().setLayout(null);
		
		//Setting up a scrollable pane for the table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 73, 356, 138);
		frame.getContentPane().add(scrollPane);
	
		//Creating the table & populating it with data from the database
		ArrayList<Users> list = userList();		
		table = new JTable();
		scrollPane.setViewportView(table);

		String[] columnNames = {"Login","First Name","Last Name"};
		table.setModel(new DefaultTableModel(columnNames,x.length));
		int k=-1;
		for(int i=0;i<x.length;i++)  
			if(x[i]>0)
				{
				k++;
				table.setValueAt(list.get(i).getLogin_name(),k,0);
				table.setValueAt(list.get(i).getFirst_name(),k,1);
				table.setValueAt(list.get(i).getLast_name(),k,2);
				}

		
//////////////////////////////                        EXPORTING TO an EXCEL FILE                   	//////////////////////////////////////
		JButton btnNewButton = new JButton("Export");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileOutputStream excelFOU = null;
				BufferedOutputStream excelBOU = null;
				XSSFWorkbook excelJTableExporter = null;
				
			//	Choosing Location For Saving the Excel File
			JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\cipia\\OneDrive\\Desktop\\Excel data");
			
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
			excelFileChooser.setFileFilter(fnef);
			int excelChooser = excelFileChooser.showSaveDialog(null);
			
			if(excelChooser == JFileChooser.APPROVE_OPTION){

				try {
					excelJTableExporter = new XSSFWorkbook();
					XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
					//Loop to get jtable columns and rows
					for (int i = 0; i< table.getRowCount(); i++) {
						XSSFRow excelRow = excelSheet.createRow(i);
						for(int j = 0; j < table.getColumnCount(); j++) {
							XSSFCell excelCell = excelRow.createCell(j);
						excelCell.setCellValue(table.getValueAt(i, j).toString());
						}
					}
					excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() +".xlsx");
					excelBOU = new BufferedOutputStream(excelFOU);
					excelJTableExporter.write(excelBOU);
					JOptionPane.showMessageDialog(null,"Exported Succesfully");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						if(excelBOU != null) {
							excelBOU.close();
						}
						if(excelFOU != null) {
							excelFOU.close();
						}
						if(excelJTableExporter != null) {
							excelJTableExporter.close();
						}
					} catch(IOException ex) {
						ex.printStackTrace();
					}
				} 
		    }
		  }
		});
		btnNewButton.setBounds(163, 222, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel(" Active Users this Month:");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(62, 19, 311, 37);
		frame.getContentPane().add(lblNewLabel);
		
	}
}
