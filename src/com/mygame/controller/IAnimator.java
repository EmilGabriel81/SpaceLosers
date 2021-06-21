package com.mygame.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public interface IAnimator {
	
	void setAnimation(List<BufferedImage> animationList);
	void paintAnimation(Graphics g);
	void runAnimation();
	void stopAnimation();

}
