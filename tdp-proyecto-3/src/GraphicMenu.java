import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicMenu extends JFrame {
	
	public GraphicMenu() {
		
		
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Game game = new Game();
			}
		});
	
		btnNewButton.setBounds(164, 101, 119, 47);
		getContentPane().add(btnNewButton);
		
		setupWindow();
		
		super.setVisible(true);
		super.pack();
	}
	
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 800)); 
		//setUndecorated(true);
	//	setResizable(false);

	}
}
