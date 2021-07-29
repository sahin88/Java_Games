package myGames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Snake {
	
	public static  void main(String[] args) {
		
		MF frm=new MF();
		
		
		
		
	}

}
class MF extends JFrame{
	MJ myp= new MJ();
	
	public MF() {
		
		this.setVisible(true);
		this.setResizable(true);
		this.setTitle("Snake Game");
		this.setBounds(10,10,800,800);
		this.add(myp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}



class MJ extends JPanel implements KeyListener, ActionListener {
	
	Timer tmer;
	int delay=500;
	int width=25;
	int height=25;
	int ScreenWidth=600;
	int ScreenHeight=600;
	String last_direction="RIGHT";
	int stepX=25;
	int stepY=0;
	int length_snake=1;
	int [] snakelistX= new int[ScreenWidth/width];
	int [] snakelistY= new int[ScreenWidth/width];
	boolean collision;
	int FoodPosX;
	int FoodPosY;
	int ActualX=25;
	int ActualY=25;
	boolean play= true;
	int Score=0;
	
	
	
	
	public MJ() {
		super();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tmer= new Timer(delay, this);
		Random rnd= new Random();
		FoodPosX=(rnd.nextInt(ScreenWidth/width)+1)*width;
		FoodPosY=(rnd.nextInt(ScreenWidth/width)+1)*width;
		tmer.start();
	}


	public void paint(Graphics g) {
		
		//SETTING SCREEN
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, ScreenWidth+2*(ScreenWidth/width), ScreenHeight+2*(ScreenWidth/width));
		
	
		if(play)
			{for (int i=1;i<ScreenWidth/width+1;i++) {
				for (int j=1;j<ScreenHeight/height+1;j++) {
					g.setColor(Color.GRAY);
					g.drawRect(i*width, j*height, width, height);
				}
			};
			
			
			//
			g.setColor(Color.GREEN);
			g.fillRect( FoodPosX,  FoodPosY, width, height);
			for(int i= length_snake; i>0;i--) {
				
				g.setColor(Color.RED);
				g.fillRect( snakelistX[i],  snakelistY[i], width, height);
			}
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,15));
			g.drawString("Score : "+Score,10,22);	
	
		}
		
		else {

			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over !",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart :",230,350);
		}
		g.setColor(Color.YELLOW);
		g.drawRect( width,  height,ScreenWidth, ScreenHeight);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		

	
		motion();
		collision();
		
		repaint();
		
		
	}

	
	private void collision() {
		 Rectangle rect =new Rectangle(snakelistX[0], snakelistY[0], width,height);
		 Rectangle food =new Rectangle(FoodPosX, FoodPosY, width,height);
		 Rectangle brect =new Rectangle(width,  height,ScreenWidth, ScreenHeight);
		if (snakelistX[0]==FoodPosX&&snakelistY[1]==FoodPosY) {
			
			length_snake++;
			Score++;
			Random rnd= new Random();
			FoodPosX=(rnd.nextInt(ScreenWidth/width)+1)*width;
			FoodPosY=(rnd.nextInt(ScreenWidth/width)+1)*width;
		}
		
		if(!brect.intersects(rect)) {
			play=false;
			stepX=0;
			stepY=0;
		}
		
	}


	private void motion() {
		for(int i= length_snake; i>0;i--) {
			snakelistX[i]=snakelistX[i-1];
			snakelistY[i]=snakelistY[i-1];
			
			
		}
		ActualX+=stepX;
		ActualY+=stepY;
		snakelistX[0]=ActualX;
		snakelistY[0]=ActualY;
		
		System.out.println("ActualX"+ActualX+"ActualY"+ActualY);
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT&& last_direction!="RIGHT") {
			stepX=-width;
			stepY=0;
			last_direction="LEFT";
			
		};
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT&& last_direction!="LEFT") {
			stepX=+height;
			stepY=0;
			last_direction="RIGHT";
			
		};
		
		if (e.getKeyCode()==KeyEvent.VK_UP&& last_direction!="DOWN") {
			stepY=-width;
			stepX=0;
			last_direction="UP";
			
		};
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN&& last_direction!="UP") {
			stepY=+width;
			stepX=0;
			last_direction="DOWN";
			
		};
		
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			
			Random rnd= new Random();
			FoodPosX=(rnd.nextInt(ScreenWidth/width)+1)*width;
			FoodPosY=(rnd.nextInt(ScreenWidth/width)+1)*width;
			
		
			last_direction="RIGHT";
			stepX=25;
			stepY=0;
			length_snake=1;
			snakelistX= new int[ScreenWidth/width];
			snakelistY= new int[ScreenWidth/width];
		
			ActualX=25;
			ActualY=25;
			play= true;
			Score=0;
			
			
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}



