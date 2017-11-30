package com.simaflux.rehab.states;

import java.awt.Graphics2D;

public abstract class State {

	protected GameStateManager gsm;
	
	protected State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	
	public abstract void keyPressed(int k);
	
}
