import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

class ServerClass {
  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(80);
    Socket s = ss.accept();

    InputStreamReader in = new InputStreamReader(s.getInputStream());
    BufferedReader bf = new BufferedReader(in);
    System.out.println(bf.readLine());

    PrintWriter pr = new PrintWriter(s.getOutputStream());
    pr.println("hello");
    pr.flush();
  }
}
