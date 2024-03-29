package gui;
import javax.swing.JFrame;


import logic.Game;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class GraphicMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Color color;
	private String domainRoute = "/assets/MarioAssets/";
	private JRadioButton rdbtnPokemon;

	public GraphicMenu() {
	
		color = color.decode("#01579B");
		getContentPane().setBackground(color);
		getContentPane().setLayout(null);
		setupWindow();
		setupButtons();
		setupMisc();
		super.setVisible(true);
	}
	
	/*
	 * Metodo encargado de estilizar y dimensionar la ventana
	 */
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 500)); 
        setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
	}
	
	/*
	 * Metodo encargado de crear botones logicos
	 */
	public void setupMisc() {
		
		JRadioButton rdbtnMario = new JRadioButton("Mario");
		rdbtnMario.setForeground(Color.BLACK);
		rdbtnMario.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		rdbtnMario.setBounds(172, 416, 109, 23);
		rdbtnMario.setBackground(color);
		rdbtnMario.setSelected(true);
		getContentPane().add(rdbtnMario);
		
		rdbtnPokemon = new JRadioButton("Pokemon");
		rdbtnPokemon.setForeground(Color.BLACK);
		rdbtnPokemon.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		rdbtnPokemon.setBounds(307, 416, 109, 23);
		rdbtnPokemon.setBackground(color);
		getContentPane().add(rdbtnPokemon);
		
		JLabel lblCharacters = new JLabel("Characters:");
		lblCharacters.setForeground(Color.BLACK);
		lblCharacters.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		lblCharacters.setBounds(43, 413, 90, 23);
		getContentPane().add(lblCharacters);
	
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(rdbtnMario);
		radioButtonGroup.add(rdbtnPokemon);
		
		JLabel lblNewLabel =  new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GraphicMenu.class.getResource("/assets/MenuAssets/title.gif")));
		lblNewLabel.setBounds(20, 42, 440, 185);
		getContentPane().add(lblNewLabel);
	}
	
	/*
	 * Metodo encargado de crear los botones y su logica
	 */
	private void setupButtons() {

		
		JButton btnStartGame = new JButton("Play");
		btnStartGame.setForeground(Color.BLACK);
		btnStartGame.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));

		btnStartGame.setBackground(color);
		btnStartGame.setBorder(null);
		btnStartGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if (rdbtnPokemon.isSelected()) 
						domainRoute = "/assets/PokemonAssets/";	
					else
						domainRoute = "/assets/MarioAssets/";	
					
					Game game = new Game(domainRoute);
					dispose();
				}
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnStartGame.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnStartGame.setForeground(Color.white);
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnStartGame.setForeground(Color.BLACK);
		    }
		});
		btnStartGame.setBounds(126, 245, 219, 47);
		getContentPane().add(btnStartGame);
		
		/*HIGH SCORES BUTTON*/
		JButton btnHighScores= new JButton("High Scores");
		btnHighScores.setForeground(Color.BLACK);
		btnHighScores.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		btnHighScores.setBorder(null);
		btnHighScores.setBackground(color);
		btnHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HighScores hs = new HighScores(domainRoute);
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
	
		btnHighScores.setBounds(126, 314, 219, 47);
		getContentPane().add(btnHighScores);
		
		
		/*CLOSE GAME BUTTON*/
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
		btnCloseApp.setBounds(450, 0, 50, 47);
		getContentPane().add(btnCloseApp);
	}
	
}

