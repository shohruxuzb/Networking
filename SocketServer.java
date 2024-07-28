package by.shohruh;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        try(var serverSocker =new ServerSocket(8081);
            var socket=serverSocker.accept();
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream());
            var scanner=new Scanner(System.in)) {
            var request =inputStream.readUTF();
            while(!request.equals("exit")) {
                System.out.println("Client:" + request);
                outputStream.writeUTF(scanner.nextLine());
                request=inputStream.readUTF();
            }
            }
    }
}
