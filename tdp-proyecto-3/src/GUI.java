import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
	
	private Game game;
	private String backgroundUrl;
	private ImageIcon backgroundImage;
	private JPanel panel = new JPanel();
	

	public GUI(Game game, String backgroundUrl) {
		
		this.game = game;
		this.backgroundUrl = backgroundUrl;
		panel.setLayout(null);
		setupWindow();
		setupBackground();
		
		super.setVisible(true);
		super.pack();
		
	}
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 800)); 
        setLocationRelativeTo(null);
        setContentPane(panel);
		//setUndecorated(true);
	    //setResizable(false);

	}
	
	private void setupBackground() {
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, 0, 800, 800);
		backgroundImage = new ImageIcon(GUI.class.getResource(backgroundUrl));

		backgroundLabel.setIcon(backgroundImage);
		panel.add(backgroundLabel);
		setComponentZOrder(backgroundLabel, 1);
	}

	public void addGraphicEntity(GraphicEntity graphicEntity) {
		
		JLabel player = new JLabel("");
		ImageIcon entityImage = new ImageIcon(GUI.class.getResource(graphicEntity.getImageRoute()));
		player.setIcon(entityImage);
		player.setBounds(graphicEntity.getXValue(), graphicEntity.getYValue(), 500, 500);
		panel.add(player);
		setComponentZOrder(player, 1);
		
	}
	
	
	

}
