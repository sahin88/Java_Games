package myGames;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PingPong {

	public static void main(String[] args) {
		
		MyWFrame obj2 = new MyWFrame();
	}

}
class MyWFrame extends JFrame{

	
	public MyWFrame() {
		
		
		
		
		setResizable(true);
		setVisible(true);
		setTitle("Breaket Game");
		setBounds(10,10,700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 MyWPanel pnl= new MyWPanel();
		 this.add(pnl);
		
		
		
	}
	
}


class MyWPanel extends JPanel implements KeyListener, ActionListener{
	int BallPositionX=(int)(Math.random()*600)+20;
	int BallPositionY=(int)(Math.random()*500);
	int BallDirX=1;
	int BallDirY=2;
	int delay=9;
	int PaddleA=250;
	int PaddleB=250;
	int ScoreA=0;
	int ScoreB=0;
	int Round=0;
	boolean play=true;
	
	
	Timer timer= new Timer(delay,this);
	
	public MyWPanel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer.start();
		
	}
	

	

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.blue);
		g.fillRect(1, 1, 700, 592);
//		this.setBackground(Color.BLUE);
		g.setColor(Color.green);
		g.fillRect(350, 10, 5, 550);
		
	
		
		
		g.setColor(Color.red);
		g.fillOval(BallPositionX,BallPositionY,20,20);
		
		g.setColor(Color.YELLOW);
		g.fillRect(20, PaddleA, 15, 100);
		
		
		g.setColor(Color.green);
		g.fillRect(682,PaddleB, 15, 100);
		g.setColor(Color.PINK);
		g.setFont(new Font("serif",Font.BOLD,16));
		g.drawString(" Score B : "+ScoreB,390,30);
		
		g.setColor(Color.PINK);
		g.setFont(new Font("serif",Font.BOLD,16));
		g.drawString("Score A : "+ScoreA,200,30);
		
		
		g.setColor(Color.PINK);
		g.setFont(new Font("serif",Font.BOLD,15));
		g.drawString("Round : "+Round,10,30);
		if (!play) {
			
			g.setColor(Color.PINK);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over, Score :",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart :",230,350);
		}
		
	
		g.dispose();
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(play) {
			BallPositionX+=BallDirX;
			BallPositionY+=BallDirY;
			Rectangle PaddleARect =new Rectangle(0, PaddleA, 20, 100);
			Rectangle BallRect =new Rectangle(BallPositionX,BallPositionY,20,20);
			Rectangle PaddleBRect =new Rectangle(682,PaddleB, 20, 100);
			System.out.println("Sonuc   "+BallRect.intersects(PaddleBRect)+"  Hipokrat  :"+BallPositionX+" besss  :"+BallDirX);
			
			if (BallRect.intersects(PaddleBRect)){
				BallDirX=-BallDirX;
				ScoreB++;
		
			}
			if (BallRect.intersects(PaddleARect)){
				BallDirX=-BallDirX;
				ScoreA++;
			
			}
		if (BallPositionX>687 ) {
			BallDirX=0;
			BallDirY=0;
			Round++;
			if(Round>2&&play==true) {
				
				play=false;
			}
			else {
				BallPositionX=(int)(Math.random()*600)+20;;
				BallPositionY=(int)(Math.random()*500);;
				BallDirX=1;
				BallDirY=2;
				delay=9;
				PaddleA=250;
				PaddleB=250;
				
				
			}
			
			
		}
		
		if ( BallPositionX<15) {
			BallDirX=0;
			BallDirY=0;
			Round++;
			if(Round>2&&play==true) {
				
				play=false;
			}
			else {
				
				BallPositionX=(int)(Math.random()*600)+20;;
				BallPositionY=(int)(Math.random()*500);
				BallDirX=1;
				BallDirY=2;
				delay=9;
				PaddleA=250;
				PaddleB=250;
				
				
			}
		}
		if (BallPositionY<0 || BallPositionY>600 ) {
			BallDirY=-BallDirY;
		}
		

		
		}
		
		timer.start();
		
		repaint();
		
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==38) {
			
			if(PaddleB<5) {
				PaddleB=10;
			}
			else {
				moveUpB();
				}
		}
		if(e.getKeyCode()==87)
		
		{ if(PaddleA<5) {
			
				PaddleA=10;
			}
			else {
			moveUpA();
			}
			
		}
		
		if(e.getKeyCode()==40) {
			
			if(PaddleB>500) {
				PaddleB=500;
			}
			
			else {
				moveDownB();
			}}
		if(e.getKeyCode()==83) {
			if(PaddleA>500 ) {
				PaddleA=500;
			}
			else {
				moveDownA();
			}
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			
		if(!play) {
			BallPositionX=25;
			BallPositionY=50;
			BallDirX=1;
			BallDirY=2;
			delay=9;
			PaddleA=250;
			PaddleB=250;
			ScoreA=0;
			ScoreB=0;
			play=true;
			Round=0;
		}
		
	}

	}

	private void moveUpB() {
		play=true;
		PaddleB-=20;
		
		
	}
	
	private void moveUpA() {
		play=true;
		PaddleA-=20;
	
		
	}
	private void moveDownA() {
		play=true;
		PaddleA+=20;
		
	}
	
	private void moveDownB() {
		play=true;
		PaddleB+=20;
	}
		
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}