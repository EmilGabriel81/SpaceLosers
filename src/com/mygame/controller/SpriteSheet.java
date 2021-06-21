package com.mygame.controller;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	/**
	 *  Cuts the sprites from the spritesheet to certain subimages
	 * 
	 */
	public BufferedImage spriteSheet;
	
	public SpriteSheet() {
		//this.spriteSheet = ss;
	}
	
	public BufferedImage grabSprite(int x, int y, int width, int height, BufferedImage spriteSheet) {
		
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		
		return sprite;
		
	}
}
