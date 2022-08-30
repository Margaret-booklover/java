package server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
 
public class Server {
  static final int PORT = 20;
  private ArrayList<Model> users = new ArrayList<Model>();
  public Server() {
    Socket cs = null;
    ServerSocket ss = null;
    try {
      ss = new ServerSocket(PORT);
      System.out.println("Server started working");
      while (true) {
        cs = ss.accept();
        Model client = new Model(cs, this);
        users.add(client);
        new Thread(client).start();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void sendAll(String msg) {
    for (Model ob : users) {
      ob.sendMsg(msg);
    }
  }
  public void delete(Model temp) {
	  users.remove(temp);
  }
}