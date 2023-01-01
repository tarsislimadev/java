import java.util.List;

import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.OutputStream;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;

class Request {
  public String firstLine = "";

  public Request(InputStream inputStream) throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(inputStreamReader);

    String s = "";

    this.firstLine = reader.readLine();

    while ((s = reader.readLine()) != "") {
      System.out.println("header: " + s);
    }

    while ((s = reader.readLine()) != "") {
      System.out.println("body: " + s);
    }

  }

}

class Response {
  InputStream inputStream = null;

  public Response(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public String getFirstLine() {
    return "HTTP/1.1 200 OK";
  }

  public String getContentType() {
    return "Content-Type: text/html";
  }

  public String getBodyString() {
    return "";
  }
}

class Main {

  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(80);
    Socket s = ss.accept();

    InputStream inputStream = s.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(inputStreamReader);

    OutputStream outputStream = s.getOutputStream();
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    BufferedWriter writer = new BufferedWriter(outputStreamWriter);

    String string = "";

    while ((string = reader.readLine()) != null) {
      System.out.println(string);
    }

    // Response response = new Response(inputStream);

    // writer.write(response.getFirstLine());
    // writer.newLine();
    
    // writer.write(response.getContentType());
    // writer.newLine();
    
    // writer.write("");
    // writer.newLine();
    
    // writer.write(response.getBodyString());
    // writer.newLine();

    // writer.flush();
  }
}
