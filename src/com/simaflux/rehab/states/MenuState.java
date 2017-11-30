package com.simaflux.rehab.states;

import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vars;

public class MenuState extends State {

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void keyPressed(int k) {
		if(k == Vars.SPACE) gsm.setState(new PlayState(gsm));
	}

}
