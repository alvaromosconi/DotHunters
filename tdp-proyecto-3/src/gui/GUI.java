package gui;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import entities.Entity;
import logic.Game;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Game game;
	private String backgroundUrl;
	private ImageIcon backgroundImage;
	private JPanel panel = new JPanel();
	private JLabel lblScoreNumber;
	private Color color;
	private JLabel lblVidas;
	private JLabel lblBomb;
	
	private KeyListener keyListener;
	private Map<Entity, JLabel> labelMap;

	public GUI(Game game, String backgroundUrl) {
		
		this.game = game;
		this.backgroundUrl = backgroundUrl;
		labelMap = new HashMap<Entity, JLabel>();
		color = color.decode("#01579B");
		panel.setBackground(color);
		
		setupWindow();

		addKeyListener();
		
		super.setVisible(true);
		super.pack();
		
	    foco();
	}
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 684)); 
        setLocationRelativeTo(null);     
        
		setContentPane(panel);
		panel.setLayout(null);
		
		setupLabels();
		setupButton();		
       
		setUndecorated(true);
	    setResizable(false);
	    
	    foco();
	}
	
	private void setupButton() {
		JButton btnCloseApp = new JButton("");
		btnCloseApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
		btnCloseApp.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/closeImage.png")));
		btnCloseApp.setBorder(null);
		btnCloseApp.setBackground(new Color(1, 87, 155));
		btnCloseApp.setBounds(1050, 0, 50, 47);
		panel.add(btnCloseApp);
		
		JButton btnSound = new JButton("");
		btnSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.changeSound();
				if (game.isSound())
					btnSound.setIcon(new ImageIcon(GUI.class.getResource("/assets/MarioAssets/soundON.png")));
				else
					btnSound.setIcon(new ImageIcon(GUI.class.getResource("/assets/MarioAssets/soundOFF.png")));
				
				foco();
			}
		});
		if (game.isSound())
			btnSound.setIcon(new ImageIcon(GUI.class.getResource("/assets/MarioAssets/soundON.png")));
		else
			btnSound.setIcon(new ImageIcon(GUI.class.getResource("/assets/MarioAssets/soundOFF.png")));
		btnSound.setBounds(1053, 636, 37, 37);
		btnSound.setBackground(new Color(1, 87, 155));
		btnSound.setBorder(null);
		panel.add(btnSound);
		
	}
	
	public void foco() {
		this.requestFocus();
	}
	
	private void setupLabels() {
		JLabel lblScoreWord = new JLabel("Score:");
		lblScoreWord.setForeground(Color.BLACK);
		lblScoreWord.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
		lblScoreWord.setBounds(983, 79, 107, 38);
		panel.add(lblScoreWord);
		
		lblScoreNumber = new JLabel(""+game.getScore());
		lblScoreNumber.setForeground(Color.BLACK);
		lblScoreNumber.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblScoreNumber.setBounds(983, 128, 107, 23);
		panel.add(lblScoreNumber);
		
		JLabel lblArrows = new JLabel("");
		lblArrows.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/arrows2.png")));
		lblArrows.setBounds(983, 175, 86, 73);
		panel.add(lblArrows);
		
		JLabel lblMove = new JLabel("Move");
		lblMove.setForeground(Color.BLACK);
		lblMove.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblMove.setBounds(983, 247, 93, 38);
		panel.add(lblMove);
		
		JLabel lblP = new JLabel("P");
		lblP.setForeground(Color.BLACK);
		lblP.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblP.setBounds(1009, 296, 33, 89);
		panel.add(lblP);
		
		JLabel lblPower = new JLabel("Power");
		lblPower.setForeground(Color.BLACK);
		lblPower.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblPower.setBounds(983, 370, 93, 38);
		panel.add(lblPower);
		
		lblVidas = new JLabel("");
		lblVidas.setBounds(974, 419, 116, 36);
		lblVidas.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/lives" + game.getLifes() + ".png")));
		panel.add(lblVidas);
		
		lblBomb = new JLabel("");
		lblBomb.setBounds(980, 479, 37, 37);
		panel.add(lblBomb);
	}
	public void setupBackground() {
		
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setSize(new Dimension(1100, 684));
		backgroundImage = new ImageIcon(GUI.class.getResource(backgroundUrl));
		backgroundLabel.setIcon(backgroundImage);
		

		backgroundLabel.setLocation(0, 0);
		getContentPane().add(backgroundLabel);
		repaint();

	}

	
	private void addKeyListener() {
		
		keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_P) {
				   game.potionTypeAEvent();
				   desvisualizarBomba();
				   }
				else
				   game.movePlayer(e);
			
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		};

		this.addKeyListener(keyListener);
	}
	
	
	public void addEntity(Entity entity) {
		
		JLabel entityLabel = new JLabel("");
		ImageIcon entityImage = new ImageIcon(GUI.class.getResource(entity.getImageRoute()));

	//	entityLabel.setOpaque(rootPaneCheckingEnabled);
	//	entityLabel.setBackground(Color.BLUE);
	
		entityLabel.setBounds( (int) entity.getXValue(), (int) entity.getYValue() , entity.getWidth(), entity.getHeight() );
		entityLabel.setIcon(entityImage);
		entityLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		System.out.println(entity.getImageRoute());
		labelMap.put(entity, entityLabel);
		panel.add(entityLabel);
		panel.setComponentZOrder(labelMap.get(entity), 0);
		

		
	}
	
	public void refreshEntity(Entity entity) {

		JLabel entityLabel = labelMap.get(entity);
		
		if (entityLabel != null)
			labelMap.get(entity).setLocation(entity.getXValue(), entity.getYValue());

	}
	
	public void refreshImage(Entity entity ) {
		
		
		JLabel entityLabel = labelMap.get(entity);
		
		if (entityLabel != null)
			labelMap.get(entity).setIcon(new ImageIcon(GUI.class.getResource(entity.getImageRoute())));
		
		lblScoreNumber.setText("" + game.getScore());
	}
	
	public void addWall(Entity w) {
		
		JLabel wallLabel = new JLabel("");
		wallLabel.setBounds( (int) w.getXValue(), (int) w.getYValue(), w.getWidth(), w.getHeight());
		
	//	wallLabel.setOpaque(rootPaneCheckingEnabled);
	//	wallLabel.setBackground(Color.BLUE);
		
		panel.add(wallLabel);
		labelMap.put(w, wallLabel);	
	}


	public void addDoorWay(Entity d) {
	
		JLabel doorWayLabel = new JLabel("");
		doorWayLabel.setBounds( (int) d.getXValue(), (int) d.getYValue(), d.getWidth(), d.getHeight());
		
		//doorWayLabel.setOpaque(rootPaneCheckingEnabled);
		//doorWayLabel.setBackground(Color.BLUE);
		
		panel.add(doorWayLabel);
		labelMap.put(d, doorWayLabel);
		
	}
	
	public void destroyEntity(Entity entityToDestroy) {
		
		panel.remove(labelMap.get(entityToDestroy));
		labelMap.remove(entityToDestroy);

	}
	
	public void updateLives() {
		lblVidas.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/lives" + game.getLifes() + ".png")));
	}
	
	public void visualizarBomba() {
		lblBomb.setIcon(new ImageIcon(GUI.class.getResource("/assets/MarioAssets/bomb.png")));
	}
	
	public void desvisualizarBomba() {
		lblBomb.setIcon(null);
	}
}
