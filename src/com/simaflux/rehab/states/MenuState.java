package com.simaflux.rehab.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;

public class MenuState extends State {

	private int selectedTopic = 0;
	public static String[] topics = {"Pythagoras", "Trigonometry", "Derive"};
	public static boolean[] unlocked = {true, false, false};
	public static int unlockedNum = 1;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("bg").render(g, 0, 0);
		
		g.setFont(new Font("Serif", Font.BOLD, 60));
		g.setColor(Color.RED);
		g.drawString("Pick a topic:", 200, 180);
		for(int i = 0; i < topics.length; i++) {
			if(unlocked[i]) g.setColor(Color.RED);
			else g.setColor(Color.DARK_GRAY);
			g.drawString(topics[i], 200, 270 + i * 70);
			if(!unlocked[i]) Loader.getTexture("lock").render(g, 130, 210 + i * 70);
			if(i == selectedTopic) g.fillOval(150, 230 + i * 70, 40, 40);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == Vars.SPACE) gsm.setState(new PlayState(gsm, selectedTopic));
		if(k == Vars.UP) {
			selectedTopic--;
			if(selectedTopic < 0) selectedTopic = unlockedNum - 1;
		}
		if(k == Vars.DOWN) {
			selectedTopic++;
			if(selectedTopic > unlockedNum - 1) selectedTopic = 0;
		}
	}

}
