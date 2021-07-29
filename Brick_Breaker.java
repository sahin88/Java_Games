package myGames;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Brick_Breaker {

public static  void main(String[] args) {
		
		Frame frm=new Frame();
		
		
		System.out.println("Fuck you all!");
		
	}

}

class Frame extends JFrame{
	
	public Frame() {
//		setLayout(new FlowLayout());
		setResizable(true);
		setVisible(true);
		setTitle("Breaket Game");
		setBounds(10,10,700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePlay game =new GamePlay();
		add(game);
		
	}
}
	


class GamePlay extends JPanel implements KeyListener,ActionListener{
	private boolean play=false;
	private int totalBricks=21;
	private int score;
	private Timer time;
	private int delay=8;
	private int playerX=320;
	private int ballPositionX=20;
	private int ballPositionY=330;	
	private int ballYDir=-2;	
	private int ballXDir=-1;
	private MapGenerator map;
	Timer tmer;
	
	public GamePlay() {
		map= new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tmer= new Timer(delay, this);
		tmer.start();
	}
			
	
	public void  paint(Graphics g) {
		//BACKGROUND
		g.setColor(Color.blue);
		g.fillRect(1, 1, 692, 592);
		map.draw((Graphics2D)g);
		//BORDERS
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		
		g.setColor(Color.PINK);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);
		//TENNIS
		g.setColor(Color.red);
		g.fillRect(playerX,550,100,8);
		//BALL
		g.setColor(Color.red);
		g.fillOval(ballPositionX,ballPositionY,20,20);
		if (ballPositionY > 570) {
			play = false;
			ballYDir=0;
			ballXDir=0;
			
			g.setColor(Color.PINK);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Score :"+score,190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart :",230,350);
		}
		
		
		g.dispose();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Public time call this section first*/
		if (new Rectangle(ballPositionX,ballPositionY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
			ballYDir=-ballYDir;	
		}
		A: for(int i=0; i<map.map.length; i++) {
			 for(int j=0; j<map.map[0].length; j++)
				 if (map.map[i][j]>0) {
					 int brickX=j*map.brickWidth+80;
					 int brickY=i*map.brickHeight+50;
					 int brickHeight=map.brickHeight;
					 int brickWidth=map.brickWidth;
					 Rectangle rect =new Rectangle(brickX, brickY, brickWidth,brickHeight);
					 Rectangle ballRect =new Rectangle(ballPositionX, ballPositionY, 20,20);
					 Rectangle brickRect=rect;
					 if (ballRect.intersects(brickRect)){
						 map.setBrickValue(0, i, j);
						 totalBricks--;
						 score+=5;
						 
						 if(ballPositionX+19 <= brickRect.x || ballPositionX+1 >= brickRect.x+brickRect.width) {
							 ballXDir=-ballXDir;
							 
						 }
						 else {
							 ballYDir=-ballYDir;
							 
						
						 }
						 
						 break A;
					 }
		 }}			
		 
		if (play) {
			ballPositionX+=ballXDir;
			ballPositionY+=ballYDir;
			if(ballPositionX>670) {
				ballXDir=-ballXDir;
			}
			if(ballPositionX<0) {
				ballXDir=-ballXDir;
			}
			
			if(ballPositionY<0) {
				ballYDir=-ballYDir;
			}
		}
		
		System.out.println("ballPositionY  ;  "+ballPositionY);
		
		tmer.start();
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
		
			if(playerX>=600) {
				playerX=600;
			}
			else {
				moveRight();
			}
			
		}
	if(e.getKeyCode()==KeyEvent.VK_LEFT) {

		if(playerX<=10) {
			playerX=10;
		}
		else {
			moveLeft();
		}
		
			}
	
	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		
		if (!play) {
			play=true;
			totalBricks=21;
			score=0;
		
		    playerX=320;
		    ballPositionX=20;
		    ballPositionY=330;	
		    ballYDir=-2;	
			ballXDir=-1;
			map= new MapGenerator(3,7);
		}
		
	}

	}

	private void moveLeft() {
		play=true;
		playerX -= 20;
		
	}


	private void moveRight() {
		play=true;
		playerX+=20;
		System.out.println(playerX);
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


class MapGenerator{
	public int map[][];
	public int brickWidth;
	public  int brickHeight;
	public MapGenerator(int row, int col) {
		
		map = new int[row][col];
		for (int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j]=1;
				
			}
		}
		brickWidth=540/col;
		brickHeight=150/row;
	};
	
	public void draw(Graphics2D g) {
		for (int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]==1) {
				g.setColor(Color.green);
				g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.BLACK);
				g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
				}
		
	}
}

	}
	
	public  void setBrickValue(int value, int row, int col) {
		map[row][col]=value;
	}
	

	
	
	
	
	
}
