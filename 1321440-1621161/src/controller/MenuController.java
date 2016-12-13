package controller;

//import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuController implements ActionListener {

    private String newline = "\n";
    
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem saveItem;
    private JMenuItem reloadItem;
    
    private JFrame parentFrame;
 
    private static MenuController menuController = null;
    
    /**
	 * Initializes the menuController
	 */
	private MenuController() {
	}
	
	/**
	 * If not already initialized: initializes the singleton
	 * as an single instance and returns it
	 * If initialized only returns the instance
	 * @return the instanced Menu Controller
	 */
	public static MenuController getMenuController() {
		if(menuController == null) {
			menuController = new MenuController();
		}
		return menuController;
	}
    
    public JMenuBar createMenuBar() {
        
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("Arquivo");
        menu.getAccessibleContext().setAccessibleDescription("Um menu que lida com salvamento e recarga de jogo.");
        menuBar.add(menu);
 
        saveItem = new JMenuItem("Salvar");
        saveItem.getAccessibleContext().setAccessibleDescription("Abre diálogo que salva o jogo.");
        saveItem.addActionListener(this);
        saveItem.setEnabled(false);
        menu.add(saveItem);
 
        reloadItem = new JMenuItem("Abrir");
        reloadItem.addActionListener(this);
        reloadItem.getAccessibleContext().setAccessibleDescription("Abre diálogo para abrir jogo já salvo.");
        menu.add(reloadItem);
 
        return menuBar;
    }
 
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                   + newline
                   + "    Event source: " + source.getText();
        System.out.println(s);
        
        if(source.getText() == "Salvar") {
        	onPressingSave();
        }
        else if (source.getText() == "Abrir") {
        	onPressingOpen();
        }
    }

    private void onPressingSave() {
    	GameController gameManager = GameController.getMainGameManager();
    	gameManager.saveGame();
    }
    
    private void onPressingOpen() {
    	GameController gameManager = GameController.getMainGameManager();
    	Boolean reloadStatus = gameManager.reloadGame();
    	
    	if(reloadStatus == true) {
    		GamePresenter gamePresenter = GamePresenter.getMainGamePresenter();
    		gamePresenter.showOngoingBattle();
    	}
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI(JFrame parentFrame) {
    	this.parentFrame = parentFrame;
        parentFrame.setJMenuBar(menuController.createMenuBar());
    }
    
    public void enableSaving(Boolean b) {
    	parentFrame.getJMenuBar().getMenu(0).getItem(0).setEnabled(b);
    }
    
    public void enableReloading(Boolean b) {
    	parentFrame.getJMenuBar().getMenu(0).getItem(1).setEnabled(b);
    }
}
