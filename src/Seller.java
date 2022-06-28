package src;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Seller {
  public static void main(String[] args) {
    try {
      Random random = new Random();
      ServerSocket server = new ServerSocket(Sockets.sellerPort);
      System.out.println("Listening in " +
                         Integer.toString(Sockets.sellerPort));
      while (true) {
        Socket socket = server.accept();
        if (random.nextBoolean()) {
          CallServer(Sockets.server1ip, Sockets.server1port);
          System.out.println("1");
          if (random.nextBoolean()) {
            CallServer(Sockets.server2ip, Sockets.server2port);
            System.out.println("2");
          } else {
            CallServer(Sockets.server3ip, Sockets.server3port);
            System.out.println("3");
          }
        } else {
          CallServer(Sockets.server2ip, Sockets.server2port);
          CallServer(Sockets.server3ip, Sockets.server3port);
          System.out.println("2 y 3");
        }
        socket.close();
        pause(5000);
      }
    } catch (IOException e) {
      System.out.println("Exception detected: " + e);
    }
  }

  private static void CallServer(String serverIp, Integer serverPort) {
    try {
      Socket socket = new Socket(serverIp, serverPort);
      DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
      dataOut.writeUTF("Take one!");
      dataOut.flush();
      dataOut.close();
      socket.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void pause(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }
  }
}