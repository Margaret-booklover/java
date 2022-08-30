package controller;
import java.awt.EventQueue;
import javax.swing.JFrame;
import view.ViewFrame;

/**
 * Разместить на форме друг под другом несколько кнопок и устроить гонки от левого края формы до правого. 
 * Каждая гонка должна ехать со своей скоростью (каждый раз определяющейся случайным образом) и в отдельном потоке. 
 * Гонка останавливается, как только какая-то кнопка достигнет «финиша». 
 * Финалист меняет цвет фона + появления окна сообщения с именем победителя. 
 * Необходимо наличие возможности рестарта гонки с «правильным» завершением соответствующих потоков.
 * @author Fujitsu
 *
 */
public class Controller {
	static void controller(){
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame fr = new ViewFrame(); 
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fr.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		Controller.controller();
	}

}

