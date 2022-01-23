package network.client;

import java.util.HashMap;

public class Client {
    public final int indentifier;
    public final HashMap<String , Integer> ressources;

    public Client(int indentifier, HashMap<String, Integer> ressources) {
        this.indentifier = indentifier;
        this.ressources = ressources;
    }

    public HashMap<String, Integer> getRessources() {
        return ressources;
    }

    public int getIndentifier() {
        return indentifier;
    }
}
