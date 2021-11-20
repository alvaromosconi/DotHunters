package gui;

import javax.swing.JFrame;

import logic.Game;
import javax.swing.JLabel;
import java.awt.BorderLayout;
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
	private JTextField textFieldPlayerName;

	public GameOverGUI(Game game) {
		getContentPane().setLayout(null);
		
		textFieldPlayerName = new JTextField();
		textFieldPlayerName.grabFocus();
		textFieldPlayerName.setForeground(Color.MAGENTA);
		textFieldPlayerName.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPlayerName.setBackground(Color.BLUE);
		textFieldPlayerName.setText("Player");
		textFieldPlayerName.setBounds(213, 256, 148, 20);
		textFieldPlayerName.setBorder(null);
		textFieldPlayerName.setColumns(10);
		textFieldPlayerName.grabFocus();
		getContentPane().add(textFieldPlayerName);
		
		
		JButton btnHighScores = new JButton("High Scores");
		btnHighScores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HighScores hs = new HighScores();
				hs.addScore(game.getScore(),textFieldPlayerName.getText());
				dispose();
			}
		});
		
		JButton btnReset = new JButton("");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Game game = new Game();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Your name:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(80, 248, 123, 32);
		getContentPane().add(lblNewLabel);
		
		
		
		
		btnReset.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/reset.png")));
		btnReset.setBounds(385, 0, 46, 42);
		getContentPane().add(btnReset);
		
		btnHighScores.setBounds(155,301, 112, 29);
		getContentPane().add(btnHighScores);
		
		JLabel lblScore = new JLabel("Your score: " + game.getScore());
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		lblScore.setBounds(70, 175, 310, 80);
		getContentPane().add(lblScore);
		
		JLabel lblGameOver = new JLabel("");
		lblGameOver.setIcon(new ImageIcon(GameOverGUI.class.getResource("/assets/GameOver.png")));
		lblGameOver.setBounds(0, 0, 444, 365);
		getContentPane().add(lblGameOver);
		
		setupWindow();
		
		super.setVisible(true);
		
	}
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 400)); 
        setLocationRelativeTo(null);        
		
        textFieldPlayerName.requestFocus();
        
	    setResizable(false);
	}
}
