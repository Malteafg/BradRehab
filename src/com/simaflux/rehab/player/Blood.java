package com.simaflux.rehab.player;

import java.awt.Color;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class Blood {

	private Vector2f pos, size, direction;
	private static final float GRAVITY = 0.1f;
	private boolean landed;
	
	public Blood(Vector2f pos) {
		this.pos = pos;
		this.direction = new Vector2f((float) (1.0 - Math.random() * 2.0), (float) -Math.random());
		this.direction = direction.normalize().scale((float) Math.random() * 5 + 2);
		this.size = new Vector2f((float) (5 * Math.random() + 5), (float) (5 * Math.random() + 5));
		this.landed = false;
	}
	
	public void update() {
		if(!landed) {
			pos = pos.add(direction);
			direction = new Vector2f(direction.x, direction.y + GRAVITY);
			landed = pos.y > Vars.PLAYER_HEIGHT;
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval((int) (pos.x - size.x / 2.0), (int) (pos.y - size.y / 2.0), (int) size.x, (int) (size.x * (landed ? 0.5 : 1)));
	}
	
}
