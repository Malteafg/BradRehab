package com.simaflux.rehab;

import java.awt.Color;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class Player {
	
	private Vector2f pos, size;
	private boolean dead, jumping;
	private float verticalSpeed;
	
	public Player() {
		size = new Vector2f(50, 50);
		pos = new Vector2f(500, Vars.PLAYER_HEIGHT - 25);
	}
	
	public void update() {
		
		if(jumping) {
			pos = new Vector2f(pos.x, pos.y + verticalSpeed);
			verticalSpeed -= 0.1;
			
			jumping = pos.y < Vars.PLAYER_HEIGHT;
		}
		
	}
	
	public void jump(float length, float height, boolean dead) {
		this.dead = dead;
		jumping = true;
		verticalSpeed = height / length * 2;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) (pos.x - size.x / 2), (int) (pos.y - size.y / 2), (int) (size.x), (int) (size.y));
	}

}
