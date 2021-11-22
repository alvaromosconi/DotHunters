package gui;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entities.Entity;
import logic.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndLevelGUI extends JFrame {
	
	private Game game;
	private String domainRoute;
	private Color color;
	private JTextField textFieldFinishLevel;
	private JTextField textFieldPlayerName;
	
	

	public EndLevelGUI(Game game, String domainRoute) {
		
		this.game = game;
		this.domainRoute = domainRoute;
		setupWindow();
		color = color.decode("#01579B");
		getContentPane().setBackground(color);
		getContentPane().setLayout(null);
		
		super.setVisible(true);
		super.pack();
		
		
		if(game.getLevel() < 3)
			this.esperar(2);
		else
			this.esperar(10);
		
		dispose();

	}
	
	public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }   
	
	private void setupWindow() {
		
		if(game.getLevel() == 1 || game.getLevel() == 2) 
			setLevelCompletedWindow();
		else
			setGameCompletedWindow();
		
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 400)); 
        setLocationRelativeTo(null);               
	    setResizable(false);
	    
	}
	
	private void setLevelCompletedWindow() {
		
		JLabel lblLevel = new JLabel("");
		lblLevel.setIcon(new ImageIcon(EndLevelGUI.class.getResource("/assets/MenuAssets/level.png")));
		lblLevel.setBounds(141, 27, 165, 73);
		getContentPane().add(lblLevel);
		
		JLabel lblCompleted = new JLabel("");
		lblCompleted.setIcon(new ImageIcon(EndLevelGUI.class.getResource("/assets/MenuAssets/completed.png")));
		lblCompleted.setBounds(67, 201, 348, 73);
		getContentPane().add(lblCompleted);
		
		JLabel lblNumberLevel = new JLabel("");
		lblNumberLevel.setBounds(197, 111, 104, 84);
		
		if(game.getLevel() == 1 || game.getLevel() == 2) {
			if(game.getLevel() == 1) 
				lblNumberLevel.setIcon(new ImageIcon(EndLevelGUI.class.getResource("/assets/MenuAssets/1.png")));
			else 
				lblNumberLevel.setIcon(new ImageIcon(EndLevelGUI.class.getResource("/assets/MenuAssets/2.png")));
		}
		getContentPane().add(lblNumberLevel);
		
	}
	
	private void setGameCompletedWindow() {
		
		/*Close App button*/
		JButton btnCloseApp = new JButton("");
		btnCloseApp.setBackground(color);
		btnCloseApp.setBorder(null);
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
		btnCloseApp.setIcon(new ImageIcon(GraphicMenu.class.getResource("/assets/MenuAssets/closeImage.png")));
		btnCloseApp.setBounds(400, 0, 50, 47);
		getContentPane().add(btnCloseApp);
		
		/*Game Completed*/
		JLabel lblLevel = new JLabel("");
		lblLevel.setIcon(new ImageIcon(EndLevelGUI.class.getResource("/assets/MenuAssets/game.png")));
		lblLevel.setBounds(141, 27, 165, 73);
		getContentPane().add(lblLevel);
		
		/*Player Name Textfield*/
		textFieldPlayerName = new JTextField();
		textFieldPlayerName.grabFocus();
		textFieldPlayerName.setForeground(Color.WHITE);
		textFieldPlayerName.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		textFieldPlayerName.setBackground(color);
		textFieldPlayerName.setText("Player");
		textFieldPlayerName.setBounds(254, 256, 148, 20);
		textFieldPlayerName.setBorder(null);
		textFieldPlayerName.setColumns(10);
		textFieldPlayerName.grabFocus();
		getContentPane().add(textFieldPlayerName);
		
		/*Player Name Label*/
		JLabel lblName = new JLabel("Your name:");
		lblName.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(93, 248, 123, 32);
		getContentPane().add(lblName);
		
		/*Player's Score Label*/
		JLabel lblScore = new JLabel("Your score: " + game.getScore());
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.BLACK);
		lblScore.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		lblScore.setBounds(70, 175, 310, 80);
		getContentPane().add(lblScore);
		
		/*Underline Label*/
		JLabel lblLine = new JLabel("New label");
		lblLine.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/MenuAssets/playerNameLine.png")));
		lblLine.setBounds(202, 276, 224, 14);
		getContentPane().add(lblLine);
		
		/*HighScores Button*/
		JButton btnHighScores = new JButton("High Scores");
		btnHighScores.setForeground(Color.BLACK);
		btnHighScores.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		btnHighScores.setBackground(color);
		btnHighScores.setBorder(null);
		btnHighScores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HighScores hs = new HighScores(domainRoute);
				hs.addScore(game.getScore(),textFieldPlayerName.getText());
				dispose();
			}
		});
		
		btnHighScores.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnHighScores.setForeground(Color.white);
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnHighScores.setForeground(Color.BLACK);
		    }
		});
		btnHighScores.setBounds(115,290, 211, 60);
		getContentPane().add(btnHighScores);
		
		JLabel lblCompleted = new JLabel("");
		lblCompleted.setIcon(new ImageIcon(EndLevelGUI.class.getResource("/assets/MenuAssets/completed.png")));
		lblCompleted.setBounds(67, 111, 348, 73);
		getContentPane().add(lblCompleted);
		
	}
}
