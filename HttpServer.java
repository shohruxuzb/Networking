package by.javaguru.HTTP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run(){
        try{
            ServerSocket serverSocket=new ServerSocket(port);
            var socket=serverSocket.accept();
            processSocket(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try(socket;
            var inputStream=new DataInputStream(socket.getInputStream());
            var outputStream=new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println(new String(inputStream.readNBytes(400)));

            byte[] body= Files.readAllBytes(Path.of("src/main/resources/news.html"));
            outputStream.write("""
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
