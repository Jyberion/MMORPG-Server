import java.net.*;
import java.io.*;

public class GameServer {

  private ServerSocket serverSocket;
  
  public GameServer() {
    try {
      serverSocket = new ServerSocket(8080); 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    while (true) {
      try {
        Socket client = serverSocket.accept();
        ClientHandler handler = new ClientHandler(client);
        handler.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void main(String[] args) {
    GameServer server = new GameServer();
    server.start();
  }

}

class ClientHandler extends Thread {

  private Socket client;
  
  public ClientHandler(Socket socket) {
    this.client = socket;
  }

  public void run() {
    // Handle client connection
  }

}
