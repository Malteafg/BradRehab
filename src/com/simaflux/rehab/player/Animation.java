package com.simaflux.rehab.player;

import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Texture;
import com.simaflux.rehab.utils.Vector2f;

public class Animation {
	
	private Texture[] images;
	
	private int time, speed, count, length;
	
	private String path;
	
	public Animation(String path, int length, int width, int speed) {
		this.path = path;
		
		this.speed = speed;
		this.length = length;
		
		time = 0;
		count = 0;
		
		images = new Texture[length];
		
		if(Loader.getTexture(path).getImage() == null) System.out.println("NULL");
		for(int i = 0; i < length; i++) {
			images[i] = new Texture(Loader.getTexture(path).getImage().getSubimage(i * width, 0, width, Loader.getTexture(path).getHeight()));
		}
	}
	
	public void update() {
		time++;
		
		if(time == speed) {
			time = 0;
			count++;
		}
	}
	
	public void render(Graphics2D g, Vector2f pos) {
		images[count % length].render(g, pos.x, pos.y - Loader.getTexture(path).getHeight());
	}

}
