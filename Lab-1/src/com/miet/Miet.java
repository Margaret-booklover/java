package com.miet;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
/**������� �����
 *  @version 1.10 04 Oct 1996 
 *  @author ��������� ������� */
public class Miet  {
	/**
	 * �������� �������
	 * �������� ���������, ������� ��������� ��������, �������� � ������ � �����������, � ���� � �����. 
	 * 1 ��� = 30,48 ��; 1 ���� = 2,54 ��. ���� �������� �� ����������� ������, ��������� 
	 * ����� ������ �� ���������� ������. ������, ��� 1 ��� ����� 12 ������.	 
	 * @param args ������� ��������� ������� main
	 * @param m  ����� � ������, �������� ������� ������
	 * @param cm  ����� � �����������, �������� ������� ������
	 * @param length ����� �����, ���������� � �����������, �������� ������� ������
	 * @throws str.isEmpty ������� ������ ������
	 * @throws NotANumber ��������� ���������� �� �������� ������
	 * @throws LessThanZero ����� ������ ����
	 * @throws IOException �������������� ����������
	 * @serialField f ����� ����� �����
	 * @serialField d ����� ����� ������ */
	public static void main(String[] args)  {
		scenarioController();
        
        }	
	
	public static void scenarioController() {
		inputDataController();
        model();
        view("final");
        
        }
	
    static Properties prop=new Properties();
    
  //������
    static void model() {
		double length;
		double m=Double.parseDouble(prop.getProperty("m"));
		double cm=Double.parseDouble(prop.getProperty("cm"));
		length=100*m+cm;
		int f, d;		
		if (length>2.54)
		{
			d=(int)Math.round(length/2.54);
			f=d/12;
			d=d-f*12;			
		}
		else 
			{
			f=0; 
			d=(int)(length/2.54);
			}
		prop.setProperty("final","����� "+m+" ������ "+ cm+" ����������� ����� "+ f +" ����� "+ d +" ������");
    }

    //View
    static void view(String key) {
    	
        if( prop.containsKey(key) ) {
            System.out.println(prop.get(key));
        } else {
            System.out.println(key);
        }
    }  
    
    //Controller
    static void inputDataController() {   	
		Scanner temp = new Scanner(System.in);
		view("���� �������� ������� �����, ������������ �������� �����!!! \n");
		try
		{
		view("����� \n");
		String str= temp.nextLine();		
		if (str.isEmpty())throw new java.io.IOException("str.isEmpty!");
		if (isNumber(str)==false) throw new java.io.IOException("NotANumber!");	
		prop.setProperty("m", str);
		if (Double.parseDouble(str)<0)throw new java.io.IOException("LessThanZero!");
		view("����������\n");
		str= temp.nextLine();
		if (str.isEmpty())throw new java.io.IOException("str.isEmpty!");
		if (isNumber(str)==false) throw new java.io.IOException("NotANumber!");
		prop.setProperty("cm", str);
		if (Double.parseDouble(str)<0)throw new java.io.IOException("LessThanZero!");
		}
		catch (java.io.IOException exc)
		{
			view("������� ����������   \""+exc.getMessage()+"\"!\n");
		}
		  
    } 
    
	/**�������� �������� �� ������ ������
	 * @param str ������, ������� ��������� �� ��, �������� �� ��� ������, 
	   @return true ���� ������ �������� ������
	   * false ���� ������ �� �������� ������*/
	public  static boolean isNumber(String str) {
		 if (str == null || str.isEmpty()) {
		        return false;
		    }
		    int commaCount = 0;
		    for (int i = 0; i < str.length(); i++) {
		        if (str.charAt(i) == '.') {
		            commaCount++;
		        }
		        if ((!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != '-') || commaCount > 1) {
		            return false;
		            
		        }
		    }
		    return true;
		    
	}



}

