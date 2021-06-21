package com.mygame.model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;

import com.mygame.constants.Constants;
import com.mygame.controller.IAnimator;

public class Player extends Sprite implements IAnimator{

	private BufferedImage[] sprites;
	private BufferedImage[] spritesArray;
	private boolean animeRunning = true;
	private int index1 = 0;
	private int speed1 = 0;
	private int index2 = 0;
	private int speed2 = 0;

	public Player() {
		init();
	}

	private void init() {
		int xStart = Constants.BORDERPADDING;
		int yStart = Constants.BOARD_HEIGHT / 2 - Constants.PLAYER_HEIGHT2 / 2;
		setX(xStart);
		setY(yStart);
	}

	@Override
	public void move() {

		y += yDir;
		x += xDir;
		// add constraints
		if (this.getX() < Constants.BORDERPADDING)
			this.x = Constants.BORDERPADDING;// a padding of 50
		if (this.getY() < 0)
			this.y = 0;
		if (this.getX() >= Constants.BOARD_WIDTH - (Constants.BORDERPADDING + Constants.PLAYER_WIDTH2))
			this.x = Constants.BOARD_WIDTH - (Constants.BORDERPADDING + Constants.PLAYER_WIDTH2);// a padding of 50
		if (this.getY() >= Constants.BOARD_HEIGHT - Constants.PLAYER_HEIGHT2)
			this.y = Constants.BOARD_HEIGHT - Constants.PLAYER_HEIGHT2;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP)
			yDir = -4;
		if (key == KeyEvent.VK_DOWN)
			yDir = 4;
		if (key == KeyEvent.VK_LEFT)
			xDir = -3;
		if (key == KeyEvent.VK_RIGHT)
			xDir = 3;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP)
			yDir = 0;
		if (key == KeyEvent.VK_DOWN)
			yDir = 0;
		if (key == KeyEvent.VK_LEFT)
			xDir = 0;
		if (key == KeyEvent.VK_RIGHT)
			xDir = 0;
	}

	// -------------------------- New Changes--------------------------

	@Override
	public void setAnimation(List<BufferedImage> animationList) {
		sprites = new BufferedImage[animationList.size()];
		for (int i = 0; i < animationList.size(); i++) {
			sprites[i] = animationList.get(i);
		}
	}

	public void setSprites(List<BufferedImage> animationList) {
		spritesArray = new BufferedImage[animationList.size()];
		for (int i = 0; i < animationList.size(); i++) {
			spritesArray[i] = animationList.get(i);
		}
	}
	
	@Override
	public void paintAnimation(Graphics g) {
		if(this.animeRunning) {
			g.drawImage(this.sprites[index2], this.getX(), this.getY(), null);
		}
		
		//System.out.println(index2);
	}

	
	public void paintEntity(Graphics g) {
		if (speed1 < 8) {
			speed1++;
		} else {
			speed1 = 0;
			if (index1 == 0) {
				index1 = 1;
			} else {
				index1 = 0;
			}
		}
		g.drawImage(spritesArray[index1], this.getX(), this.getY(), null);
	}
	
	@Override
	public void runAnimation() {
		if(animeRunning) {
			if (speed2 < 5) {
				speed2++;
			} else {
				speed2 = 0;
				if (index2 < sprites.length - 1) {
					index2++;
				} else {
					index2 = 0;
					stopAnimation();	
				}
			}
		}		
	}
	
	@Override
	public void stopAnimation() {
		animeRunning = false;
		this.speed2 = 0;
		this.index2 = 0;
	}

}
