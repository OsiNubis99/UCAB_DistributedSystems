package src.servers;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import src.Sockets;

public class Server {
  private Integer product;
  private Integer port;

  public Server(Integer port) {
    this.product = 0;
    this.port = port;
  }

  public void Run(Integer responseTime) {
    try {
      String req;
      String res = "";
      ServerSocket server = new ServerSocket(this.port);
      System.out.println("Listening in " + Integer.toString(this.port));
      DateTimeFormatter dtf =
          DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      while (true) {
        req = "";
        Socket socket = server.accept();
        DataInputStream dataIn = new DataInputStream(socket.getInputStream());
        req = dataIn.readUTF();
        switch (req) {
        case "I need!":
          if (this.product > 0) {
            System.out.println(dtf.format(now) + ": Good!");
            res = "Take one!";
            this.product -= 1;
          } else {
            System.out.println(dtf.format(now) + ": Bad");
            res = "I don't have bro :(";
          }
          DataOutputStream dataOut =
              new DataOutputStream(socket.getOutputStream());
          dataOut.writeUTF(res);
          dataOut.flush();
          dataOut.close();
          break;
        case "Take one!":
          System.out.println(dtf.format(now) + ": More!");
          this.product += 1;
          break;
        }
        socket.close();
        this.pause(responseTime);
      }
    } catch (IOException e) {
      System.out.println("Exception detected: " + e);
    }
  }

  private void pause(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }
  }
}
