import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Rozgrywka extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeDlugoscX = new int[1000];
	private int[] snakeDlugoscY = new int[1000];
	
	private int[] pozycjeJablekX= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,
			675,700,725,750,775,800,825,850};
	private int[] pozycjeJablekY= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600};
	
	private Random losowa =new Random();
	
	private int xPoz = losowa.nextInt(34);
	private int yPoz = losowa.nextInt(22);
	
	private boolean gora = false;
	private boolean dol = false;
	private boolean lewo = false;
	private boolean prawo = false;
	
	private ImageIcon goraSnake;
	private ImageIcon dolSnake;
	private ImageIcon prawoSnake;
	private ImageIcon lewoSnake;
	private ImageIcon snake;
	private ImageIcon jablko;
	private ImageIcon koniec;
	
	private int dlugoscSnake = 3;
	private int ruchy =0;
	private int wynik =0;
	private int tmpwynik;
	private int tmpruchy;
	
	private Timer czas;
	private int opoznienie = 100;
	
	public Rozgrywka()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		czas = new Timer(100,this);
		czas.start();
		
		
	}
	public void paint(Graphics g) {
		
		if(ruchy == 0) {
			snakeDlugoscX[0]=100;
			snakeDlugoscX[1]=75;
			snakeDlugoscX[2]=50;
			
			snakeDlugoscY[0]=100;
			snakeDlugoscY[1]=100;
			snakeDlugoscY[2]=100;
		}

		
		g.setColor(Color.RED);
		g.drawRect(24, 74, 851, 551);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(25, 75, 850, 550);
		
		prawoSnake = new ImageIcon("snakeright.png");
		prawoSnake.paintIcon(this, g, snakeDlugoscX[0], snakeDlugoscY[0]);
		
		for(int i =0; i<dlugoscSnake;i++) {
			if(i==0 && gora) {
				goraSnake = new ImageIcon("snakeup.png");
				goraSnake.paintIcon(this, g, snakeDlugoscX[i], snakeDlugoscY[i]);
			}
			if(i==0 && dol) {
				dolSnake = new ImageIcon("snake.png");
				dolSnake.paintIcon(this, g, snakeDlugoscX[i], snakeDlugoscY[i]);
			}
			if(i==0 && prawo) {
				prawoSnake = new ImageIcon("snakeright.png");
				prawoSnake.paintIcon(this, g, snakeDlugoscX[i], snakeDlugoscY[i]);
			}
			if(i==0 && lewo) {
				lewoSnake = new ImageIcon("snakeleft.png");
				lewoSnake.paintIcon(this, g, snakeDlugoscX[i], snakeDlugoscY[i]);
			}
			if(i!= 0) {
				snake = new ImageIcon("body.png");
				snake.paintIcon(this, g, snakeDlugoscX[i], snakeDlugoscY[i]);
			}
		}
		
		
		
		jablko = new ImageIcon("apple.png");
		
		if(pozycjeJablekX[xPoz] == snakeDlugoscX[0] && pozycjeJablekY[yPoz] == snakeDlugoscY[0]) {
			dlugoscSnake++;
			xPoz = losowa.nextInt(34);
			yPoz = losowa.nextInt(22);
			wynik+=dlugoscSnake;
		}
		
		jablko.paintIcon(this, g, pozycjeJablekX[xPoz], pozycjeJablekY[yPoz]);
		
		for(int j =1 ;j<dlugoscSnake;j++) {

			if(snakeDlugoscX[j]==snakeDlugoscX[0] && snakeDlugoscY[j]==snakeDlugoscY[0]) {
				koniec = new ImageIcon("gameover.jpg");
				koniec.paintIcon(this, g, 50, 75);
				
				g.setColor(Color.RED);
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Spacja zeby zagrac jeszcze raz", 300, 350);
				g.drawString("Enter by zapisaæ wynik", 300, 400);
				g.drawString("Dlugosc "+dlugoscSnake, 300, 450);
				g.drawString("Wynik "+wynik, 300, 500);
				
				tmpruchy=ruchy;
				tmpwynik=wynik;
				ruchy=0;
				dlugoscSnake=3;
				wynik=0;
				gora=false;
				dol=false;
				prawo=false;
				lewo=false;
				
			}
		}
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		czas.start();
		if(gora) {
			for(int i = dlugoscSnake-1;i>=0;i--) {
				snakeDlugoscX[i+1] = snakeDlugoscX[i];
			}
			for(int j=dlugoscSnake;j>=0;j--) {
				if(j==0) {
					snakeDlugoscY[j]=snakeDlugoscY[j]-25;
				}
				else {
					snakeDlugoscY[j]=snakeDlugoscY[j-1];
				}
				if(snakeDlugoscY[j]<75) {
					//dopisac koniec gra wyswietlic ilosc pkt
					snakeDlugoscY[j]= 600;
				}
			}
			repaint();
		}
		if(dol) {
			for(int i = dlugoscSnake-1;i>=0;i--) {
				snakeDlugoscX[i+1] = snakeDlugoscX[i];
			}
			for(int j=dlugoscSnake;j>=0;j--) {
				if(j==0) {
					snakeDlugoscY[j]=snakeDlugoscY[j]+25;
				}
				else {
					snakeDlugoscY[j]=snakeDlugoscY[j-1];
				}
				if(snakeDlugoscY[j]>600) {
					//dopisac koniec gra wyswietlic ilosc pkt
					snakeDlugoscY[j]= 75;
				}
			}
			repaint();			
		}
		if(prawo) {	
			for(int i = dlugoscSnake-1;i>=0;i--) {
				snakeDlugoscY[i+1] = snakeDlugoscY[i];
			}
			for(int j=dlugoscSnake;j>=0;j--) {
				if(j==0) {
					snakeDlugoscX[j]=snakeDlugoscX[j]+25;
				}
				else {
					snakeDlugoscX[j]=snakeDlugoscX[j-1];
				}
				if(snakeDlugoscX[j]>850) {
					//dopisac koniec gra wyswietlic ilosc pkt 
					snakeDlugoscX[j] = 25;
				}
			}
			repaint();
		}
		if(lewo) {
			for(int i = dlugoscSnake-1;i>=0;i--) {
				snakeDlugoscY[i+1] = snakeDlugoscY[i];
			}
			for(int j=dlugoscSnake;j>=0;j--) {
				if(j==0) {
					snakeDlugoscX[j]=snakeDlugoscX[j]-25;
				}
				else {
					snakeDlugoscX[j]=snakeDlugoscX[j-1];
				}
				if(snakeDlugoscX[j]<25) {
					//dopisac koniec gra wyswietlic ilosc pkt
					snakeDlugoscX[j] = 850;
				}
			}
			repaint();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			dlugoscSnake=3;
			ruchy=0;
			wynik=0;
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			File file = new File("results.txt");
			FileWriter fr = null;
			try {
				// Below constructor argument decides whether to append or override
				fr = new FileWriter(file, true);
				fr.write(tmpruchy + ", " + tmpwynik + "\n");

			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					fr.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			ruchy++;
			gora =true;
			if(!dol) {
				gora=true;
			}
			else {
				gora=false;
				dol=true;
			}
			lewo=false;
			prawo=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_DOWN) {
			ruchy++;
			dol =true;
			if(!gora) {
				dol=true;
			}
			else {
				dol=false;
				gora=true;
			}
			prawo=false;
			lewo=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			ruchy++;
			prawo =true;
			if(!lewo) {
				prawo=true;
			}
			else {
				prawo=false;
				lewo=true;
			}
			gora=false;
			dol=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			ruchy++;
			lewo =true;
			if(!prawo) {
				lewo=true;
			}
			else {
				lewo=false;
				prawo=true;
			}
			gora=false;
			dol=false;
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}