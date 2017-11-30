package com.simaflux.rehab;

import java.awt.Color;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class Player {
	
	private Vector2f pos, size;
	
	public Player() {
		size = new Vector2f(50, 50);
		pos = new Vector2f(200, Vars.PLAYER_HEIGHT - 50);
	}
	
	public void update() {
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) (pos.x - size.x / 2), (int) (pos.y - size.y / 2), (int) (size.x), (int) (size.y));
	}

}
