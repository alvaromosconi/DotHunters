package gui;

import java.awt.Color;
import java.awt.Dimension;
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

import logic.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
	private Color color;
	private JButton btnCloseApp;
	private JButton btnBackArrow;
	private String domainRoute;
	private String nameString;
	private String scoreString;
	private String highScoreString;

	public HighScores(String domainRoute) {

		setupWindow();
		this.domainRoute = domainRoute;
		color = color.decode("#01579B");
		super.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBackground(color);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel title = new JLabel("");
		title.setIcon(new ImageIcon(HighScores.class.getResource("/assets/MenuAssets/highScoresImage.png")));
		title.setBounds(79, 59, 371, 104);
		JLabel lblScoreWord = new JLabel("Score:");
		lblScoreWord.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		contentPane.setLayout(null);
		contentPane.add(title);
		
		setupLabels();
		setupButtons();
		chargeScores();


	}

	
	private void setupButtons() {
		

		btnCloseApp = new JButton("");
		btnCloseApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCloseApp.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnCloseApp.setIcon(new ImageIcon(GraphicMenu.class.getResource("/assets/MenuAssets/hoverCloseImage.png")));
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnCloseApp.setIcon(new ImageIcon(GraphicMenu.class.getResource("/assets/MenuAssets/closeImage.png")));
		    }
		});
		btnCloseApp.setIcon(new ImageIcon(HighScores.class.getResource("/assets/MenuAssets/closeImage.png")));
		btnCloseApp.setBorder(null);
		btnCloseApp.setBackground(new Color(1, 87, 155));
		btnCloseApp.setBounds(483, 1, 50, 47);
		contentPane.add(btnCloseApp);
		
		btnBackArrow = new JButton("");
		btnBackArrow.setBorder(null);
		btnBackArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicMenu g = new GraphicMenu();
				dispose();
			}
		});
		btnBackArrow.setIcon(new ImageIcon(HighScores.class.getResource("/assets/MenuAssets/backArrowImage.png")));
		btnBackArrow.setBorder(null);
		btnBackArrow.setBackground(new Color(1, 87, 155));
		btnBackArrow.setBounds(433, 1, 40, 47);
		contentPane.add(btnBackArrow);
	}
	
	
	private void setupLabels() {
		lblScore0 = new JLabel("New label");
		lblScore0.setForeground(Color.BLACK);
		lblScore0.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblScore0.setBounds(79, 216, 436, 26);
		contentPane.add(lblScore0);

		lblScore1 = new JLabel("New label");
		lblScore1.setForeground(Color.BLACK);
		lblScore1.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblScore1.setBounds(79, 253, 869, 26);
		contentPane.add(lblScore1);

		lblScore2 = new JLabel("New label");
		lblScore2.setForeground(Color.BLACK);
		lblScore2.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblScore2.setBounds(79, 290, 869, 26);
		contentPane.add(lblScore2);

		lblScore3 = new JLabel("New label");
		lblScore3.setForeground(Color.BLACK);
		lblScore3.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblScore3.setBounds(79, 327, 869, 26);
		contentPane.add(lblScore3);

		lblScore4 = new JLabel("New label");
		lblScore4.setForeground(Color.BLACK);
		lblScore4.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblScore4.setBounds(79, 364, 869, 26);
		contentPane.add(lblScore4);
		
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
		    setFormat(lblScore0,Scores[0]);
		    setFormat(lblScore1,Scores[1]);
		    setFormat(lblScore2,Scores[2]);
		    setFormat(lblScore3,Scores[3]);
		    setFormat(lblScore4,Scores[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void setFormat(JLabel label, String string) {
		scoreString = string.replaceAll("\\D+","");
	    nameString = string.replaceAll("[0-9]","");
	    highScoreString = scoreString;
	    while(highScoreString.length() + nameString.length() < 55) {
	    	highScoreString +=".";
	    }
	    highScoreString+=nameString;
	    label.setText(highScoreString);
		
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
		    		  originalFileContent+= Score+" "+name + System.lineSeparator();
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
	
	private void setupWindow() {
		setResizable(false);
		setMinimumSize(new Dimension(500, 500)); 
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 485);
		
	}
}