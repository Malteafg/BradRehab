package com.simaflux.rehab.player;

import java.awt.Graphics2D;
import java.text.DecimalFormat;

import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Maths;
import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class Player {
	
	private Vector2f pos;
	private boolean dead, jumping, rotating;
	private float startSpeed, time, topTime;
	
	private double angle;
	
	private boolean bloodTime, top;
	
	private Animation run1;
	
	public Player() {
		pos = new Vector2f(500, Vars.PLAYER_HEIGHT);
		
		angle = 0;
		rotating = false;
		bloodTime = false;
		
		run1 = new Animation("run1", 4, 128, 3);
	}
	
	public void update(boolean moving) {
		
		if(jumping) {
			if(!top) {
				time++;
			} else time--;

			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			
//			System.out.println( - (startSpeed * time) - (0.5f * Vars.GRAVITY * Math.pow(time, 2)));
//			System.out.println(Vars.PLAYER_HEIGHT - (startSpeed * time));
//			System.out.println(df.format(-0.5f * Vars.GRAVITY * Maths.pow(time, 2)));
			System.out.println(df.format(Vars.PLAYER_HEIGHT - (startSpeed * time) - (0.5f * Vars.GRAVITY * Maths.pow(time, 2))));
			pos = new Vector2f(pos.x, (Vars.PLAYER_HEIGHT - (startSpeed * time) - (0.5f * Vars.GRAVITY * Maths.pow(time, 2))));
			
			if(time > topTime) {
				top = true;
				if(dead) rotating = true;
			}
			
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
		
		if(moving) run1.update();
		
	}
	
	public void jump(float length, float height, boolean dead) {
		this.dead = dead;
		jumping = true;
		time = 0;
		topTime = length / Vars.moveSpeed;
		startSpeed = (float) ((height - (0.5f * -Vars.GRAVITY * Math.pow(topTime, 2))) / topTime);
		top = false;
	}
	
	public void render(Graphics2D g, boolean moving) {
		if(!moving) {
			Loader.getTexture("user").render(g, pos.x, pos.y - Loader.getTexture("user").getHeight(), rotating ? angle : 0.0);
		} else if(jumping) {
			Loader.getTexture("jump").render(g, pos.x, pos.y - Loader.getTexture("jump").getHeight());
		} else {
			run1.render(g, pos);
		}
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public boolean bloodTime() {
		return bloodTime;
	}

}
