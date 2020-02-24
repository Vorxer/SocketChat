package Prototype;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;
import java.io.*;
import java.security.Security;
//import java.security.GeneralSecurityException;
//import java.security.cert.X509Certificate;

public class Proto2 {

    public static void disabledMain(String[] args) throws Exception {
        // reserve the security properties
        String reservedSSFacProvider =
                Security.getProperty("ssl.ServerSocketFactory.provider");

        try {
            Security.setProperty("ssl.ServerSocketFactory.provider", "oops");
            ServerSocketFactory ssocketFactory =
                    SSLServerSocketFactory.getDefault();
            SSLServerSocket sslServerSocket =
                    (SSLServerSocket)ssocketFactory.createServerSocket();
        } catch (Exception e) {
            if (!(e.getCause() instanceof ClassNotFoundException)) {
                throw e;
            }
            // get the expected exception
        } finally {
            // restore the security properties
            if (reservedSSFacProvider == null) {
                reservedSSFacProvider = "";
            }
            Security.setProperty("ssl.ServerSocketFactory.provider",
                    reservedSSFacProvider);
        }
    }

}