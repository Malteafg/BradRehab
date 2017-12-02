package com.simaflux.rehab.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.simaflux.rehab.challenges.Challenge;
import com.simaflux.rehab.challenges.PythagorasJump;
import com.simaflux.rehab.player.Player;
import com.simaflux.rehab.player.Splatter;
import com.simaflux.rehab.utils.Loader;
import com.simaflux.rehab.utils.Vars;

public class PlayState extends State {
	
	private Player player;
	
	private boolean moving, jumping;
	private float movedDist, horizontalSpeed;
	
	private Challenge challenge;
	
	private String answer;
	
	private Splatter splatter;
	
	private int time;
	private final int selectedTime;
	
	private int flashTimer;

	public PlayState(GameStateManager gsm, int selectedTime) {
		super(gsm);
		
		time = 0;
		this.selectedTime = selectedTime;
		
		player = new Player();
		
		moving = true;
		jumping = false;
		
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
			player.update(moving);
		}
		
		if(jumping) {
			movedDist += horizontalSpeed;
			
			challenge.updatePosition(horizontalSpeed);
			
			if(player.getPos().y == Vars.PLAYER_HEIGHT) {
				jumping = false;
				moving = true;
			}
		}
		
		flashTimer++;
	}

	@Override
	public void render(Graphics2D g) {
		Loader.getTexture("bg").render(g, (-movedDist / 3) % Vars.WIDTH, 0);
		Loader.getTexture("bg").render(g, (-movedDist / 3) % Vars.WIDTH + Vars.WIDTH, 0);
		Loader.getTexture("plank").render(g, -movedDist % Vars.WIDTH, Vars.PLAYER_HEIGHT);
		Loader.getTexture("plank").render(g, -movedDist % Vars.WIDTH + Vars.WIDTH, Vars.PLAYER_HEIGHT);
		
		challenge.render(g);
		
		if(player.bloodTime() && splatter != null) {
			splatter.render(g);  
			Loader.getTexture("user").render(g, player.getPos().x, player.getPos().y - 80, Math.PI / 2);
		} else player.render(g, moving);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Serif", Font.BOLD, 60));
		int answerwidth = g.getFontMetrics().stringWidth(answer);
		if(!moving && !challenge.hasAnswered()) {
			int width = g.getFontMetrics().stringWidth(challenge.getQuestion());
			g.drawString(challenge.getQuestion(), Vars.WIDTH / 2 - width / 2, Vars.PLAYER_HEIGHT + 200);
			g.fillRect(Vars.WIDTH / 2 - 75, Vars.PLAYER_HEIGHT + 270, 150, 70);
			g.setColor(Color.BLACK);
			int t = 60;
			if(flashTimer % t > 0 && flashTimer % t < t / 2) g.fillRect(Vars.WIDTH / 2 + answerwidth / 2, Vars.PLAYER_HEIGHT + 280, 5, 50);
			g.setFont(new Font("Serif", Font.BOLD, 50));
			g.drawString(answer, Vars.WIDTH / 2 - answerwidth / 2, Vars.PLAYER_HEIGHT + 320);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(!moving && !challenge.hasAnswered()) {
			if(k == Vars._0) answer += "0";
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
			if(k == Vars.BACK) if(answer.length() > 0) answer = answer.substring(0, answer.length() - 1);
			
			if(k == Vars.SPACE) {
				jumping = true;
				boolean c = challenge.answer(answer.equals("") ? -1 : Double.parseDouble(answer));
				
				switch(challenge.getName()) {
				case "PythagorasJump":
					player.jump(challenge.getPos().x + challenge.getSize().x - player.getPos().x, 
							(c ? challenge.getSize().y * 0.9f : challenge.getSize().y + Loader.getTexture("user").getHeight() / 2), 
							c);
					horizontalSpeed = player.getHorizontalSpeed();
					break;
				case "PythagorasLake":
					player.jump(challenge.getPos().x + challenge.getSize().x - player.getPos().x, 150, c);
					horizontalSpeed = player.getHorizontalSpeed();
					break;
				}
			}
		}
	}

}
