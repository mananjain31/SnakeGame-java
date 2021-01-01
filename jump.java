import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class jf extends JFrame
{
	jf()
	{
		jp p = new jp();
		add(p);
	}
}

class jp extends JPanel implements ActionListener,KeyListener
{
	int x = 0;
	int y = 0;
	int width = 50;
	int height = 50;
	int vel = 15;
	boolean right = false;
	boolean left = false;
	boolean up = false;
	boolean down = false;
	boolean jumping = false;
	boolean jumping2 = false;
	int jumpCount = 8;
	jp()
	{
		System.out.println("jp is on "); 
		Timer t = new Timer(40,this);
		t.start();
		addKeyListener(this);
		setFocusable(true);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(right && x < 500 - width - vel)
		{
			x += vel;
		}
		if(left && x > 0 )
		{
			x -= vel;
		}
		if( !(jumping) )
		{
			if(up && y > 0)
			{
				y -= vel;
			}
			if(down && y < 500 - height - vel*3)
			{
				y += vel;
			}
		}
		else
		{
			if(jumpCount >= -8)
			{
				int neg = 1;
				if(jumpCount <= 0) 
					neg = -1;
				System.out.println(y);
				y -= (jumpCount * jumpCount) * neg;
				System.out.println("  " + y);
				jumpCount -- ;
			}
			else
			{
				jumping = false;
				jumpCount = 8;
			}
		}

		g.fillRect(0,0,500,500);
		if( ! jumping)
		{
			if(left)
				g.setColor(Color.red);
			else if(right)
				g.setColor(Color.green);
			else if (up)
				g.setColor(Color.blue);
			else if (down)
				g.setColor(Color.pink);
			else 
				g.setColor(Color.black);
		}
		else if(jumping)
				g.setColor(Color.white);
		g.fillRoundRect(x, y, width, height,20,20);
	}
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = false;
		}		
		// if(e.getKeyCode() == KeyEvent.VK_SPACE)
		// {
			// jumping = false;
		// }
	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT )
		{
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP )
		{
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jumping = true;
		}
	}
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jumping = true;
		}
	}
}

class jump
{
	public static void main(String...args)
	{
		jf f = new jf();
		f.setVisible(true);
		f.setBounds(0,0,500,500);
	}
}