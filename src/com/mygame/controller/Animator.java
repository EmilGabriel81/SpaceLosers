package com.mygame.controller;

import java.awt.image.BufferedImage;
import java.util.*;

public class Animator {
/**
 *  The class in charged with animating the sprites from the sprite sheet
 */
	private List<BufferedImage> frames;
	private BufferedImage sprite;
	private volatile boolean running = false;
	private long beforeTime, previousTime, speed;
	private int frameAtPause, currentFrame;
	
	//Changes
	private volatile boolean alive;

	public Animator(List<BufferedImage> frames) {
		this.frames = frames;
	}

	public void setSpeed(long speed) {
		this.speed = speed;

	}

	public void update(long time) {
		if (running) {
			if (time - previousTime >= speed) {
				currentFrame++;
				try {
					sprite = frames.get(currentFrame);
					//System.out.println("current frame"+currentFrame);
				} catch (IndexOutOfBoundsException e) {
					currentFrame = 0;
						this.stop();
					//sprite = frames.get(currentFrame);
					
				}
				previousTime = time;
			} // first if
		} // second if
	}// update

	public void play() {
		running = true;
		alive = true;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}

	public void stop() {
		running = false;
		alive = false;
		previousTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	
	public void pause() {
		frameAtPause = currentFrame;
		running = false;
	}
	
	public void resume() {
		currentFrame = frameAtPause;
		running = true;
		
	}

	public BufferedImage getSprite() {
		return sprite;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
