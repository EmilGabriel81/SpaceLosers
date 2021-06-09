package com.mygame.startapp;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.mygame.constants.Constants;
import com.mygame.game.GameMainPanel;



public class GameMainWindow extends JFrame{

	/**
	 *  Just the main window
	 */
	
	public GameMainWindow(){
	
		 initGui();
	}

	private void initGui() {
		
		add(new GameMainPanel());
		setTitle(Constants.TITLE);
		pack();//JFrame will be as large as the JPanel(GameMainPanel)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		//setSize(ConstantVariables.BOARD_WIDTH, ConstantVariables.BOARD_HEIGHT);
		
	}

	
	
}
