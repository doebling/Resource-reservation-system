package network;

import java.io.*;
import java.net.ServerSocket;
import java.util.*;

import network.client.Client;
import network.ressource.Ressource;

import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkNode {

    private final int identifier;                                           // value for Server
    private final int tcpPort;                                              // tcpPort where the Server is reachable
    private final String gatewayIP;                                         // the Ip of the gateway
    private final int gatewayPort;                                          // the port of the gateway

    private HashMap<String, Integer> ressources;                  // List of all Ressources of the servers
    private List<Client> clients = new ArrayList<>();


    public NetworkNode(int identifier, int tcpPort, String gatewayIP, int gatewayPort, HashMap<String, Integer> ressources) {
        this.identifier = identifier;
        this.tcpPort = tcpPort;
        this.gatewayIP = gatewayIP;
        this.gatewayPort = gatewayPort;
        this.ressources = ressources;
    }


    public void startServer() {                                                      // starts the server now
        ServerSocket server;
        try {
            System.out.println("Listening at port " + tcpPort);
            server = new ServerSocket(tcpPort);
            while (true) {
                Socket socket;
                try {
                    socket = server.accept();
                    System.out.println("New connection: " + socket.getInetAddress() + ":" + socket.getPort());
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream output = new PrintStream(socket.getOutputStream());
                    String message = input.readLine();
                    System.out.println(message);

                    boolean error = false;
                    StringBuilder response = new StringBuilder("ALLOCATED ");

                    Client client = new Client(Integer.parseInt(message.split(" ")[0]), new HashMap<>());
                    HashMap<String, Integer> availableRessources = ressources;
                    if(message.split(" ")[1].equals("terminate")){
                        output.println("TERMINATED");
                        output.flush();

                    }else {
                        for (int i = 1; i < message.split(" ").length; i++) {
                            String[] req = message.split(" ")[i].split(":");
                            String name = req[0];
                            int ressourceSize = Integer.parseInt(req[1]);

                            if (availableRessources.get(name) != null && availableRessources.get(name) >= ressourceSize) {
                                response.append(name).append(":").append(availableRessources.get(name)).append(" ");
                                availableRessources.put(name, (availableRessources.get(name) - ressourceSize));
                                client.getRessources().put(name, ressourceSize);
                            } else {
                                error = true;
                            }
                            if (message.split(" ").length == i && !error) {
                                clients.add(client);
                                this.ressources = availableRessources;
                            }
                        }
                        if (error) {
                            output.println("FAILED");
                        } else {
                            response.append(server.getLocalSocketAddress());
                            output.println(response);
                        }
                        output.flush();
                        output.close();
                        input.close();
                        socket.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
