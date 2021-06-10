package com.mygame.model;

public class Enemy extends Sprite{

	private boolean visible = true;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		init();
	}

	private void init() {
	
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void move(int dir) {
		if(this.getX() > 1100) {
			this.x += -10;
		}
		 this.x += dir;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}
	
	public void getSpeed() {
		
	}
	
	public void setSpeed(int speed) {
		
	}

}
