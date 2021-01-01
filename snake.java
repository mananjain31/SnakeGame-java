import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Fd extends JFrame
{
	Fd()
	{
		setLayout(new BorderLayout());
		JPd jpd=new JPd();
		add(jpd);	
	}
}
class JPd extends JPanel implements ActionListener,KeyListener
{
	ImageIcon img1,img2,img3,img4,img5;
	Image body,head,bg,food,gameOver;
	Timer t;
	long count=0;
	
	int x[]=new int[1000];
	int y[]=new int[1000];
	int score,num=5;
	int fx,fy,gox=200,goy=0;
	
	boolean game,right,left,up,down;
	
	JPd()
	{
		img1=new ImageIcon("head.png");
		head=img1.getImage();
		
		img2=new ImageIcon("body.png");
		body=img2.getImage();
		
		img3=new ImageIcon("bg.png");
		bg=img3.getImage();
		
		img4=new ImageIcon("food.png");
		food=img4.getImage();
		random();
		
		img5=new ImageIcon("GAMEOVER.jpg");
		gameOver=img5.getImage();
		
		t=new Timer(100,this);
		t.start();
		
		x[0]=100;
		y[0]=20;
		x[1]=80;
		y[1]=20;
		x[2]=60;
		y[2]=20;
		x[3]=40;
		y[3]=20;
		x[4]=20;
		y[4]=20;
		
		addKeyListener(this);
		setFocusable(true);
		game=true;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bg,0,0,this);
		g.drawImage(food,fx,fy,this);
		
		
		
		
		
		
		for(int i=0;i<num;i++)
		{
		if(i==0)
		{
			g.drawImage(head,x[0],y[0],this);
		}
		else
		g.drawImage(body,x[i],y[i],this);
		}
		
		if(game==true)
		{	
			if(x[0]==fx&&y[0]==fy)
			{
				random();
				if(num<=10)
					num++;
				else if(num<=20)
					num+=2;
				else if(num<=30)
					num+=3;
				else if(num<=50)
					num+=4;
				else
					num+=4;
				
				score=num*10;
			}
		
		
			for(int i=num-1;i>0;i--)
			{
				x[i]=x[i-1];
				y[i]=y[i-1];
			}
		
			if(right)
				x[0]+=20;
		
			else if(left)
				x[0]-=20;
	
			else if(up)
				y[0]-=20;

			else if(down)
				y[0]+=20;
		
			escapeFromVision();
			eatItself(true);
		}
		else
		{
			goy+=10;
			g.drawImage(gameOver,gox,goy,this);
			
			if(goy==200)
				t.stop();
		}
		g.setColor(Color.red);
		g.fillRoundRect(0,5,40,35,5,5);
		g.setColor(Color.black);
		g.setFont(new Font("Corbel Light",Font.BOLD,30));
		g.drawString(""+(5-(count/10)),5,35);
		
		g.setFont(new Font("Corbel Light",Font.BOLD,25));
		g.drawString("score:",10,75);
		g.drawString(""+score,10,95);
	}
	
	public void escapeFromVision()
	{
		if(x[0]>580)
			x[0]=0;
		if(y[0]>580)
			y[0]=0;
		if(x[0]<0)
			x[0]=580;
		if(y[0]<0)
			y[0]=580;
	}
	
	public void eatItself(boolean doIt)
	{
		if(doIt&&(right||left||up||down))
		for(int i=1;i<num;i++)
		{
				if(x[0]==x[i]&&y[0]==y[i])
				{
					right=left=up=down=false;
					System.out.println("Lose");
					count=0;
					game=false;
					// g.drawImage(ate,x[i],y[i],this);
				}
		}
	}
	
	public void random()
	{
		fx=(int)Math.round(Math.random()*(580/20));
		fy=(int)Math.round(Math.random()*(580/20));
		fx*=20;
		fy*=20;
		
		count=0;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
		if(game)
		{
			count++;
			if(count==100/2)
			{
				random();
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(left==false)
			{
			System.out.println("r");
			right=true;
			left=false;
			up=false;
			down=false;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(right==false)
			{
			System.out.println("l");
			right=false;
			left=true;
			up=false;
			down=false;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			if(down==false)
			{
			System.out.println("u");
			right=false;
			left=false;
			up=true;
			down=false;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			if(up==false)
			{
			System.out.println("d");
			right=false;
			left=false;
			up=false;
			down=true;			
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			snake.f.setVisible(false);
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			System.out.println("esc");
			snake.f.setVisible(false);
			snake.main();
			System.out.println("ape");
		}
		
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}
class snake
{
	static Fd f;
	public static void main(String...args)
	{
		int wid=600,hig=600;
		f=new Fd();
		f.setVisible(true);
		f.setSize(wid+15,hig+35);
		f.setLocation(400,5);
	}
}