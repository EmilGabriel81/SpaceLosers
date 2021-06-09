package com.mygame.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mygame.game.GameMainPanel;

public class GameCore implements ActionListener{
	
	/**
	 *  Keeping the action listener in a separate class to avoid boiler plate code
	 */

	private GameMainPanel gamePanel;
	
	 public GameCore(GameMainPanel gamePanel) {
		
		this.gamePanel = gamePanel;
	}
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.gamePanel.render();
	}

}
