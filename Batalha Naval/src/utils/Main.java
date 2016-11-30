package utils;
import controller.Game;

public class Main {
	public static void main(String[] args) {
		Game newGame = Game.getMainGame();
		newGame.initiateGame();
	}
}
