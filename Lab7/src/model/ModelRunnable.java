package model;

import java.awt.*;
import javax.swing.JLabel;
/**
 * Здесь описывается поведение объекта машинки 
 * @author Fujitsu
 *
 */
public class ModelRunnable implements Runnable{
	private ModelCar car;
	private Component component;
	public static final int SLEEP = 25;
	JLabel first = new JLabel("");
	private static int counter=1;
	public static boolean flag = true;
	/**конструктор с параметрами, создает машинку на поле*/
	public ModelRunnable(Component comp, ModelCar temp, JLabel f){
		component = comp;
		car = temp;
		first = f;
        first.setPreferredSize(new Dimension(50,50));
	}
	/**переопределенный метод ран*/
	public void run(){
		this.car.setName(this.car.getName()+counter);
		counter++;
		if ("Thread-0".equals(Thread.currentThread().getName())) 
			Thread.currentThread().setPriority(10);
		try{System.out.println(Thread.currentThread().getName()+" started");
			while( (car.getX() < component.getBounds().getWidth())){
					car.move(component.getBounds(),5);
					component.repaint();	
					Thread.sleep(SLEEP);	
				}
		}
		catch(InterruptedException e){return;}
		System.out.println(Thread.currentThread().getName()+" finished  x= "+car.getX()+" y= "+car.getY());
			first.setVisible(true);
			if (flag) {
				first.setText("Победил "+car.getName());
				flag=false;
				counter = 1;
		}
	}
}
