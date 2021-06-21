package com.mygame.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;
import com.mygame.constants.Constants;
import com.mygame.controller.Animator;
import com.mygame.controller.BufferedImageLoader;
import com.mygame.controller.Handler;
import com.mygame.controller.SpriteSheet;
import com.mygame.listeners.GameCore;
import com.mygame.listeners.GameListener;
import com.mygame.model.Bomb;
import com.mygame.model.Bullet;
import com.mygame.model.Enemy;
import com.mygame.model.Player;
import com.mygame.model.WormHole;

public class GameMainPanel extends JPanel {

	private Timer timer;// javax.swing timer not java.util
	private boolean inGame = false;
	private Handler handler;
	// Images & Tools;
	//private BufferedImageLoader loader;
	private BufferedImage backGround;
	//private BufferedImage playerSprite;
	private BufferedImage playerFrame1;
	private BufferedImage playerFrame2;
	private BufferedImage bulletSprite;
	private BufferedImage enemySprite;
	private BufferedImage enemySprite3;
	private BufferedImage enemySprite4;
	private BufferedImage enemySprite5;

	private BufferedImage bombSprite;
	private BufferedImage lifeSprite;
	// InGame Objects;
	private Player player;
	private Bullet bullet;
	private List<Enemy> enemiesList;// the list of enemies
	private List<WormHole> wormholesList;// the list of enemies
	private List<Bomb> bombs;
	// constants for parallax BackGround
	private int relX;
	private int imgX = 0;
	// for enemy spawning
	private Random r;
	private int enemyCount = 3;
	private int enemyKilled = 0;
	// Game stats
	private String message = Constants.PRESSENTER;
	private int life = 3;
	private int wave = 1;
	private int score = 0;
	private int points = 0;

	// Animations---------------------------------------
	private BufferedImage explosionSpriteSheet;
	private BufferedImage wormholeSpriteSheet;
	//private SpriteSheet ss;
	private List<BufferedImage> explosionSpritesList;// the list with the explosion sprites
	private List<BufferedImage> wormholeSpritesList;// the list with the wormhole sprites
	private List<BufferedImage> playerSpritesList;// player frame1 and player frame2
	
	//private Animator animator;
	//private int explosionAtX = 0;
	//private int explosionAtY = 0;

	// -------------------------------------------------

	public GameMainPanel() {
		initVar();// variables;
		init();// general Layout;
	}

	private void initVar() {

		//this.loader = new BufferedImageLoader();
		this.player = new Player();
		this.bullet = new Bullet();
		this.enemiesList = new ArrayList<Enemy>();
		this.bombs = new ArrayList<Bomb>();
		this.handler = new Handler();
		this.wormholesList = new ArrayList<WormHole>();
		r = new Random();

		// ------------------ BufferedImages--------------------
		backGround = handler.fetchSprite(Constants.BACKGROUND_IMAGE);// the background
		//----------------------The Player----------------------
		
		//playerSprite = handler.fetchSprite(Constants.PLAYER_IMAGE2);// the player
		playerFrame1 = handler.fetchSprite(Constants.PLAYER_FRAME1);// the player sprite frame 1
		playerFrame2 = handler.fetchSprite(Constants.PLAYER_FRAME2);// the player sprite frame 2
		
		//---------------------the Bullet -------------------------
		bulletSprite = handler.fetchSprite(Constants.BULLET_IMAGE);// the bullet/laser
		enemySprite = handler.fetchSprite(Constants.ENEMY_IMAGE2);// first enemy
		enemySprite3 = handler.fetchSprite(Constants.ENEMY_IMAGE3);// second enemy
		enemySprite4 = handler.fetchSprite(Constants.ENEMY_IMAGE4);// third enemy
		enemySprite5 = handler.fetchSprite(Constants.ENEMY_IMAGE5);// fourth enemy
		bombSprite = handler.fetchSprite(Constants.BOMB_IMAGE);// the Bomb
		lifeSprite = handler.fetchSprite(Constants.LIFE_IMAGE);// the Heart
		explosionSpriteSheet = handler.fetchSprite(Constants.SPRITESHEET);// the explosion Sprite Sheet
		wormholeSpriteSheet = handler.fetchSprite(Constants.WORMHOLE);// the wormhole Sprite Sheet
		
		// ------------------------------------------------

		
		 playerSpritesList = new ArrayList<BufferedImage>();
		 playerSpritesList.add(playerFrame1);
		 playerSpritesList.add(playerFrame2);
		 player.setSprites(playerSpritesList);
		 
		 
		 
		// ss = new SpriteSheet(spriteSheet);
		 //explosionSpritesList = new ArrayList<BufferedImage>();
		 
		 explosionSpritesList = (ArrayList<BufferedImage>) handler.initSprites(4, 4, 64, 64, explosionSpriteSheet );// The explosion
		 wormholeSpritesList = (ArrayList<BufferedImage>) handler.initSprites(5, 5, 192, 192, wormholeSpriteSheet );// The wormholes
		 //initSprites(4, 4, 64, 64, explosionSpritesList);
			
		 player.setAnimation(explosionSpritesList);
		 
		//player.setImage(playerSprite);
		createEnemies(enemyCount);
		createWormHoles(enemyCount);

		// Dealing with Animations--------------------------
		
		
		//animator = new Animator(explosionSpritesList);
		//animator.setSpeed(100);
		// animator.play();// It will start the animation

		this.timer = new Timer(Constants.GAMESPEED, new GameCore(this));
		this.timer.start();

	}

