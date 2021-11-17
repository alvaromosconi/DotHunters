package gui;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HighScores extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String file = "highScores.txt";
	private JPanel contentPane;
	private JLabel lblScore0;
	private JLabel lblScore1;
	private JLabel lblScore2;
	private JLabel lblScore3;
	private JLabel lblScore4;

	public HighScores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		super.setVisible(true);
		
		JLabel title = new JLabel("High Scores");
		title.setBounds(214, 11, 424, 14);
		JLabel lblScoreWord = new JLabel("Score:");
		lblScoreWord.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		lblScoreWord.setBounds(983, 11, 107, 23);
		contentPane.setLayout(null);
		contentPane.add(title);
		
		lblScore0 = new JLabel("New label");
		lblScore0.setBounds(56, 75, 436, 14);
		contentPane.add(lblScore0);
		
		lblScore1 = new JLabel("New label");
		lblScore1.setBounds(56, 100, 869, 14);
		contentPane.add(lblScore1);
		
		lblScore2 = new JLabel("New label");
		lblScore2.setBounds(56, 125, 869, 14);
		contentPane.add(lblScore2);
		
		lblScore3 = new JLabel("New label");
		lblScore3.setBounds(56, 150, 869, 14);
		contentPane.add(lblScore3);
		
		lblScore4 = new JLabel("New label");
		lblScore4.setBounds(56, 175, 869, 14);
		contentPane.add(lblScore4);
		
		chargeScores();
		
		
	}
	
	public void chargeScores() {

		String[] Scores = new String[5];
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	Scores[i] = line;
		    	i++;
		    }
		    br.close();
		    lblScore0.setText(Scores[0]);
		    lblScore1.setText(Scores[1]);
		    lblScore2.setText(Scores[2]);
		    lblScore3.setText(Scores[3]);
		    lblScore4.setText(Scores[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void addScore(int Score, String name) {
		int lowerScore = 0;
		String originalFileContent = "";
		boolean replace = false;
		try {
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      String line = br.readLine();
		      int scoresAmount =0;
		      while(line!=null && scoresAmount<5) {
		    	  lowerScore =Integer.valueOf(line.replaceAll("\\D+", ""));
		    	  if (Score>lowerScore && !replace) {
		    		  originalFileContent+= Score+"-"+name + System.lineSeparator();
		    		  replace = true;
		    		  scoresAmount++;
		    	  }
		    	  originalFileContent += line + System.lineSeparator();
		    	  line = br.readLine();
		    	  scoresAmount++;
		      }
		      
		      if (replace) {
		    	  String modifiedContent = originalFileContent;
		    	  BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    	  writer.write(modifiedContent);
		    	  writer.close();
		    	  
		      }
		      br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		chargeScores();
	}
	
	public int countScores() {
		int amount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    while (br.readLine() != null) {
		    	amount++;
		    }
		    br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}
}
