package com.mygame.model;

import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Sprite {

	/**
	 *  The parent class
	 */
	
	private BufferedImage image;
	private boolean dead;
	protected int x;
	protected int y;
	protected int xDir;
	protected int yDir;
	
	public abstract void move();
	
	public Sprite() {
		this.dead = false;
		
	}
	
	public void kill() {
		// the sprite dies
		this.dead = true;
	}

	public BufferedImage  getImage() {
		return this.image;
	}

	public void setImage(BufferedImage  image) {
		this.image = image;
	}

	public boolean isDead() {
		return this.dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

//	public int getxDir() {
//		return xDir;
//	}
//
//	public void setxDir(int xDir) {
//		this.xDir = xDir;
//	}
	
}