	// prepare the sprites for the objects---------------
//	private BufferedImage fetchSprites(String path) {
//		BufferedImage image = null;
//		try {
//			image = loader.loadImage(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return image;
//	}

	// fill the ArrayList with enemies--------------------
	public void createEnemies(int enemyCount) {
		Enemy enemy;
		for (int i = 0; i < enemyCount; i++) {
			int randX = r.nextInt(Constants.BOARD_WIDTH) + Constants.BOARD_WIDTH;
			int randY = r.nextInt(Constants.BOARD_HEIGHT - Constants.PLAYER_HEIGHT2);
			enemy = new Enemy(randX, randY);
			enemy.setImage(enemySprite);
			//------
			enemy.setAnimation(explosionSpritesList);
			
			//------
			enemiesList.add(enemy);
			// enemies.add(new Enemy(1000 *i +30, 100*i+30));
		}
	}

	public void createWormHoles(int enemyCount) {
		WormHole wormHole;
		for (int i = 0; i < enemyCount; i++) {
			wormHole = new WormHole(1100, enemiesList.get(i).getY()-45);
			wormHole.setAnimation(wormholeSpritesList);
			wormholesList.add(wormHole);
		}
		System.out.println("wormholes : "+wormholesList.size());
	}
	
	
	// prepare the animations-----------------------------
//	private void initSprites(int row, int col, int width, int height, List<BufferedImage> sprites) {
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < col; j++) {
//				sprites.add(ss.grabSprite(width * j, height * i, width, height));
//			}
//		}
//	}

	// initialize the layout------------------------------
	private void init() {
		addKeyListener(new GameListener(this));
		setFocusable(true);
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
	}

	// ----------------------- Draw
	// Methods----------------------------------------------

	private void drawBoard(Graphics g) {
		// doDrawing
		drawBackGround(g);// The BackGround
		drawStats(g);// stats about the game
		drawPlayer(g);// The Player
		drawBullet(g);// The Bullet
		drawEnemies(g);// The Enemies
		drawBombs(g);// The Bombs
		//drawAnimatedExplosion(g);
		drawEnemyExplosion(g);
		drawPlayerExplosion(g);
		drawWormHoles(g);
		// drawTestRectangles(g);

	}



	private void drawPlayerExplosion(Graphics g) {
		if(player.isDead()) {
			player.paintAnimation(g);
		}
	}

	private void drawEnemyExplosion(Graphics g) {
		for(Enemy enemy :enemiesList) {
			if(enemy.isDead())
			enemy.paintAnimation(g);
		}
		
	}

	private void drawStats(Graphics g) {
		for (int i = 0; i < life; i++) {
			g.drawImage(lifeSprite, 30 * i + 10, 10, null);
		}

		Font fontS = new Font("Helvetica", Font.BOLD, 25);
		g.setColor(Color.RED);
		g.setFont(fontS);
		g.drawString("Points: " + points, 700, 30);
		g.drawString("Wave: " + wave, 1100, 30);
		g.drawString("Score: " + score, 900, 30);

		Font font = new Font("Helvetica", Font.BOLD, 50);
		FontMetrics fontMetrics = this.getFontMetrics(font);
		if (!inGame) {
			g.setColor(Color.YELLOW);
			g.setFont(font);
			g.drawString(message, Constants.BOARD_WIDTH / 2 - fontMetrics.stringWidth(message) / 2,
					Constants.BOARD_HEIGHT / 2 - 100);
		}
	}

	private void drawBackGround(Graphics g) {
		g.drawImage(backGround, this.relX, 0, Constants.BOARD_WIDTH, 640, null);
		if (relX < Constants.BOARD_WIDTH) {
			g.drawImage(backGround, this.relX + Constants.BOARD_WIDTH, 0, Constants.BOARD_WIDTH, 640, null);
		}
	}

	private void drawPlayer(Graphics g) {
		if (!this.player.isDead()) {
			//g.drawImage(player.getImage(), player.getX(), player.getY(), null);
			this.player.paintEntity(g);
		}
	}

	private void drawBullet(Graphics g) {
		if (!bullet.isDead()) {
			g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);
		}
	}

	private void drawEnemies(Graphics g) {
		for (Enemy enemy : this.enemiesList) {
			if (enemy.isVisible()) {
				if(wave >= 1 && wave < 5) {
					enemy.setImage(enemySprite);
				
				}else if (wave >= 5 && wave < 8) {
					enemy.setImage(enemySprite3);
				
				}else if (wave >= 8 && wave < 11) {
					enemy.setImage(enemySprite4);
					
				}else if (wave >= 11) {
					enemy.setImage(enemySprite5);
				
				}	
				if(enemy.getX() < 1150) {
					g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);	
				}
				
			}
		}
	}

	private void drawBombs(Graphics g) {
		for (Bomb bomb : this.bombs) {
			if (!bomb.isDead()) {
				g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), null);
			}
		}
	}

	
	private void drawWormHoles(Graphics g) {
		for(WormHole wh : wormholesList) {
			wh.paintAnimation(g);
		}
	}
	
	// draw the animations--------------------------------------------------
