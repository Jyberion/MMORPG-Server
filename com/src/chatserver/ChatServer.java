// Server

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {

  private int port;
  private ClientHandlerPool handlerPool;
  private AuthenticationService authService;

  public ChatServer(int port) {
    this.port = port;
    this.handlerPool = new ClientHandlerPool();
    this.authService = new DatabaseAuthService();
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);
    while (true) {
      Socket client = serverSocket.accept();
      ClientHandler handler = new ClientHandler(client, this);
      handlerPool.execute(handler);
    }
  }

  public void broadcast(Message message) {
    // send message to all connected clients
  }

}

class ClientHandler implements Runnable {

  private Socket client;
  private ChatServer server;

  public ClientHandler(Socket client, ChatServer server) {
    this.client = client;
    this.server = server;
  }

  public void run() {
    // handle client 
  }

}

class ClientHandlerPool {

  private ExecutorService pool;

  ClientHandlerPool() {
    pool = Executors.newFixedThreadPool(10);  
  }

  public void execute(ClientHandler handler) {
    pool.execute(handler);
  }

}


// Auth 

public interface AuthenticationService {
  boolean authenticate(String username, String password);
}

public class DatabaseAuthService implements AuthenticationService {

  public boolean authenticate(String username, String password) {
    // check credentials against database
    return true;
  }

}

