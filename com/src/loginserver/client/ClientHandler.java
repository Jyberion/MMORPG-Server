import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

class ClientHandler implements Runnable {

  private Socket client;
  private BufferedReader in;
  private PrintWriter out;
  
  private ExecutorService executor; //thread pool
  
  public ClientHandler(Socket client, ExecutorService executor) {
    this.client = client;
    this.executor = executor;
  }

  public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
      
      while (true) {
        String request = in.readLine();
        Request req = parseRequest(request);
        
        // Validate request 
        if (!validateRequest(req)) {
          sendErrorResponse(out);
          continue;
        }

        // Handle request
        if (req.getType().equals("login")) {
          handleLogin(req);
        } else if (req.getType().equals("move")) {
          handleMove(req);
        }
        
        // Broadcast updates
        broadcastUpdate(req);
        
        // Send response
        Response resp = new Response("OK");
        out.println(resp.toJson());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeResources();
    }
  }

  private void closeResources() {
    try {
      in.close();
      out.close();
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

// Usage
ExecutorService executor = Executors.newCachedThreadPool();
executor.execute(new ClientHandler(client, executor));
