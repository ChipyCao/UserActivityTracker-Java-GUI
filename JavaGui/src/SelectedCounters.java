import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SelectedCounters {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(JTable table_1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectedCounters window = new SelectedCounters(table_1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param table_1 
	 */
	public SelectedCounters(JTable table_1) {
		initialize(table_1);
		table_1 = new JTable();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JTable table_1) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 323);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 89, 393, 149);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel(" Selected Counters - All Users");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(30, 11, 371, 54);
		frame.getContentPane().add(lblNewLabel);
		
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
					for (int i = 0; i< table_1.getRowCount(); i++) {
						XSSFRow excelRow = excelSheet.createRow(i);
						for(int j = 0; j < table_1.getColumnCount(); j++) {
							XSSFCell excelCell = excelRow.createCell(j);
						excelCell.setCellValue(table_1.getValueAt(i, j).toString());
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
		btnNewButton.setBounds(162, 250, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}