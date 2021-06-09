package com.mygame.model;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.mygame.constants.Constants;

public class Player extends Sprite {

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

}
