package prototype;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.*;

/**
 * This class implements java socket client
 * @author pankaj
 *
 */
public class SSLProto {

    public static void DisabledMain(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        System.setProperty("javax.net.ssl.trustStore", "/home/SSL/CA/ClientKeyStore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "1234567");
        System.setProperty("javax.net.debug", "ssl:record");

        SocketFactory socketFactory = SSLSocketFactory.getDefault();
        SSLSocket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(int i=0; i<100;i++){
            //establish socket connection to

            socket = (SSLSocket)socketFactory.createSocket("192.168.1.10",9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos.writeObject("exit");
            else oos.writeObject(""+i);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
