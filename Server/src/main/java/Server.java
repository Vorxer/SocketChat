import Clients.Client;
import Service.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {


    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;
    private static ArrayList<Client> clients;

    public static void main(String args[]) throws Exception {
        //create the socket server object
        server = new ServerSocket(port);
        while (true) {

            System.out.println("Waiting for the client requests");

            Socket socket = server.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String message = (String) ois.readObject();
            System.out.println("Class Server Received Message: "+ message);
            System.out.println("Class Server Received Socket: "+ socket.getInetAddress().toString());

            //Each Service Thread Services a Request
            Service s = new Service(message, socket.getInetAddress().toString().substring(1));
            Thread t = new Thread(s);
            t.start();

        }
    }

}
