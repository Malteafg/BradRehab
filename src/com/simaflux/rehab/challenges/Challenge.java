package com.simaflux.rehab.challenges;

import java.awt.Graphics2D;

import com.simaflux.rehab.utils.Vector2f;

public abstract class Challenge {
	
	protected final int[][] questions = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {20, 21, 29}, {7, 24, 25}, {9, 40, 41}};
	protected int answer, question;
	
	protected Vector2f pos, size;
	
	protected boolean answered;
	
	protected String q;
	
	protected final String name;
	
	public Challenge(String name) {
		this.name = name;
	}
	
	public boolean updatePosition(float speed) {
		pos.x -= speed;
		
		if(pos.x + size.x + 60 < 0) {
			return true;
		} else return false;
	}
	
	public boolean answer(double a) {
		answered = true;
		if(a == -1) return true;
		
		return answerCase(a);
	}
	
	public abstract boolean answerCase(double a);
	public abstract void render(Graphics2D g);
	
	public Vector2f getPos() {
		return pos;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public boolean hasAnswered() {
		return answered;
	}
	
	public String getQuestion() {
		return q;
	}
	
	public String getName() {
		return name;
	}

}
