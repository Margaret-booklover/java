import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class Lab7
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame fr = new RaceFrame(); 
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fr.setVisible(true);
			}
		});
	}
}

class RaceRunnable implements Runnable{
	private Car car;
	private Component component;
	public static final int SLEEP = 25;
	JLabel first = new JLabel("");
	public static boolean flag = true;
	public RaceRunnable(Component comp, Car temp, JLabel f){
		component = comp;
		car = temp;
		first = f;
        first.setPreferredSize(new Dimension(50,50));
	}
	public void run(){
		try{
			while( (car.getX() < component.getBounds().getWidth())){
					car.move(component.getBounds(),5);
					component.repaint();	
					Thread.sleep(SLEEP);	
				}
		}
		catch(InterruptedException e){return;}
		if (car.getX() >= component.getBounds().getWidth()) {
			int y = car.getY();
			first.setVisible(true);
			if (flag) {
				if (y==70) first.setText("Победил Первый");
				else if (y==130) first.setText("Победил Второй");
				else if (y==190) first.setText("Победил Третий");
				flag=false;
			}
		}
	}
}

class RaceFrame extends JFrame{
	private Cars component;
	JLabel winner = new JLabel("", SwingConstants.CENTER);
	ArrayList<Thread> threads = new ArrayList<Thread>();
	public RaceFrame ()
	{
		component = new Cars();
		component.setBackground(component.getColor());
		add(component, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start/Restart", new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int i=0;
				for(Thread thread : threads){
					i++;
				    thread.interrupt();
				}
				threads.clear();
				System.out.println(i);
				startRace();
			}
		});
		
		addButton(buttonPanel, "Close", new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{ 
				System.exit(0);
			}
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
		add(winner, BorderLayout.NORTH);
		pack();
	}

	public void addButton(Container c, String title, ActionListener listener)
	{
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	public void startRace()
	{
		Car car1 = new Car(0,20);
		Car car2 = new Car(0,80);
		Car car3 = new Car(0,140);
		RaceRunnable ob1 = new RaceRunnable(component, car1, winner);
		RaceRunnable ob2 = new RaceRunnable(component, car2, winner);
		RaceRunnable ob3 = new RaceRunnable(component, car3, winner);
		RaceRunnable.flag=true;
		try {
			winner.setVisible(false);
			Cars.add(car1, car2, car3);
			Thread t1 = new Thread(ob1);
			Thread t2 = new Thread(ob2);
			Thread t3 = new Thread(ob3);
			threads.add(t1);
			threads.add(t2);
			threads.add(t3);
			t1.start();
			t2.start();
			t3.start(); 
		} catch (Exception e) {}
	}
}

class Cars extends JPanel{
	private static final int wid = 450;
	private static final int hei = 250;
	private static Color color = Color.WHITE;
	private static Car[] cars = new Car[3];
	
	public Color getColor(){
		return color;
	}
	public static void add(Car a, Car b, Car c){
		cars[0] = a;
		cars[1] = b;
		cars[2] = c;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Car b : cars){
			if (b != null){
				g2.setPaint(b.getColor());
				g2.fill(b.getShape());
				if (b.getX() >= wid){
					this.setBackground(b.getColor());
					color = b.getColor();
					g2.fill(b.getShape());
					cars = new Car[3];
				}
			}
		}
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(wid, hei);
	}
}

class Car{
	private int x = 0;
	private int y = 0;
	private static final int length = 100;
	private static final int width = 50;
	private Color color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	
	public Car(int a, int b){
		x = a;
		y = b;
	}
	public Color getColor(){
		return color;
	}
	public void move(Rectangle2D bounds, int c){
		x += Math.random()*c;
	}
	
	public Rectangle2D getShape(){
		return new Rectangle2D.Double(x, y, length, width);
	}
	
	public int getX(){
		return x + 100;
	}
	public int getY(){
		return y + 50;
	}
}