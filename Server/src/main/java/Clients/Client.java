package Clients;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private String Name;
    private String IP;

    public String getName() {
        return Name;
    }

    public String getIP() {
        return IP;
    }

    private int Port;

    private Socket socket = null;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;

    public Client(String name, String IP, int port) throws Exception{
        this.Name = name;
        this.IP = IP;
        this.Port = port;

        this.socket = new Socket(IP,port);

        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject("This is server. I see your port.");

        objectOutputStream.close();


        System.out.println("New Client Created " + name + " " + IP+ ":" + port);
    }

    public void sendMessage(String message, String sender) throws Exception{
        System.out.println("Method SendMessage Initiated");
        this.socket = new Socket(IP,Port);
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject("Message from " + sender + ": " + message);
        objectOutputStream.close();
    }
}
