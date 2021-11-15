package gui;
import javax.swing.JFrame;

import logic.Game;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class GraphicMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GraphicMenu() {
		
		
		getContentPane().setLayout(null);
		
		JButton btnStartGame = new JButton("Play");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Game game = new Game();
			}
		});
	
		btnStartGame.setBounds(164, 101, 119, 47);
		getContentPane().add(btnStartGame);
		
		setupWindow();
		
		super.setVisible(true);
	
	}
	
	
	private void setupWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 800)); 
        setLocationRelativeTo(null);
		//setUndecorated(true);
	   //setResizable(false);

	}
}
