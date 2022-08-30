package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
/**
 * В этом классе определяется геометрия и цвет каждой машинки, а также ее движение
 *
 */
public class ModelCar{
	private int x = 0;
	private int y = 0;
	private static final int length = 100;
	private static final int width = 50;
	private String name = "car-";
	private Color color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	/**Конструктор с параметрами*/
	public ModelCar(int a, int b){
		x = a;
		y = b;
	}
	/** геттер цвета*/
	public Color getColor(){
		return color;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/** передвижение объекта на случайное расстояние*/
	public void move(Rectangle2D bounds, int c){
		x += Math.random()*c;
	}
	/**возвращает прямоугольник текущей ширины и длины*/
	public Rectangle2D getShape(){
		return new Rectangle2D.Double(x, y, length, width);
	}
	/** геттер координаты х*/
	public int getX(){
		return x + 100;
	}
	/** геттер координаты у*/
	public int getY(){
		return y + 50;
	}
}
