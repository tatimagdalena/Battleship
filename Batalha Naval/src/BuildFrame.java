//View
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class BuildFrame extends JFrame implements ActionListener{
	
	private GameBoard boardPanel = new GameBoard();
	private JPanel instructionPanel = new JPanel();
	private JButton turnButton = new JButton("Pronto!");
	private JPanel hidroavioes = new JPanel();
	private Boat[] hidroaviao = new Hidroaviao[4];
	
	public BuildFrame() {
		//Gofigurações de Janela
		ScreenDimensions screen = ScreenDimensions.getScreenDimensions();
		setSize(screen.screenIntWidth, screen.screenIntHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(null);
		
		//Panel de Intruções ao jogador
		instructionPanel.setSize(500, 40);
		instructionPanel.setLocation((int)(screen.screenIntWidth*1/2 - instructionPanel.getSize().getWidth()/2), 
				(int) 50);	
		
		//Hidroavioes
		for (int i = 0; i< 4; i++){
			hidroaviao[i] = new Hidroaviao(i);
			hidroaviao[i].setSize(hidroaviao[i].getSquareSize() * 3, hidroaviao[i].getSquareSize() * 2);
			hidroaviao[i].setLocation((int)(hidroaviao[i].getSize().getWidth() + 5) * i, 10);
			getContentPane().add(hidroaviao[i]);
		}
		
		//Tabuleiro
		boardPanel.setSize((boardPanel.getNumLines()+1)*boardPanel.getSquareSize(), 
				(boardPanel.getNumColumns()+1)*boardPanel.getSquareSize());
		boardPanel.setLocation((int)(screen.screenIntWidth*3/4 - boardPanel.getSize().getWidth()/2), 
				(int)(screen.screenIntHeight*1/2 - boardPanel.getSize().getHeight()/2));
		
		//Botao para finalizar
		turnButton.setSize(100, 50);
		turnButton.setLocation((int)(screen.screenIntWidth*1/2 - turnButton.getSize().getWidth()/2), 
				(int)(boardPanel.getLocation().getY() + boardPanel.getSize().getHeight() + 30));
		turnButton.addActionListener(this);
		
		getContentPane().add(instructionPanel);
		getContentPane().add(boardPanel);
		getContentPane().add(turnButton);
		getContentPane().add(hidroavioes);
		
		setInstruction("Player turn");
		
		
		
	}
	
	public GameBoard getPanel() {
		return boardPanel;
	}
	
	public Boat[] getBoat() {
		return hidroaviao;
	}
	
	public JPanel getInstruction(){
		return instructionPanel;
	}
	
	public void setInstruction (String text){
		instructionPanel.removeAll();
		instructionPanel.revalidate();
		Label instructionLabel = new Label(text);
		instructionPanel.add(instructionLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Game game = Game.getMainGame();
		game.changePlayerTurn();
	}
}
