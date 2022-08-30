package lab6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Fujitsu
 *Разработать простейший калькулятор без сохранения приоритета операций, но с памятью. 
 *Реализовать поддержку 4 арифметических действий, квадратный корень. -работает
 *Так же должна быть возможность стирания последней набранной цифры, - работает
 *запись текущего значения в память и вызов данных из памяти. - работает
 *Сгруппированные по блокам элементы должны масштабироваться при изменении размеров окна. - сделано
 */
public class program {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                CalculatorFrame frame = new CalculatorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
class CalculatorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public CalculatorFrame() {
        setTitle("Калькулятор");
        Calculator panel = new Calculator();
        add(panel);
        pack();
    }
}
class Calculator extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton screen;
    private JPanel calcPanel;
    private double current;
    private double store;
    private String lastPressed;
    private boolean start;
    public Calculator() {
        setLayout(new BorderLayout());
        current = 0;
        lastPressed = "=";
        start = true;
        store=0;
        screen = new JButton("0");
        screen.setEnabled(false);
        screen.setFont(new Font ("Italic", Font.ITALIC, 20));
        screen.setBackground(Color.white);
        add(screen, BorderLayout.NORTH);
        ActionListener digit = new DigitAction();
        ActionListener math = new MathAction();
        ActionListener special = new SpecialAction();
        String[] names={"Del","√","save","load","7","8","9","÷","4","5","6","*","1","2","3","–","0",".","=","+"};
        ActionListener[] types= {special,math,special,special,digit,digit,digit,math,digit,digit,digit,
        		math,digit,digit,digit,math,digit,digit,math,math};
        calcPanel = new JPanel();
        calcPanel.setLayout(new GridLayout(5, 4));
        for (int i=0;i<names.length;i++)  addPressButton(names[i],types[i]);
        add(calcPanel, BorderLayout.CENTER);
    }
    private void addPressButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.setFont(new Font ("Italic", Font.ITALIC, 20));
        button.setBackground(new Color(255,188,217));
        button.addActionListener(listener);
        calcPanel.add(button);
    }
    private class DigitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {
                screen.setText("");
                start = false;
            }
            screen.setText(screen.getText() + input);
        }
    }
    private class SpecialAction implements ActionListener{
    	public void actionPerformed(ActionEvent event) {
    		String input = event.getActionCommand();
            if (input.equals("save")) {
            	store=Double.parseDouble(screen.getText());  
            }
            else if (input.equals("load")) {
            	current=store;
            	screen.setText(store+"");
            }
            else if (input.equals("Del")) {
            	String buf=screen.getText();
            	if (buf.length()>1) current=Double.parseDouble(buf.substring(0,buf.length()-1));
            	else current=0;
            	screen.setText(buf.substring(0,buf.length()-1));
            }
    	}
    }
    private class MathAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if (start) {
                if (command.equals("")) {
                    screen.setText(command);
                    start = false;
                } else lastPressed = command;
            } else {
                operation(Double.parseDouble(screen.getText()));
                lastPressed = command;
                start = true;
            }
        }
    }
    public void operation(double x) {
    	if (lastPressed.equals("+")) current = current+x;
        else if (lastPressed.equals("–")) current = current-x;
        else if (lastPressed.equals("*")) current = current*x;
        else if (lastPressed.equals("÷")) current = current/x;        
        else if (lastPressed.equals("√")) current = Math.sqrt(x);
        else if (lastPressed.equals("=")) current = x;
        screen.setText(current+"");
        
        
        
    }
}