package com.simaflux.rehab.states;

import java.awt.Graphics2D;

import com.simaflux.rehab.Player;

public class PlayState extends State {
	
	private Player player;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		player = new Player();
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render(Graphics2D g) {
		player.render(g);
	}

	@Override
	public void keyPressed(int k) {
		
	}

}
