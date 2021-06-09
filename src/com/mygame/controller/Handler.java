package com.mygame.controller;

import java.awt.image.BufferedImage;

public class Handler {
/**
 *  More like a helper class
 * 
 * @param constant
 * @param wave
 * @return
 */
	
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
