
package models;

import beans.Client;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inscrits {

    private static List<Client> listId = new ArrayList<>();

    private static long cleIdClient = 0;

    // private static Iterator iterator = listId.iterator();

    public static void ajouter(Client client) {
        client.setIdClient(getCleIdClient());
        listId.add(client);
        System.out.println("Ajout d'un client :"
            + client.getUsername());
    }

    public static int find(String username) {
        Iterator<Client> iterator = listId.iterator();
        boolean trouve = false;
        int index = -1;
        while(iterator.hasNext()
            && !trouve) {
            index++;
            Client client = iterator.next();
            if(client.getUsername().equals(username)) {
                trouve = true;
                return index;
            }

        }
        return -1;
    }

    public static Client connexion(String username,
        String password) {
        int indexClient = find(username);
        if(indexClient != -1) {
            if(listId.get(indexClient).getPassword().equals(password)) {
                return listId.get(indexClient);
            }
        }
        return null;
    }

    public static long getCleIdClient() {
        return ++cleIdClient;
    }

    public static void setCleIdClient(long cleIdClient) {
        Inscrits.cleIdClient = cleIdClient;
    }
}
