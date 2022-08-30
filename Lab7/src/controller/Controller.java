package controller;
import java.awt.EventQueue;
import javax.swing.JFrame;
import view.ViewFrame;

/**
 * ���������� �� ����� ���� ��� ������ ��������� ������ � �������� ����� �� ������ ���� ����� �� �������. 
 * ������ ����� ������ ����� �� ����� ��������� (������ ��� �������������� ��������� �������) � � ��������� ������. 
 * ����� ���������������, ��� ������ �����-�� ������ ��������� �������. 
 * �������� ������ ���� ���� + ��������� ���� ��������� � ������ ����������. 
 * ���������� ������� ����������� �������� ����� � ����������� ����������� ��������������� �������.
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

