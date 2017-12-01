package com.simaflux.rehab.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;

public class MenuState extends State {
	
	private int selectedTime;
	private final int[] times = {5, 10, 15, 20, 25, -1};

	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		selectedTime = 0;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("bg").render(g, 0, 0);
		
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.setColor(Color.RED);
		g.drawString("Time Limit", 200, 200);
		
		for(int i = 0; i < times.length; i++) {
			g.drawString(i == times.length - 1 ? "No Limit" : Integer.toString(times[i]), 200, 240 + i * 40);
			
			if(i == selectedTime) {
				g.fillOval(165, 215 + i * 40, 30, 30);
			}
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == Vars.SPACE) gsm.setState(new PlayState(gsm, selectedTime));
		if(k == Vars.UP) {
			selectedTime--;
			if(selectedTime < 0) selectedTime = times.length - 1;
		}
		if(k == Vars.DOWN) {
			selectedTime++;
			if(selectedTime > times.length - 1) selectedTime = 0;
		}
	}

}
