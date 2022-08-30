package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
 
public class Chat extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String host = "localhost";
	private static final int port = 20;
	private Socket cs;
	private Scanner in;
	private PrintWriter out;
	private JTextField msg;
	private JLabel name;
	private JTextArea chat;
	private String nick = "";
  
  public String getNick() {
    return this.nick;
  }
  public Chat() {
	  host = JOptionPane.showInputDialog(null, new String[] {"Server IP"});
	  while(nick.equals(""))
		  nick = JOptionPane.showInputDialog(null, new String[] {"Nickname"});
    try {
      cs = new Socket(host, port);
      in = new Scanner(cs.getInputStream());
      out = new PrintWriter(cs.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    setBounds(500, 200, 500, 400);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    chat = new JTextArea();
    chat.setEditable(false);
    JScrollPane dialog = new JScrollPane(chat);
    add(dialog, BorderLayout.CENTER);
    
    JLabel count = new JLabel("Сейчас онлайн: ");
    add(count, BorderLayout.NORTH);
    
    JPanel activeField = new JPanel(new BorderLayout());
    add(activeField, BorderLayout.SOUTH);
    JButton send = new JButton("Send");
    activeField.add(send, BorderLayout.EAST);
   
    msg = new JTextField();
    activeField.add(msg, BorderLayout.CENTER);
    name = new JLabel(nick);  
    activeField.add(name, BorderLayout.WEST);
    send.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!msg.getText().isEmpty()) 
          send();
      }
    });
    new Thread(new Runnable() {
      public void run() {
        try {
          while (true) {
            if (in.hasNext()) {
              String inbox = in.nextLine();
              if (inbox.indexOf("Number of users online: ") == 0) count.setText(inbox);
              else chat.append(inbox+"\r\n");
            }
          }
        } catch (Exception e) {}
      }
    }).start();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        try {         
          out.println(nick + " went out from chat!");   
          out.println("break");
          out.flush();
          out.close();
          in.close();
          cs.close();
        } catch (IOException exc) {}
      }
    });
    setVisible(true);
  }
  public void send() {
    String tempMsg = name.getText() + ": " + msg.getText();
    out.println(tempMsg);
    out.flush();
    msg.setText("");
  }
}
