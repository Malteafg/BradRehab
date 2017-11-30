package com.simaflux.rehab.states;

import java.awt.Graphics2D;
import java.util.Stack;

public class GameStateManager {
	
	private Stack<State> states;
	
	public GameStateManager() {
		states = new Stack<State>();

		push(new MenuState(this));
	}
	
	public void update() {
		states.peek().update();
	}
	
	public void render(Graphics2D g) {
		states.peek().render(g);
	}

	public void setState(State s) {
		pop();
		push(s);
	}
	
	private void push(State s) {
		states.push(s);
	}
	
	private void pop() {
		states.pop();
	}
	
	public void keyPressed(int k) {
		states.peek().keyPressed(k);
	}
	
}
