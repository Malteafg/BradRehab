package com.simaflux.rehab.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.Player;
import com.simaflux.rehab.Splatter;
import com.simaflux.rehab.challenges.Challenge;
import com.simaflux.rehab.challenges.PythagorasJump;
import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;

public class PlayState extends State {
	
	private Player player;
	
	private boolean moving;
	private float movedDist;
	
	private Challenge challenge;
	
	private String answer;
	
	private Splatter splatter;

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
			movedDist += Vars.moveSpeed;
			
			if(challenge.updatePosition(Vars.moveSpeed)) createChallenge();
			
			if(challenge.getPos().x + challenge.getSize().x / 2 < Vars.WIDTH / 2 && !challenge.hasAnswered()) {
				moving = false;
			}
		}
		
		if(player.bloodTime()) {
			if(moving) splatter = new Splatter(player.getPos());
			moving = false;
			splatter.update();
		} else {
			player.update();
		}
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("bg").render(g, (int) ((-movedDist / 3) % Vars.WIDTH), 0);
		Loader.getTexture("bg").render(g, (int) ((-movedDist / 3) % Vars.WIDTH) + Vars.WIDTH, 0);
		Loader.getTexture("plank").render(g, (int) (-movedDist % Vars.WIDTH), Vars.PLAYER_HEIGHT);
		Loader.getTexture("plank").render(g, (int) (-movedDist % Vars.WIDTH) + Vars.WIDTH, Vars.PLAYER_HEIGHT);
		
		challenge.render(g);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.drawString(answer, 1300, 300);
		
		if(player.bloodTime() && splatter != null) {
			splatter.render(g);
			Loader.getTexture("user").render(g, (int) (player.getPos().x), (int) (player.getPos().y - 80), Math.PI / 2);
		} else player.render(g);
	}

	@Override
	public void keyPressed(int k) {
		if(!moving && !challenge.hasAnswered()) {
			if(k == Vars._0) {
				answer += "0";
				System.out.println(answer);
			}
			if(k == Vars._1) answer += "1";
			if(k == Vars._2) answer += "2";
			if(k == Vars._3) answer += "3";
			if(k == Vars._4) answer += "4";
			if(k == Vars._5) answer += "5";
			if(k == Vars._6) answer += "6";
			if(k == Vars._7) answer += "7";
			if(k == Vars._8) answer += "8";
			if(k == Vars._9) answer += "9";
			if(k == Vars.COMMA) answer += ".";
			if(k == Vars.DOT) answer += ".";
			
			if(k == Vars.SPACE) {
				moving = true;
				boolean c = challenge.answer(answer.equals("") ? -1 : Double.parseDouble(answer));
				player.jump(challenge.getPos().x + challenge.getSize().x - player.getPos().x, challenge.getSize().y, c);
			}
		}
	}

}
