import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GUI implements ActionListener{
	private JFrame f;
	private JPanel p;
	
	public GUI() {
		f = new JFrame();
		
		JButton bStart = new JButton("Start!");
		bStart.addActionListener(this);
		bStart.setActionCommand("start");
		JButton bResults = new JButton("Results");
		bResults.addActionListener(this);
		bResults.setActionCommand("results");
		JButton bExit = new JButton("Exit");
		bExit.addActionListener(this);
		bExit.setActionCommand("exit");
		
		p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		p.setLayout(new GridLayout(3,1));
		p.add(bStart);
		p.add(bResults);
		p.add(bExit);
		
		f.add(p, BorderLayout.CENTER);
		f.setBounds(10, 10, 905, 700);
		f.setBackground(Color.BLACK);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Wunsz");
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new GUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
        if ("start".equals(e.getActionCommand())) {
        	JFrame f2 = new JFrame();
    		f2.setBounds(10, 10, 905, 700);
    		f2.setBackground(Color.BLACK);
    		f2.setResizable(true);
    		f2.setVisible(true);
    		Rozgrywka rozgrywka = new Rozgrywka();
    		f2.add(rozgrywka);
        }
        if ("results".equals(e.getActionCommand())) {
        	JFrame frame = new JFrame();
        	String columns[] = {"ruchy","wynik"};
            List<String[]> values = new ArrayList<String[]>();

        	try {
      	      File myObj = new File("results.txt");
      	      Scanner myReader = new Scanner(myObj);
      	      while (myReader.hasNextLine()) {
      	    	  values.add(myReader.nextLine().split(", "));
      	      }
      	      myReader.close();
      	    } catch (FileNotFoundException e1) {
      	      System.out.println("An error occurred.");
      	      e1.printStackTrace();
      	    }
        	TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns);
            JTable table = new JTable(tableModel);
            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.add(table.getTableHeader(), BorderLayout.NORTH);
            frame.setVisible(true);
            frame.setSize(200,200);
        }
        if ("exit".equals(e.getActionCommand())) {
        	System.exit(0);
        }
	}
}
