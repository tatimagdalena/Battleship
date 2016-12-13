package view;
//View

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.GameController;
import controller.GamePresenter;
import controller.MenuController;
import utils.ScreenDimensions;

@SuppressWarnings("serial")
public class NamingFrame extends JFrame implements ActionListener {
	
	private final int FRAME_WIDTH = 300; 
	private final int FRAME_HEIGHT = 200;
	
	private JTextField firstPlayerField = new JTextField("Jogador 1");
	private JTextField secondPlayerField = new JTextField("Jogador 2");
	private JButton startButton = new JButton("Começar");
	
	public NamingFrame() {
		
		JPanel firstPlayerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 25));
		JPanel secondPlayerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		Label firstPlayerLabel = new Label("Jogador 1: ");
		Label secondPlayerLabel = new Label("Jogador 2: ");
		
		firstPlayerPanel.add(firstPlayerLabel);
		firstPlayerPanel.add(firstPlayerField);
		secondPlayerPanel.add(secondPlayerLabel);
		secondPlayerPanel.add(secondPlayerField);
		getContentPane().add(firstPlayerPanel);
		getContentPane().add(secondPlayerPanel);
		getContentPane().add(startButton);
		
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		int initialX = (int) (screen.screenCenterX - FRAME_WIDTH/2);
		int initialY = (int) (screen.screenCenterY - FRAME_HEIGHT/2);
		setBounds(initialX, initialY, FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		startButton.addActionListener(this);
		
		MenuController.getMenuController().createAndShowGUI(this);
	}

	
	//TODO: Trocar de classe
	@Override
	public void actionPerformed(ActionEvent e) {
		
		GameController gameManager = GameController.getMainGameManager();
		GamePresenter facade = GamePresenter.getMainGamePresenter();
		
		gameManager.setPlayer1(firstPlayerField.getText());
		gameManager.setPlayer2(secondPlayerField.getText());
		
		//TODO: verificar se faz sentido ser aqui
		facade.closeNaming();
		facade.showPositioning();
	}
	
}
