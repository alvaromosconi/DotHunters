package gui;

import javax.swing.JFrame;

import logic.Game;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GameOverGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldPlayerName;
	private Color color;

	public GameOverGUI(Game game, String domainRoute) {
		setupWindow();
		color = color.decode("#01579B");
		getContentPane().setBackground(color);
		getContentPane().setLayout(null);
		
		
		
		
		setupButtons(game,domainRoute);
		setupMisc(game);
		
		super.setVisible(true);
		
	}
	
	private void setupMisc(Game game) {
		
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
		
		/*Game Over Sign Label*/
		JLabel lblGameOver = new JLabel("");
		lblGameOver.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/MenuAssets/gameOverImage.png")));
		lblGameOver.setBounds(52, 58, 332, 90);
		getContentPane().add(lblGameOver);
		
	}
	
	private void setupButtons(Game game, String domainRoute){
		
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
		
		/*Reset Game Button*/
		JButton btnReset = new JButton("");
		btnReset.setBorder(null);
		btnReset.setBackground(color);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Game game = new Game(domainRoute);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnReset.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/MenuAssets/hoverResetImage.png")));
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnReset.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/MenuAssets/resetImage.png")));
		    }
		});
		
		btnReset.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/MenuAssets/resetImage.png")));
		btnReset.setBounds(350, 0, 52, 47);
		getContentPane().add(btnReset);
	}
	
	private void setupWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 400)); 
        setLocationRelativeTo(null);               
	    setResizable(false);
	    
	}
}
