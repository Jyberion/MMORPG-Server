package client;

class ClientHandler extends Thread {

  private Socket client;
  
  public ClientHandler(Socket client) {
    this.client = client;
  }

  public void run() {
    try {
      BufferedReader in = new BufferedReader(
        new InputStreamReader(client.getInputStream()));
      PrintWriter out = new PrintWriter(
        new OutputStreamWriter(client.getOutputStream()));
      
      while (true) {
        String request = in.readLine();
        
        // Handle request and send response
        out.println(response);
        out.flush();  
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        client.close();  
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
