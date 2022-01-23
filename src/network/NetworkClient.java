package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkClient {

    private final String ip;                                                            // ip of the client
    private final int port;                                                             // port with which the client connects
    private final int identifier;                                                       // identifier of the server (nubmer of the server)
    private String command;


    public NetworkClient(String ip, int port, int identifier, String command) {
        this.ip = ip;
        this.port = port;
        this.identifier = identifier;
        this.command = command;
        System.out.println("IP: " + ip + " Port:" + port + " Ident:" + identifier + " CMD:" + command);                 // Message of the connection

    }
    
    
    public void connect(){                                                                                              //
        Socket netSocket;
        PrintWriter out;
        BufferedReader in;
        try {
            System.out.println("Connecting with: " + ip + " at port " + port);
            netSocket = new Socket(ip, port);
            out = new PrintWriter(netSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(netSocket.getInputStream()));
            System.out.println("Connected");

            if (!"TERMINATE".equals(command)) {
                command = identifier + " " + command;
            }
            System.out.println("Sending: " + command);
            out.println(command);
            // Read and print out the response
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

            // Terminate - close all the streams and the socket
            out.close();
            in.close();
            netSocket.close();
            System.exit(0);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + ip + ".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No connection with " + ip + ".");
            System.exit(1);
        }

    }
}
