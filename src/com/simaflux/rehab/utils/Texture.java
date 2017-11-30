package com.simaflux.rehab.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	private BufferedImage image;
	
	private int width, height;
	
	public Texture(String path) {
		try {
			image = ImageIO.read(Class.class.getResourceAsStream(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public void setColor(Color color) {
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void render(Graphics2D g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
	
	public void render(Graphics2D g, int x, int y, int width, int height) {
		g.drawImage(image, x, y, width, height, null);
	}

	public void render(Graphics2D g, int x, int y, double angle) {
		AffineTransform a = new AffineTransform();
        a.translate(x + width / 2.0, y + height / 2.0);
        a.rotate(angle);
        a.translate(- width / 2.0, - height / 2.0);
        a.scale(1, 1);
		g.drawImage(image, a, null);
	}
	
}
