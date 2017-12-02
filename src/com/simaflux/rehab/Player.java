package com.simaflux.rehab;

import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class Player {
	
	private Vector2f pos;
	private boolean dead, jumping, rotating;
	private float verticalSpeed, horizontalSpeed;
	
	private double angle;
	
	private boolean bloodTime;
	
	public Player() {
		pos = new Vector2f(500, Vars.PLAYER_HEIGHT);
		
		angle = 0;
		rotating = false;
		bloodTime = false;
	}
	
	public void update() {
		
		if(jumping) {
			pos = new Vector2f(pos.x, pos.y + verticalSpeed);
//			verticalSpeed += rotating ? 0.25 : 0.5;
			verticalSpeed += Vars.GRAVITY;
			
			if(verticalSpeed > 0 && dead) rotating = true;
			
			if(pos.y > Vars.PLAYER_HEIGHT) {
				jumping = false;
				pos.y = Vars.PLAYER_HEIGHT;
			}
		}
		
		if(rotating) {
			angle += 0.2;
			
			if(pos.y == Vars.PLAYER_HEIGHT) {
				bloodTime = true;
			}
		}
		
	}
	
	public void jump(float length, float height, boolean dead) {
		this.dead = dead;
		jumping = true;
		
		verticalSpeed = (float) - Math.sqrt(height * 2.0 * Vars.GRAVITY);
		horizontalSpeed = (float) - ((length * Vars.GRAVITY) / verticalSpeed);
		
	}
	
	public void render(Graphics2D g) {
		Loader.getTexture("user").render(g, (int) (pos.x), (int) (pos.y - Loader.getTexture("user").getHeight()), rotating ? angle : 0.0);
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public boolean bloodTime() {
		return bloodTime;
	}

	public float getHorizontalSpeed() {
		return horizontalSpeed;
	}

}
