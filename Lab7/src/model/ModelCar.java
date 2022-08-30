package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
/**
 * � ���� ������ ������������ ��������� � ���� ������ �������, � ����� �� ��������
 *
 */
public class ModelCar{
	private int x = 0;
	private int y = 0;
	private static final int length = 100;
	private static final int width = 50;
	private String name = "car-";
	private Color color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	/**����������� � �����������*/
	public ModelCar(int a, int b){
		x = a;
		y = b;
	}
	/** ������ �����*/
	public Color getColor(){
		return color;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/** ������������ ������� �� ��������� ����������*/
	public void move(Rectangle2D bounds, int c){
		x += Math.random()*c;
	}
	/**���������� ������������� ������� ������ � �����*/
	public Rectangle2D getShape(){
		return new Rectangle2D.Double(x, y, length, width);
	}
	/** ������ ���������� �*/
	public int getX(){
		return x + 100;
	}
	/** ������ ���������� �*/
	public int getY(){
		return y + 50;
	}
}
