import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
	
	private Game game;
	private String backgroundUrl;
	private ImageIcon backgroundImage;
	private JPanel panel = new JPanel();
	
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
        setMinimumSize(new Dimension(1072, 800)); 
        setLocationRelativeTo(null);
        
		setContentPane(panel);
		panel.setLayout(null);
       
		//setUndecorated(true);
	    //setResizable(false);

	}
	
	public void setupBackground() {
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, 0, 1024, 688);
		backgroundImage = new ImageIcon(GUI.class.getResource(backgroundUrl));
		backgroundLabel.setIcon(backgroundImage);
		backgroundLabel.setLocation( (panel.getWidth() - backgroundLabel.getWidth()) / 2, (panel.getHeight() - backgroundLabel.getHeight()) / 2);
		this.add(backgroundLabel);
	}

	
	private void addKeyListener() {
		
		keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
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
		entityLabel.setIcon(entityImage);
		entityLabel.setBounds(entity.getXValue(), entity.getYValue(), entityImage.getIconWidth(), entityImage.getIconHeight());
		
		panel.add(entityLabel);
		labelMap.put(entity, entityLabel);

		setupBackground(); 
	}
	
	public void refreshEntity(Entity entity) {
	
		labelMap.get(entity).setLocation(entity.getXValue(), entity.getYValue());
	}
	

}
