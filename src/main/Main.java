import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

class Response {
  private String getFirstLine() {
    return "HTTP/1.1 OK 200";
  }

  private String[] getHeaderLines() {
    return [];
  }

  private String getBodyString() {
    return "";
  }

}

class Main {

  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(80);
    Socket s = ss.accept();

    InputStreamReader in = new InputStreamReader(s.getInputStream());
    BufferedReader bf = new BufferedReader(in);
    System.out.println(bf.readLine());

    Response response = new Response()

    PrintWriter pr = new PrintWriter(s.getOutputStream());
    pr.println(response.getFirstLine());

    pr.println("");

    pr.println(response.getBodyString());

    pr.flush();
  }
}
