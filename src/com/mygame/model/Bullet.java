package com.mygame.model;


import com.mygame.constants.Constants;

public class Bullet extends Sprite {
	
	private int bulletSpeed = 5;

	public Bullet() {
		this.kill();
	}

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		init();

	}

	private void init() {
		setX(x + Constants.PLAYER_WIDTH);
		setY(y + Constants.PLAYER_HEIGHT2 / 2);

	}

	@Override
	public void move() {

		this.x += bulletSpeed;
		
		if (this.x > Constants.BOARD_WIDTH)
			this.kill();
	}
	

	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
	
	

}
