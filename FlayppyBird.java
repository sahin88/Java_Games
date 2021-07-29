package myGames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
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

public class FlayppyBird {

	public static void main(String[] args) {
		FlapyFrame fpfr= new FlapyFrame();
	}

}

class FlapyFrame extends JFrame{
	FlappyPanel fpanel = new FlappyPanel();
	
	
	public FlapyFrame() {
	this.setResizable(true);
	this.setBounds(10,30,600,600);
	this.setVisible(true);
	this.add(fpanel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	
	
}


class FlappyPanel extends JPanel implements KeyListener,ActionListener{
	
	
	 int BallPositionX = 100;
	 int BallPositionY = 100;
	 int FallingDown=1;
	 int [] pipelist= new int[25];
	 int ScreenHeight=600;
	 int TopHeight;
	 int BottomHeight;
	 int Width=20;
	 int Gap=150;
	 int Score=0;
	 List<List<Integer>> TopPipe = new ArrayList<>();
	 List<List<Integer>> BottomPipe = new ArrayList<>();
	 List <Integer> topX= new ArrayList<Integer>();	
	
	 List <Integer> SubTopPipe= new ArrayList<Integer>();		
	 List <Integer> SubBottomPipe= new ArrayList<Integer>();
	 int StepX=2;
	 int counter=0;
	 
	 boolean play=true;
	public FlappyPanel () {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		int delay=150;
		Timer timer= new Timer(delay, this);
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(10,10, 600, 600);
		if(TopPipe.size()<5) {
			 createPipe();
				
		}
		if (play){
		for(int i=0; i<TopPipe.size();i++) {
		
			
			g.setColor(Color.RED);
			g.fillRect(TopPipe.get(i).get(0),TopPipe.get(i).get(1), TopPipe.get(i).get(2), TopPipe.get(i).get(3));
			g.setColor(Color.RED);
			g.fillRect(BottomPipe.get(i).get(0),BottomPipe.get(i).get(1), BottomPipe.get(i).get(2), BottomPipe.get(i).get(3));
			
			 List<Integer> topsub=TopPipe.get(i);
			 List<Integer> bottomsub=BottomPipe.get(i);
			 if (topsub.get(0)<50) {
				 TopPipe.remove(i);
				 BottomPipe.remove(i);
			 }
			 else {
				 int Xval=topsub.get(0)-StepX;
				 int Yval=bottomsub.get(0)-StepX;
				 
				 topsub.set(0, Xval);
				 TopPipe.set(i, topsub);
				 bottomsub.set(0, Yval);
				 BottomPipe.set(i, bottomsub);
			 }
		g.setColor(Color.white);
		g.fillOval(BallPositionX,BallPositionY,20,20);
		
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("serif",Font.BOLD,18));
		g.drawString("Score : "+Score,10,22);	
	}
	}
		for(int i=0; i<TopPipe.size();i++) {
			
			 Rectangle rect_bottom =new Rectangle(BottomPipe.get(i).get(0),BottomPipe.get(i).get(1), BottomPipe.get(i).get(2), BottomPipe.get(i).get(3));
			 Rectangle rect_top =new Rectangle(TopPipe.get(i).get(0),TopPipe.get(i).get(1), TopPipe.get(i).get(2), TopPipe.get(i).get(3));
			
			 Rectangle rect_ball =new Rectangle(BallPositionX,BallPositionY,20,20);
			if (rect_ball.intersects(rect_bottom) ||rect_ball.intersects(rect_top)) {
				g.setColor(Color.YELLOW);
				play=false;
				g.setFont(new Font("serif",Font.BOLD,30));
				g.drawString("Game Over !",190,300);
				
				g.setFont(new Font("serif",Font.BOLD,20));
				g.drawString("Press Enter to Restart :",230,350);
			}
		}
	}
	private void createPipe() {
		
		Integer posX;
		if(TopPipe.size()==0) {
			posX =400;
		}
		else {
			List<Integer> yeniceri=TopPipe.get(TopPipe.size()-1);
			posX=yeniceri.get(0)+200;
		}
		
		 int posY=60;
		Random rndm= new Random();
		TopHeight= rndm.nextInt(ScreenHeight/2-Gap)+Gap/2;
		BottomHeight=ScreenHeight-TopHeight-Gap;
		List <Integer> SubTopPipe= new ArrayList<Integer>();
		SubTopPipe.add(posX);
		SubTopPipe.add(posY);
		SubTopPipe.add(Width);
		SubTopPipe.add(TopHeight);
		TopPipe.add(SubTopPipe);
		List <Integer> SubBottomPipe= new ArrayList<Integer>();
		SubBottomPipe.add(posX);
		SubBottomPipe.add(TopHeight+Gap);
		SubBottomPipe.add(Width);
		SubBottomPipe.add(BottomHeight);
		BottomPipe.add(SubBottomPipe);
		System.out.println();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		checkCollision();
				
		repaint();
		
	}
	



	private void checkCollision() {
		
		BallPositionY+=10*FallingDown;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			
			BallPositionY-=50*FallingDown;
			
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}



