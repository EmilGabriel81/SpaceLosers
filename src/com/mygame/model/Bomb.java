package com.mygame.model;

import javax.swing.ImageIcon;



public class Bomb extends Sprite{

	public Bomb() {
		
	}
	
	public Bomb(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		this.x -= 3;
		if(this.getX() < 0) kill();

	}

}