//	private void drawAnimatedExplosion(Graphics g) {
//		
//		if (animator != null && animator.isAlive()) {
//			animator.update(System.currentTimeMillis());
//			g.drawImage(this.animator.getSprite(), explosionAtX, explosionAtY, 64, 64, null);
//		}
//
//	}

	private void drawTestRectangles(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(player.getX() + 9, player.getY() + 7, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		for (Enemy enemy : enemiesList) {
			if (enemy.isVisible()) {
				g.drawRect(enemy.getX() + 8, enemy.getY() + 6, Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT);
			}
		}
		// for testing purpose only
	}

	// ------------------------------ GameCore
	// Methods--------------------------------------------------------

	public void render() {
		// doOneLoop
		if (inGame) {
			update();
		}
		repaint();

	}

	private void update() {
		updateParallax();
		checkGameStats();
		
		//---------------------
		updateEnemyAnimation();
		
		//--------------------
		this.player.move();
		checkCollisionOnBulletVsEnemy();
		this.bullet.move();
		respawnEnemy();
		checkPlayerStatus();
		checkEnemiesStatus();
		releaseBomb();
		checkCollisionBombVsPlayer();
		checkCollisionEnemyVsPlayer();
		checkWormholeProximity();

	}

	
	// ------------------------ Update Methods ----------------------

	private void checkWormholeProximity() {
		Enemy enemy;
		WormHole wormhole;
		for(int i = 0; i < enemiesList.size(); i++) {
			enemy = enemiesList.get(i);
			if(enemy.isVisible()){
				if(enemy.getX() < 1300) {
				wormhole = wormholesList.get(i);
				//wormhole.startAnimation();
				wormhole.runAnimation();
				}
			}
		}
		
	}

	private void updateEnemyAnimation() {
		for(Enemy enemy : enemiesList) {
			if(enemy.isDead()) {
				enemy.runAnimation();	
			}
		}	
	}

	private void updateParallax() {
		// Scrolling BackGround
		this.relX = imgX % Constants.BOARD_WIDTH;
		imgX -= 1;
	}

	private void checkPlayerStatus() {
		if (life <= 0) {
			//explosionAtX = this.player.getX();
			//explosionAtY = this.player.getY();
			//this.animator.play();
			player.runAnimation();
			this.player.kill();
		}

	}

	
	private void checkGameStats() {
		
		if(points >= 100) {
			life++;
			points = 0;
		}
		if (player.isDead()) {
			inGame = false;
			message = Constants.GAMEOVER;
		}
		System.out.println("wormholes : "+wormholesList.size()+" Enemies : "+enemiesList.size());
		
	}

	private void checkCollisionOnBulletVsEnemy() {
		if (!bullet.isDead()) {
			// bullet coordinates
			int bulletX = bullet.getX();
			int bulletY = bullet.getY();
			for (Enemy enemy : enemiesList) {
				int enemyX = enemy.getX();
				int enemyY = enemy.getY();
				if (!enemy.isVisible())
					continue;// if it`s not alive we skip it
				if (bulletX >= (enemyX + 8) && bulletX <= (enemyX + Constants.ENEMY_WIDTH) && bulletY >= (enemyY + 4)
						&& bulletY <= (enemyY + Constants.ENEMY_HEIGHT + 4)) {// it means the bullet is inside the
																				// collision
					// added some extra padding to the collision box since i1m using a smaller one
					// // box
					enemy.setVisible(false);// maybe kill();???
					//explosionAtX = enemy.getX() - 10;
					//explosionAtY = enemy.getY() - 10;
					//this.animator.play();
					bullet.kill();
					enemy.kill();
					enemyKilled++;
					score += 5;
					points += 2;
					
				}
			}
		}
	}

	private void respawnEnemy() {
		Enemy enemy;
		WormHole wormhole;
		for(int i = 0; i < enemiesList.size(); i++) {	
		enemy = enemiesList.get(i);
		//for (Enemy enemy : this.enemiesList) {
			if (enemy.isVisible()) {
				
				int randX = r.nextInt(Constants.BOARD_WIDTH) + Constants.BOARD_WIDTH;
				int randY = r.nextInt(Constants.BOARD_HEIGHT - Constants.PLAYER_HEIGHT2);
				// enemy.move(adjustValue("enemy"));
				enemy.move(Handler.adjustValues("enemy", wave));
				if (enemy.getX() < 0) {
					
					// System.out.println("randX :" + randX + " RandY : " + randY);
					enemy.setX(randX);
					enemy.setY(randY);
					wormhole = wormholesList.get(i);
					wormhole.setY(randY-45);
					wormhole.startAnimation();
					score--;// we decrease the score if the enemy get`s away
				}
			}
		}
	}

	private void checkEnemiesStatus() {
		if (enemyKilled >= enemyCount) {
			enemyCount += 2;
			enemyKilled = 0;
			wave++;
		}
		
		int count = 0;
		for (Enemy enemy : enemiesList) {
			if (!enemy.isVisible()) {
				count++;
			}
		}
		if (enemiesList.size() == count) {
			count = 0;
			enemiesList.clear();
			wormholesList.clear();
			createEnemies(enemyCount);
			createWormHoles(enemyCount);
		}
	}

	private void releaseBomb() {
		for (Enemy enemy : this.enemiesList) {
			if (enemy.isVisible() && r.nextDouble() < Constants.BOMBPROB) {
				Bomb bomb = new Bomb((enemy.getX() + 8), (enemy.getY() + 14));
				bomb.setImage(bombSprite);
				this.bombs.add(bomb);
			}
		}
	}

	private void checkCollisionBombVsPlayer() {

		for (Bomb bomb : bombs) {
			int bombX = bomb.getX();
			int bombY = bomb.getY();
			int playerX = player.getX();
			int playerY = player.getY();
			if (!bomb.isDead() && !player.isDead()) {
				if (bombX >= (playerX + 9) && bombX <= (playerX + Constants.PLAYER_WIDTH) && bombY >= (playerY + 7)
						&& bombY <= (playerY + Constants.PLAYER_HEIGHT)) {
					bomb.kill();
					life--;
//                    if(life <= 0) {
//                    	player.kill();
//                    }
				}
			}
			bomb.move();
		}
	}

	private void checkCollisionEnemyVsPlayer() {
		Rectangle r1 = new Rectangle((player.getX() + 9), (player.getY() + 7), Constants.PLAYER_WIDTH,
				Constants.PLAYER_HEIGHT);
		Rectangle r2;

		for (Enemy enemy : enemiesList) {
			if (enemy.isVisible()) {
				r2 = new Rectangle((enemy.getX() + 8), (enemy.getY() + 6), Constants.ENEMY_WIDTH,
						Constants.ENEMY_HEIGHT);
				if (r1.intersects(r2)) {
					life--;
					enemy.setVisible(false);
					enemy.kill();
					//explosionAtX = enemy.getX();
					//explosionAtY = enemy.getY();
					//this.animator.play();
//					if(life <=0 ) {
//						player.kill();
//					}
				}

			}
		}
		/**
		 * 
		 * for(Enemy enemy : enemies) { int playerX = player.getX(); int playerY =
		 * player.getY(); int enemyX = enemy.getX(); int enemyY = enemy.getY();
		 * if(enemy.isVisible()) { if (enemyX >= (playerX) && enemyX <= (playerX +
		 * Constants.PLAYER_WIDTH) && enemyY >= (playerY) && enemyY <= (playerY +
		 * Constants.PLAYER_HEIGHT)) { life--; enemy.setVisible(false); } } }
		 **/

	}

//	private int adjustValue(String constant){
//		// some values can be adjusted from here: bullet speed, enemy speed ..etc
//		int var = 0;		
//		switch (constant) {
//		case "bullet":
//			if(wave >= 1 && wave < 4) {
//				return 7;
//			}else if (wave >= 4 && wave < 6) {
//				return 10;
//			}else if (wave >= 6 && wave < 8) {
//				return 15;
//			}else if (wave >= 8) {
//				return 30;
//			}
//			break;
//		case "enemy":
//			if(wave >= 1 && wave < 6) {
//				return -1;
//			}else if (wave >= 6 && wave < 8) {
//				return -3;
//			}else if (wave >= 8 && wave < 10) {
//				return -4;
//			}else if (wave >= 10) {
//				return -5;
//			}
//			break;
//		default:
//			break;
//		}
//		return var;
//	}

	// --------------------Key Adapters---------------------------

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		this.player.keyPressed(e);// player`s bounding

		if (key == KeyEvent.VK_SPACE) {
			int bulletX = this.player.getX();
			int bulletY = this.player.getY();
			if (inGame && this.bullet.isDead()) {
				// we instantiate a new bullet only if there is none other on the board
				bullet = new Bullet(bulletX, bulletY);
				bullet.setImage(bulletSprite);

				// bullet.setBulletSpeed(adjustValue("bullet"));
				bullet.setBulletSpeed(Handler.adjustValues("bullet", wave));
				// System.out.println("bullet speed: "+adjustValue("bullet"));
			}

		}

		if (key == KeyEvent.VK_ENTER) {
			if (inGame) {
				inGame = false;
				//this.animator.pause();
			} else {
				inGame = true;
			} // adjustments needed
		}

	}

	public void keyReleased(KeyEvent e) {
		this.player.keyReleased(e);// player`s bounding
	}

}
