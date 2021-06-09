package com.mygame.startapp;

import java.awt.EventQueue;



public class StartApp {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new GameMainWindow();
			}
		});
	}

}
