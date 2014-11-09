
package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import beans.Client;

public class Inscrits {

    private static List<Client> listId = new ArrayList<>();

    private static long cleIdClient = 0;

    // private static Iterator iterator = listId.iterator();

    public static void ajouter(Client client) {
        client.setIdClient(getCleIdClient());
        listId.add(client);
        System.out.println("Ajout d'un client :"
            + client.getUsername());
        //ajouterBD(client);
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
    /*
        public static void ajouterBD(Client client,
            String context) {

            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(context);
                System.out.println("Context : "
                    + context);

                String idClient = client.getIdClient()
                    + "";
                String username = client.getUsername();
                String password = client.getPassword();
                String nom = client.getNom();
                String prenom = client.getPrenom();
                String adresse = client.getAdresse();

                Element nouvClient = doc.createElement("client");
                Element nodeId = doc.createElement("id");
                nodeId.setNodeValue(idClient);
                Element nodeUsername = doc.createElement("username");
                nodeUsername.setNodeValue(username);
                Element nodePassword = doc.createElement("password");
                nodePassword.setNodeValue(password);
                Element nodeNom = doc.createElement("nom");
                nodeNom.setNodeValue(nom);
                Element nodePrenom = doc.createElement("prenom");
                nodePrenom.setNodeValue(prenom);
                Element nodeAdresse = doc.createElement("adresse");
                nodeAdresse.setNodeValue(adresse);
                nouvClient.appendChild(nodeId);
                nouvClient.appendChild(nodeUsername);
                nouvClient.appendChild(nodePassword);
                nouvClient.appendChild(nodeNom);
                nouvClient.appendChild(nodePrenom);
                nouvClient.appendChild(nodeAdresse);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT,
                    "yes");

                //initialize StreamResult with File object to save to file
                StreamResult result = new StreamResult(new StringWriter());
                DOMSource source = new DOMSource(doc);
                transformer.transform(source,
                    result);

                result.getWriter().toString();
            } catch(

                IOException
                | ParserConfigurationException
                | SAXException
                | TransformerException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }

        }*/
}
