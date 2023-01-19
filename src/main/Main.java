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

class Main {

  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(80);

    while (true) {
      Socket s = ss.accept();

      InputStream inputStream = s.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader reader = new BufferedReader(inputStreamReader);

      String string = "";

      while ((string = reader.readLine()) != null) {
        System.out.println(string);
      }

      OutputStream outputStream = s.getOutputStream();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
      BufferedWriter writer = new BufferedWriter(outputStreamWriter);

      Response response = new Response(inputStream);

      writer.write(Main.getFirstLine("200"));
      writer.newLine();
    
      writer.write(Main.getContentType("text/html"));
      writer.newLine();
    
      writer.write("");
      writer.newLine();
    
      writer.write(Main.getBodyString());
      writer.newLine();

      writer.flush();

      s.close();
    }

  }

  private static String getFirstLine(String status) {
    String message = Main.getStatusMessage(status);

    return "HTTP/1.1 " + status + " " + message;
  }

  private static String getStatusMessage(String status) {
    switch (status) {
      case "200": return "OK";
      case "404": return "NOT FOUND";
    }

    return "ERROR";
  }

  private static String getContentType(String type) {
    return "Content-Type: " + type;
  }

  private static String getBodyString() {
    return "{}";
  }
}
