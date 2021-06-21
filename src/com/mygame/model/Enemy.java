package com.mygame.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.mygame.controller.IAnimator;

public class Enemy extends Sprite implements IAnimator{

	private boolean visible = true;
	
	// ----------------- Changes ----------------
	private BufferedImage[] sprites;
	private int speed = 0;
	private int index = 0;
	private boolean animeRunning = true;
	//----------------- Changes ----------------

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
			this.x += -7;
			
		}
		 this.x += dir;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * --------------------------------------------New Changes -------------------------------------------------------
	 * 
	 * @param g
	 */
	
	@Override
	public void setAnimation(List<BufferedImage> animationList) {
		sprites = new BufferedImage[animationList.size()];
		for (int i = 0; i < animationList.size(); i++) {
			sprites[i] = animationList.get(i);
		}
	}
	
	@Override
	public void paintAnimation(Graphics g) {
		if(this.animeRunning) {
			g.drawImage(this.sprites[index], this.getX(), this.getY(), null);
		}
		
		//System.out.println(index);
	}

	@Override
	public void runAnimation() {
		if(animeRunning) {
			if (speed < 5) {
				speed++;
			} else {
				speed = 0;
				if (index < sprites.length - 1) {
					index++;
				} else {
					index = 0;
					stopAnimation();	
				}
			}
		}		
	}
	
	@Override
	public void stopAnimation() {
		animeRunning = false;
		this.speed = 0;
		this.index = 0;
	}

}
