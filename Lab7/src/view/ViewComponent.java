package view;
import java.awt.*;

import javax.swing.JPanel;
import model.ModelCar;
/**
 * 
 * Этот класс отвечает за вывод на экран сообщения с именем победителя и смену цвета фона
 *
 */
public class ViewComponent extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final int wid = 450;
	private static final int hei = 250;
	private static Color color = Color.WHITE;
	private static ModelCar[] cars = new ModelCar[3];

	/** геттер цвета*/
	public Color getColor(){
		return color;
	}
	/** добавление объектов в массив*/
	public static void add(ModelCar a, ModelCar b, ModelCar c){
		//cars = new ModelCar[3];
		cars[0] = a;
		cars[1] = b;
		cars[2] = c;
	}
	/**Отрисовка поля и выбор фона*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (ModelCar b : cars){
			if (b != null){
				g2.setPaint(b.getColor());
				g2.fill(b.getShape());
				if (b.getX() >= wid){
					this.setBackground(b.getColor());
					color = b.getColor();
					g2.fill(b.getShape());
					cars = null;
				}
			}
		}
	}
	public Dimension getPreferredSize(){
		return new Dimension(wid, hei);
	}
}
