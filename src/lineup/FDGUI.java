package lineup;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.HashMap;

public class FDGUI {

	private JFrame frame;
	private JTextField playerCSV;
	
	private TeamObj fdPlayerList;
	private TeamObj dailyLineups;
	private JTextField minSal;
	private JTextField maxSal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FDGUI window = new FDGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FDGUI() 
	{
		fdPlayerList = null;
		dailyLineups = null;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblLineups = new JLabel("Lineups Updated");
		lblLineups.setBounds(383, 27, 105, 16);
		frame.getContentPane().add(lblLineups);
		lblLineups.setVisible(false);
		
		JButton btnGetLineups = new JButton("Get Lineups");
		btnGetLineups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				TableGeter geter = new TableGeter();
				dailyLineups = geter.getNewestLineups();
				lblLineups.setVisible(true);
			}
		});
		btnGetLineups.setBounds(228, 13, 125, 44);
		frame.getContentPane().add(btnGetLineups);
		
		playerCSV = new JTextField();
		playerCSV.setBounds(12, 112, 346, 25);
		frame.getContentPane().add(playerCSV);
		playerCSV.setColumns(10);
		
		JButton btnNewButton = new JButton("Open \r\nPlayer \r\nCSV");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userDir = System.getProperty("user.home");
				JFileChooser readChooser = new JFileChooser(userDir + "//Desktop");
				int rVal = readChooser.showOpenDialog(readChooser);
				if(rVal == JFileChooser.APPROVE_OPTION)
				{
					playerCSV.setText(readChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnNewButton.setBounds(389, 102, 141, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton mergeButton = new JButton("Merge Lineups and CSV");
		mergeButton.setBounds(361, 209, 169, 62);
		mergeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				if(dailyLineups == null)
				{
					JOptionPane.showMessageDialog(frame, "Update lineups first!");
					return;
				}
				try {
					fdPlayerList = csvReader.ReadInPlayerCSV(playerCSV.getText());
					System.out.println("got player list from csv");
					TeamObj oneTable = TableMerger.mergeTables(fdPlayerList, dailyLineups);
					int maxSalary = Integer.parseInt(maxSal.getText());
					int minSalary = Integer.parseInt(minSal.getText());
					System.out.println("max " + maxSalary + " min " + minSalary);
					PrintLines.createFinalProduct(oneTable, maxSalary, minSalary);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("csv could not be read");
					JOptionPane.showMessageDialog(frame, "Problem reading in CSV");
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(mergeButton);
		
		minSal = new JTextField();
		minSal.setText("52000");
		minSal.setBounds(12, 176, 116, 22);
		frame.getContentPane().add(minSal);
		minSal.setColumns(10);
		
		JLabel lblMinSalary = new JLabel("Min Salary");
		lblMinSalary.setBounds(137, 179, 74, 16);
		frame.getContentPane().add(lblMinSalary);
		
		maxSal = new JTextField();
		maxSal.setText("55000");
		maxSal.setBounds(12, 229, 116, 22);
		frame.getContentPane().add(maxSal);
		maxSal.setColumns(10);
		
		JLabel lblMaxSalary = new JLabel("Max Salary");
		lblMaxSalary.setBounds(137, 232, 74, 16);
		frame.getContentPane().add(lblMaxSalary);
		
	}
}
