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
import logic.SoundHandler;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Game game;
	private SoundHandler soundHandler;
	
	private JPanel panel = new JPanel();
	private Color color;
	private ImageIcon backgroundImage;
	private JLabel lblBomb;
	private JLabel lblScoreNumber;
	private JLabel lblVidas;
	
	private String backgroundUrl;
	private KeyListener keyListener;
	private Map<Entity, JLabel> labelMap;

	public GUI(Game game, String backgroundUrl) {
		
		this.game = game;
		this.backgroundUrl = backgroundUrl;
		
		labelMap = new HashMap<Entity, JLabel>();
		soundHandler = game.getSoundHandler();

		setupWindow();
		addKeyListener();
		
		super.setVisible(true);
		super.pack();
		
	    foco();
	}
	
	/*
	 * keyListener que tiene los controles disponibles (Delega en game). 
	 */
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
	
	/*
	 * Añade las entidades graficamente al escenario y las añade al mapeo con el label.
	 * @param entity Entidad a agregar graficamente a el escenario.
	 */
	public void addEntity(Entity entity) {
		
		JLabel entityLabel = new JLabel("");

		System.out.println(entity.getImageRoute());
		ImageIcon entityImage = new ImageIcon(GUI.class.getResource(entity.getImageRoute()));
	
		entityLabel.setBounds(entity.getXValue(),entity.getYValue() , entity.getWidth(), entity.getHeight() );
		entityLabel.setIcon(entityImage);
		entityLabel.setHorizontalAlignment(SwingConstants.CENTER);

		labelMap.put(entity, entityLabel);
		panel.add(entityLabel);
		panel.setComponentZOrder(labelMap.get(entity), 0);	
	}
	
	/*
	 * Actualizamente la posicion de la entidad graficamente.
	 * @param entity Entidad a actualizar posicion.
	 */
	public void refreshLocationOfEntity(Entity entity) {

		JLabel entityLabel = labelMap.get(entity);
		
		if (entityLabel != null)
			labelMap.get(entity).setLocation(entity.getXValue(), entity.getYValue());

	}
	
	/*
	 * Actualizamente la imagen de la entidad.
	 * @param entity Entidad a actualizar imagen.
	 */
	public void refreshImage(Entity entity ) {
			
		JLabel entityLabel = labelMap.get(entity);
		
		if (entityLabel != null)
			labelMap.get(entity).setIcon(new ImageIcon(GUI.class.getResource(entity.getImageRoute())));
		
		lblScoreNumber.setText("" + game.getScore());
	}
	
	/*
	 * Añade los labels de las paredes (No tienen imagen)
	 * @param wall pared a agregar.
	 */
	public void addWall(Entity wall) {
		
		JLabel wallLabel = new JLabel("");
		wallLabel.setBounds(wall.getXValue(), wall.getYValue(), wall.getWidth(), wall.getHeight());
		panel.add(wallLabel);
		labelMap.put(wall, wallLabel);	
	}

	/*
	 * Añade los labels de los doorway (No tienen imagen)
	 * @param doorway pared a agregar.
	 */
	public void addDoorWay(Entity doorway) {
	
		JLabel doorWayLabel = new JLabel("");
		doorWayLabel.setBounds(doorway.getXValue(),doorway.getYValue(), doorway.getWidth(), doorway.getHeight());	
		panel.add(doorWayLabel);
		labelMap.put(doorway, doorWayLabel);		
	}
	
	/*
	 * Destruye la referencia a la entidad y la elimina graficamente.
	 * @param entityToDestroy entidad a destruir.
	 */
	public void destroyEntity(Entity entityToDestroy) {
		
		JLabel entityLabel = labelMap.get(entityToDestroy);
		
		if (entityLabel != null) {
			panel.remove(labelMap.get(entityToDestroy));
			labelMap.remove(entityToDestroy);
		}
	}
	
	/*
	 * Actualiza la representacion de las vidas
	 */
	public void updateLives() {
		lblVidas.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/lives" + game.getLifes() + ".png")));
	}
	
	/*
	 * Actualiza la representacion del powerTypeA
	 */
	public void dropBomb() {
		lblBomb.setIcon(new ImageIcon(GUI.class.getResource(game.getDomainRoute() + "bomb.png")));
	}
	
	/*
	 * Elimina la representacion del powerTypeA graficamente.
	 */
	public void desvisualizarBomba() {
		lblBomb.setIcon(null);
	}
	
	/*
	 *  Se encarga de dimensionar y estilizar el fondo de la ventana.
	 */
	public void setupBackground() {
		
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setSize(new Dimension(1100, 684));
		backgroundImage = new ImageIcon(GUI.class.getResource(backgroundUrl));
		backgroundLabel.setIcon(backgroundImage);
		
		backgroundLabel.setLocation(0, 0);
		getContentPane().add(backgroundLabel);
		repaint();
	}
	
	/*
	 * Metodo encargado de estilizar y dimensionar la ventana del juego.
	 */
	private void setupWindow() {
		
		color = color.decode("#01579B");	
		panel.setBackground(color);
		
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
	
	/*
	 * Metodo encargado de crear los botones necesarios para la ventana del juego.
	 */
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
	
		
		JButton btnSound = new JButton("");
		btnSound.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				soundHandler.changeSound();
				if (soundHandler.isSound())
					btnSound.setIcon(new ImageIcon(GUI.class.getResource(game.getDomainRoute() + "soundON.png")));
				else
					btnSound.setIcon(new ImageIcon(GUI.class.getResource(game.getDomainRoute() + "soundOFF.png")));
				
				foco();
			}
		});
		
		if (soundHandler.isSound())
			btnSound.setIcon(new ImageIcon(GUI.class.getResource(game.getDomainRoute() + "soundON.png")));
		else
			btnSound.setIcon(new ImageIcon(GUI.class.getResource(game.getDomainRoute() + "soundOFF.png")));
		
		btnSound.setBounds(1053, 636, 37, 37);
		btnSound.setBackground(new Color(1, 87, 155));
		btnSound.setBorder(null);
		
		panel.add(btnCloseApp);
		panel.add(btnSound);
		
	}
	
	/*
	 * Metodo encargado de crear los labels necesarios
	 */
	private void setupLabels() {
		
		JLabel lblScoreWord = new JLabel("Score:");
		lblScoreWord.setForeground(Color.BLACK);
		lblScoreWord.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
		lblScoreWord.setBounds(983, 79, 107, 38);

		lblScoreNumber = new JLabel(""+game.getScore());
		lblScoreNumber.setForeground(Color.BLACK);
		lblScoreNumber.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblScoreNumber.setBounds(983, 128, 107, 23);
		
		JLabel lblArrows = new JLabel("");
		lblArrows.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/arrows2.png")));
		lblArrows.setBounds(983, 175, 86, 73);
		
		JLabel lblMove = new JLabel("Move");
		lblMove.setForeground(Color.BLACK);
		lblMove.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblMove.setBounds(983, 247, 93, 38);
		
		JLabel lblP = new JLabel("P");
		lblP.setForeground(Color.BLACK);
		lblP.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblP.setBounds(1009, 296, 33, 89);
				
		JLabel lblPower = new JLabel("Power");
		lblPower.setForeground(Color.BLACK);
		lblPower.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblPower.setBounds(983, 370, 93, 38);
		
		lblVidas = new JLabel("");
		lblVidas.setBounds(974, 419, 116, 36);
		lblVidas.setIcon(new ImageIcon(GUI.class.getResource("/assets/MenuAssets/lives" + game.getLifes() + ".png")));
	
		lblBomb = new JLabel("");
		lblBomb.setBounds(980, 479, 37, 37);
	
		panel.add(lblScoreWord);
		panel.add(lblScoreNumber);
		panel.add(lblArrows);
		panel.add(lblMove);
		panel.add(lblP);
		panel.add(lblPower);
		panel.add(lblVidas);
		panel.add(lblBomb);
	}
	
	/*
	 * Devuelve el foco a la ventana GUI
	 */
	public void foco() {
		this.requestFocus();
	}
	
	
	
}
