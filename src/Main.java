import network.NetworkClient;
import network.NetworkNode;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        if (args[0].equals("NetworkClient")) {
            String ip = "";
            int port = 0;
            int ident = 0;
            String command = null;
            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case "-gateway":
                        String[] address = args[++i].split(":");
                        ip = address[0];
                        port = Integer.parseInt(address[1]);
                        break;
                    case "-ident":
                        ident = Integer.parseInt(args[++i]);
                        break;
                    case "terminate":
                        command = "terminate";
                        break;
                    default:
                        if (command == null) {
                            command = args[i];
                        } else if (!"TERMINATE".equals(command)) {
                            command += " " + args[i];
                        }
                        break;
                }
            }
            new NetworkClient(ip, port, ident, command).connect();

        } else if (args[0].equals("NetworkNode")) {
            int tcpPort = 0;
            int ident = 0;
            String gatewayIP = null;
            int gatewayPort = 0;
            HashMap<String, Integer> ressources = new HashMap<>();
            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case "-gateway":
                        String[] address = args[++i].split(":");
                        gatewayIP = address[0];
                        gatewayPort = Integer.parseInt(address[1]);
                        break;
                    case "-ident":
                        ident = Integer.parseInt(args[++i]);
                        break;
                    case "-tcpport":
                        tcpPort = Integer.parseInt(args[++i]);
                        break;
                    default:
                        String[] arg = args[i].split(":");
                        ressources.put(arg[0] , Integer.parseInt(arg[1]));
                }
            }
            new NetworkNode(ident, tcpPort, gatewayIP, gatewayPort, ressources).startServer();
        }
    }
}
