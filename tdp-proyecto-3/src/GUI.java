import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
	
	private Game game;
	private String backgroundUrl;
	private ImageIcon backgroundImage;
	private JPanel panel = new JPanel();
	private JLabel player;
	

	public GUI(Game game, String backgroundUrl) {
		
		this.game = game;
		this.backgroundUrl = backgroundUrl;

		setupWindow();

		super.setVisible(true);
		super.pack();	
	}
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 800)); 
        setLocationRelativeTo(null);
        
		setContentPane(panel);
		panel.setLayout(null);
       
		//setUndecorated(true);
	    //setResizable(false);

	}
	
	public void setupBackground() {
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, 0, 800, 800);
		backgroundImage = new ImageIcon(GUI.class.getResource(backgroundUrl));

		backgroundLabel.setIcon(backgroundImage);
		this.add(backgroundLabel);
	}

	
	public void addGraphicEntity(GraphicEntity graphicEntity) {
		
	    player = new JLabel("");
		player.setBounds(graphicEntity.getXValue(), graphicEntity.getYValue()+15, 50, 50);
		ImageIcon entityImage = new ImageIcon(GUI.class.getResource(graphicEntity.getImageRoute()));
		player.setIcon(entityImage);

		panel.add(player);
		
		setupBackground();
	}

}
