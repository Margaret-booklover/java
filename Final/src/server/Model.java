package server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
 
public class Model implements Runnable {
  private Server server;
  private PrintWriter out;
  private Scanner in;
  private Socket cs = null;
  private static int count = 0;
  public Model(Socket socket, Server server) {
    try {
      count++;
      this.server = server;
      this.cs = socket;
      this.out = new PrintWriter(socket.getOutputStream());
      this.in = new Scanner(socket.getInputStream());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  public void run() {
    try {
      server.sendAll("Greet our new chat user!");
      server.sendAll("Number of users online: " + count);
      while (true) {
        if (in.hasNext()) {
        String msg = in.nextLine();
        if (msg.equals("break")) break;
        System.out.println(msg);
        server.sendAll(msg);
        server.sendAll("Number of users online: " + count);
      }
    }
  }
  catch (Exception e) {
    e.printStackTrace();
  }
  finally {
    this.close();
  }
}
  public void sendMsg(String msg) {
    try {
      out.println(msg);
      out.flush();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  public void close() {
    server.delete(this);
    count--;
    server.sendAll("Number of users online: " + count);
  }
}