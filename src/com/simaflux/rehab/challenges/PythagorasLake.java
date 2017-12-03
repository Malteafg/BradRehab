package com.simaflux.rehab.challenges;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class PythagorasLake extends Challenge {
	
	public final float midlength;
	
	public PythagorasLake() {
		super("PythagorasLake");
		answer = 2;
		question = (int) (Math.random() * 6);

		midlength = 150;
		size = new Vector2f(600, 250);
		pos = new Vector2f(Vars.WIDTH + 200, Vars.PLAYER_HEIGHT);
		
		q = "How long is the lake?";
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("hole").render(g, pos.x, pos.y, midlength, size.y);
		Loader.getTexture("hole").render(g, pos.x + size.x, pos.y, (size.x - midlength) * -1.0f, size.y);
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(5));
		g.drawLine((int) (pos.x), (int) (pos.y), (int) (pos.x + size.x), (int) (pos.y));
		g.drawLine((int) (pos.x), (int) (pos.y), (int) (pos.x + midlength), (int) (pos.y + size.y - 55));
		g.drawLine((int) (pos.x + midlength), (int) (pos.y + size.y - 55), (int) (pos.x + size.x), (int) (pos.y));
		
		g.drawLine((int) (pos.x + midlength - 35), (int) (pos.y + size.y - 75), (int) (pos.x + midlength + 20), (int) (pos.y + size.y - 100));
		g.drawLine((int) (pos.x + midlength - 20), (int) (pos.y + size.y - 80),(int) (pos.x + midlength + 20), (int) (pos.y + size.y - 100));
		
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.drawString(Integer.toString(questions[question][0]), (int) (pos.x + midlength / 2 - 20), (int) (pos.y + size.y / 2));
		g.drawString(Integer.toString(questions[question][1]), (int) (pos.x + size.x / 2 + midlength / 2 - 30), (int) (pos.y + size.y / 2 + 20));
		g.drawString("?", (int) (pos.x + size.x / 2), (int) (pos.y - 10));
	}

	@Override
	public boolean answerCase(double a) {
		if((int) (a) == questions[question][answer]) return false;		
		return true;
	}

}
