package com.mygame.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.mygame.controller.IAnimator;

public class WormHole extends Sprite implements IAnimator {
	
	private BufferedImage[] sprites;
	private int speed = 0;
	private int index = 0;
	private boolean animeRunning = true;
	
	public WormHole(int initX, int initY) {
		this.x = initX;
		this.y = initY;
		
	}

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
			if(index != 0) {
				g.drawImage(this.sprites[index], this.getX(), this.getY(), 130, 130, null);	
			}
			
		}	
	}

	@Override
	public void runAnimation() {
		if(animeRunning) {
				speed = 0;
				if (index < sprites.length - 1) {
					index++;
				} else {
					index = 0;
					stopAnimation();	
				}
		}			
	}

	@Override
	public void stopAnimation() {
		animeRunning = false;
		this.speed = 0;
		this.index = 0;
	}
	
	public void startAnimation() {
		animeRunning = true;
		this.index = 0;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
