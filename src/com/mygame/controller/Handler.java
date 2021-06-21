package com.mygame.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Handler {
/**
 *  More like a helper class
 * 
 * @param constant
 * @param wave
 * @return
 */
	
	
	//----------------------- Changes -------------------------------------------------
	
	private BufferedImageLoader loader;
	private SpriteSheet ss;
	
	
	public Handler() {
		loader = new BufferedImageLoader();
		ss = new SpriteSheet();
	}
	
	public BufferedImage fetchSprite(String path) {
		BufferedImage image = null;	
		try {
			image = loader.loadImage(path);// Don`t forget to change the path
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;	
	}

	public List<BufferedImage> initSprites(int row, int col,int width, int height, BufferedImage spriteSheet) {
		List<BufferedImage> sprites = new ArrayList<BufferedImage>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sprites.add(ss.grabSprite(width * j, height * i, width, height, spriteSheet));
			}
		}
		return sprites;
	}
	
	//  ------------------------------ Changes -----------------------------------------------------
	
	public static int adjustValues(String constant, int wave){
		// some values can be adjusted from here: bullet speed, enemy speed ..etc
		int var = 0;		
		switch (constant) {
		case "bullet":
			if(wave >= 1 && wave < 4) {
				return 7;
			}else if (wave >= 4 && wave < 6) {
				return 10;
			}else if (wave >= 6 && wave < 8) {
				return 15;
			}else if (wave >= 8) {
				return 30;
			}
			break;
		case "enemy":
			if(wave >= 1 && wave < 6) {
				return -1;
			}else if (wave >= 6 && wave < 8) {
				return -3;
			}else if (wave >= 8 && wave < 10) {
				return -4;
			}else if (wave >= 10) {
				return -5;
			}
			break;
		default:
			break;
		}
		return var;
	}
	
	public static BufferedImage reloadSprite(int wave) {
		
		BufferedImage sprite = null;
		return sprite;
	}
}
