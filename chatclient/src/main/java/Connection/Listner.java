package Connection;

import javax.net.SocketFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listner implements Runnable {


    private int Port;

    private static ServerSocket server;

    public Listner(int port){
        Port = port;
    }

    public void run() {
        try {

            //socket server port on which it will listen

            //create the socket server object
            server = new ServerSocket(Port);
            //keep listens indefinitely until receives 'exit' call or program terminates
            while (true) {
                System.out.println("Message Listener is active");
                //creating socket and waiting for client connection
                Socket socket = server.accept();
                //read from socket to ObjectInputStream object
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //convert ObjectInputStream object to String
                String message = (String) ois.readObject();
                System.out.println("Message Received: " + message);
                //create ObjectOutputStream object
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //close resources
                ois.close();
                oos.close();
                socket.close();
                //terminate the server if client sends exit request
            }
        }catch (Exception e){

        }

    }

}
