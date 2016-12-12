package utils;
//import controller.GameController;
import controller.GamePresenter;

public class BatalhaNaval {
	public static void main(String[] args) {
//		GameController newGame = GameController.getMainGameManager();
		GamePresenter facade = GamePresenter.getMainGamePresenter();
		facade.initiateGame();
	}
}
