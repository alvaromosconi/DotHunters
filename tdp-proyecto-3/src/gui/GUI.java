package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import entities.ActivePotionTypeA;
import entities.Entity;
import logic.Game;
import java.awt.Font;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Game game;
	private String backgroundUrl;
	private ImageIcon backgroundImage;
	private JPanel panel = new JPanel();
	private JLabel lblScoreNumber;
	
	private KeyListener keyListener;
	private Map<Entity, JLabel> labelMap;

	public GUI(Game game, String backgroundUrl) {
		
		this.game = game;
		this.backgroundUrl = backgroundUrl;
		labelMap = new HashMap<Entity, JLabel>();
		
		setupWindow();

		addKeyListener();
		
		super.setVisible(true);
		super.pack();	
	}
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 684)); 
        setLocationRelativeTo(null);        
        
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblScoreWord = new JLabel("Score:");
		lblScoreWord.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		lblScoreWord.setBounds(983, 11, 107, 23);
		panel.add(lblScoreWord);
		
		lblScoreNumber = new JLabel(""+game.getScore());
		lblScoreNumber.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		lblScoreNumber.setBounds(983, 45, 107, 23);
		panel.add(lblScoreNumber);
       
		setUndecorated(true);
	    setResizable(false);
	}
	
	public void setupBackground() {
		
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setSize(new Dimension(1100, 684));
		backgroundImage = new ImageIcon(GUI.class.getResource(backgroundUrl));
		backgroundLabel.setIcon(backgroundImage);
		

		backgroundLabel.setLocation(0, 0);
		getContentPane().add(backgroundLabel);

	}

	
	private void addKeyListener() {
		
		keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
//				if (e.getKeyCode() == KeyEvent.VK_P){
//				   game.potionTypeAEvent();
//				}else{
				   game.movePlayer(e);
	//			}
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

		labelMap.put(entity, entityLabel);
		panel.add(entityLabel);
		panel.setComponentZOrder(labelMap.get(entity), 0);
		
	}
	
	public void refreshEntity(Entity entity) {

		labelMap.get(entity).setLocation( (int) entity.getXValue(), (int) entity.getYValue());

	}
	
	public void refreshImage(Entity entity ) {
		labelMap.get(entity).setIcon(new ImageIcon(GUI.class.getResource(entity.getImageRoute())));
		lblScoreNumber.setText(""+game.getScore());
	}
	
	public void addWall(Entity w) {
		
		JLabel wallLabel = new JLabel("");
		wallLabel.setBounds( (int) w.getXValue(), (int) w.getYValue(), w.getWidth(), w.getHeight());
		
	//	wallLabel.setOpaque(rootPaneCheckingEnabled);
	//	wallLabel.setBackground(Color.BLUE);
		
		panel.add(wallLabel);
		labelMap.put(w, wallLabel);	
	}

	public JLabel getLabel(Entity e) {
		
		return labelMap.get(e);
	}

	public void addDoorWay(Entity d) {
	
		JLabel doorWayLabel = new JLabel("");
		doorWayLabel.setBounds( (int) d.getXValue(), (int) d.getYValue(), d.getWidth(), d.getHeight());
		
		//doorWayLabel.setOpaque(rootPaneCheckingEnabled);
		//doorWayLabel.setBackground(Color.BLUE);
		
		panel.add(doorWayLabel);
		labelMap.put(d, doorWayLabel);
		
	}
	
	public void tray(Entity e) {
		
		panel.setComponentZOrder(labelMap.get(e), 0);
	}

	public void deleteEntity(Entity a) {
		
	    Timer timer = new Timer(1700, (ActionListener) new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				
				panel.remove(labelMap.get(a));
				
			}
        });
        // start Tick-Tack
        timer.start();


	
	}
}
