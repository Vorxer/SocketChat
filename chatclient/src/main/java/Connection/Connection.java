package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
public class Connection{

    private String IP;
    private String ID;
    private Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    Integer port = 9876;
    public void connect(String IP, String ID, int port){

        this.IP = IP;

        try {

            //establish socket connection to server
            this.socket = new Socket(IP, 9876);

            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            receive(port);
            Thread.sleep(2000);
            oos.writeObject("connect " +  ID + " " + port);
            oos.close();

        } catch (Exception e){

            e.printStackTrace();

        }

        this.ID = ID;
        this.IP = IP;
    }

    public void list(){

    }

    public void receive(int port) throws Exception{

        Listner listner = new Listner(port);
        Thread thread = new Thread(listner);
        thread.start();

    }


    public void message(String command) throws Exception{

        this.socket = new Socket(IP, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending message to Socket Server");
        oos.writeObject(command);
        oos.close();

    }

    public void ping(){

    }

}
