package com.simaflux.rehab.challenges;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vars;
import com.simaflux.rehab.utils.Vector2f;

public class PythagorasJump extends Challenge {
	
	private final int[][] questions = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {20, 21, 29}, {7, 24, 25}, {9, 40, 41}};
	private final int answer, question;
	
	public PythagorasJump() {
		size = new Vector2f((float) (Math.random() * 400 + 100), (float) (Math.random() * 400 + 100));
		pos = new Vector2f(Vars.WIDTH + 200, Vars.PLAYER_HEIGHT - size.y);
		answer = (int) (Math.random() * 2);
		question = (int) (Math.random() * 6);
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) (pos.x + size.x), (int) (pos.y), 50, (int) (size.y));
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(5));
		g.drawLine((int) (pos.x), (int) (pos.y + size.y), (int) (pos.x + size.x), (int) (pos.y));
		g.drawLine((int) (pos.x), (int) (pos.y + size.y), (int) (pos.x + size.x), (int) (pos.y + size.y));
		g.drawLine((int) (pos.x + size.x), (int) (pos.y), (int) (pos.x + size.x), (int) (pos.y + size.y));
		
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.drawString(Integer.toString(questions[question][2]), (int) (pos.x + size.x / 2 - 30), (int) (pos.y + size.y / 2 - 30));
		g.drawString(Integer.toString(questions[question][answer * -1 + 1]), (int) (pos.x + size.x / 2 - 10), (int) (pos.y + size.y + 30));
		g.drawString("?", (int) (pos.x + size.x + 20), (int) (pos.y + size.y / 2));
	}

	@Override
	public boolean answerCase(double a) {
		if((int) (a) == questions[question][answer]) return false;		
		return true;
	}	

}
