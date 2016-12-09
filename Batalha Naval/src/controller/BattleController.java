package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Coordinate;
import model.Player;
import view.BattleFrame;

@SuppressWarnings("serial")
public class BattleController extends BattleFrame implements ActionListener {

	BattleController() {
		super();
		this.setTitle("Batalha Naval"); 
		this.setVisible(true);
		
		setFirstBoardListener();
		setSecondBoardListener();
		getTurnButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameController gameManager = GameController.getMainGameManager();
		
		gameManager.changePlayerTurn();
		Player activePlayer = gameManager.getActivePlayer();
		
		activePlayer.refreshShots();
		this.setInstruction("Vez de " + activePlayer.getName() + ": " + activePlayer.getShotsLeft() + " tiros restantes");
		getTurnButton().setEnabled(false);
		
		Player currentPlayer = gameManager.getActivePlayer();
		Player opponentPlayer = gameManager.getWaitingPlayer();
	
		
		getFirstBoardPanel().updateAtackBoardForPlayer(opponentPlayer, currentPlayer);
		getSecondBoardPanel().updateAtackBoardForPlayer(currentPlayer, opponentPlayer);
		
		getContentPane().revalidate();
	}
	
	public void setFirstBoardListener() {
		getFirstBoardPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Do Nothing;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				getFirstBoardPanel().updateAtackBoardForPlayer(GameController.getMainGameManager().getWaitingPlayer(), GameController.getMainGameManager().getActivePlayer());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				getFirstBoardPanel().updateBoardForPlayer(GameController.getMainGameManager().getActivePlayer());
			}
		});
		
	}
	
	public void setSecondBoardListener() {
		getSecondBoardPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				secondBoardMousePressedHandler(e.getX(), e.getY());
			}
		});
	}
	
	private void secondBoardMousePressedHandler(int x, int y) {
		
		GameController gameManager = GameController.getMainGameManager();
		if(gameManager.getActivePlayer().getShotsLeft() > 0){
			int line = x/25;
			int column = y/25;
			Coordinate selectedCoord = new Coordinate(line, column);
			Player currentPlayer = gameManager.getActivePlayer();
			Player opponentPlayer = gameManager.getWaitingPlayer();
			
			currentPlayer.setNewAtack(selectedCoord);
			setInstruction("Vez de " + currentPlayer.getName() + ": " + currentPlayer.getShotsLeft() + " tiros restantes");
					
			getSecondBoardPanel().updateAtackBoardForPlayer(currentPlayer, opponentPlayer);
			
			if(currentPlayer.getShotsLeft() <= 0){
				getTurnButton().setEnabled(true);
			}
		}		
	}
	
	
}
