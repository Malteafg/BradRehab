package com.simaflux.rehab.states;

import java.awt.Graphics2D;

import com.simaflux.rehab.Player;
import com.simaflux.rehab.challenges.Challenge;
import com.simaflux.rehab.challenges.PythagorasJump;
import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;

public class PlayState extends State {
	
	private Player player;
	
	private boolean moving;
	private float movedDist;
	private final float moveSpeed = 10f;
	
	private Challenge challenge;
	
	private String answer;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		player = new Player();
		
		moving = true;
		
		movedDist = 0;
		
		createChallenge();
	}
	
	private void createChallenge() {
		challenge = new PythagorasJump();
		answer = "";
	}

	@Override
	public void update() {
		if(moving) {
			movedDist += moveSpeed;
			
			if(challenge.updatePosition(moveSpeed)) createChallenge();
			
			if(challenge.getPos().x + challenge.getSize().x / 2 < Vars.WIDTH / 2 && !challenge.hasAnswered()) {
				moving = false;
			}
		}
		
		player.update();
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("bg").render(g, (int) ((-movedDist / 3) % Vars.WIDTH), 0);
		Loader.getTexture("bg").render(g, (int) ((-movedDist / 3) % Vars.WIDTH) + Vars.WIDTH, 0);
		Loader.getTexture("plank").render(g, (int) (-movedDist % Vars.WIDTH), Vars.PLAYER_HEIGHT);
		Loader.getTexture("plank").render(g, (int) (-movedDist % Vars.WIDTH) + Vars.WIDTH, Vars.PLAYER_HEIGHT);
		
		challenge.render(g);
		
		player.render(g);
	}

	@Override
	public void keyPressed(int k) {
		if(!moving && !challenge.hasAnswered()) {
			if(k == Vars._0) answer.concat("0");
			if(k == Vars._1) answer.concat("1");
			if(k == Vars._2) answer.concat("2");
			if(k == Vars._3) answer.concat("3");
			if(k == Vars._4) answer.concat("4");
			if(k == Vars._5) answer.concat("5");
			if(k == Vars._6) answer.concat("6");
			if(k == Vars._7) answer.concat("7");
			if(k == Vars._8) answer.concat("8");
			if(k == Vars._9) answer.concat("9");
			if(k == Vars.COMMA) answer.concat(".");
			if(k == Vars.DOT) answer.concat(".");
			
			if(k == Vars.SPACE) {
				moving = true;
				boolean c = challenge.answer(answer.equals("") ? -1 : Double.parseDouble(answer));
				if(c) {
					player.jump(challenge.getSize().y, c);
				}
			}
		}
	}

}
