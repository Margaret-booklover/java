package com.miet;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
/**базовый класс
 *  @version 1.10 04 Oct 1996 
 *  @author Маргарита Петрова */
public class Miet  {
	/**
	 * основная функция
	 * Написать программу, которая переводит величину, заданную в метрах и сантиметрах, в футы и дюймы. 
	 * 1 фут = 30,48 см; 1 дюйм = 2,54 см. Если величина не переводится нацело, округлить 
	 * число дюймов до ближайшего целого. Учесть, что 1 фут равен 12 дюймам.	 
	 * @param args входные аргументы функции main
	 * @param m  длина в метрах, является дробным числом
	 * @param cm  длина в сантиметрах, является дробным числом
	 * @param length общая длина, выраженная в сантиметрах, является дробным числом
	 * @throws str.isEmpty Введена пустая строка
	 * @throws NotANumber Введенная информация не является числом
	 * @throws LessThanZero Число меньше ноля
	 * @throws IOException необработанное исключение
	 * @serialField f целое число футов
	 * @serialField d целое число дюймов */
	public static void main(String[] args)  {
		scenarioController();
        
        }	
	
	public static void scenarioController() {
		inputDataController();
        model();
        view("final");
        
        }
	
    static Properties prop=new Properties();
    
  //Модель
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
		prop.setProperty("final","Длина "+m+" метров "+ cm+" сантиметров равна "+ f +" футов "+ d +" дюймов");
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
		view("Если вводится дробное число, разделителем ЯВЛЯЕТСЯ ТОЧКА!!! \n");
		try
		{
		view("Метры \n");
		String str= temp.nextLine();		
		if (str.isEmpty())throw new java.io.IOException("str.isEmpty!");
		if (isNumber(str)==false) throw new java.io.IOException("NotANumber!");	
		prop.setProperty("m", str);
		if (Double.parseDouble(str)<0)throw new java.io.IOException("LessThanZero!");
		view("Сантиметры\n");
		str= temp.nextLine();
		if (str.isEmpty())throw new java.io.IOException("str.isEmpty!");
		if (isNumber(str)==false) throw new java.io.IOException("NotANumber!");
		prop.setProperty("cm", str);
		if (Double.parseDouble(str)<0)throw new java.io.IOException("LessThanZero!");
		}
		catch (java.io.IOException exc)
		{
			view("Вызвано исключение   \""+exc.getMessage()+"\"!\n");
		}
		  
    } 
    
	/**проверка является ли строка числом
	 * @param str строка, которую проверяем на то, является ли она числом, 
	   @return true если строка является числом
	   * false если строка не является числом*/
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

