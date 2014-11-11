
package models;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import beans.Client;
import beans.Panier;

public class ConnexionForm {

    private static final String CHAMP_USER = "username";

    private static final String CHAMP_PASS = "password";

    private String resultat;

    private Client client;

    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return this.resultat;
    }

    public Map<String, String> getErreurs() {
        return this.erreurs;
    }

    public Client connecterClient(HttpServletRequest request) {

        String username = getValeurChamp(request,
            CHAMP_USER);
        String password = getValeurChamp(request,
            CHAMP_PASS);

        this.client = Inscrits.connexion(username,
            password);
        if(this.client == null) {
            setErreur("client",
                "Invalid username or password");
        } else {
            HttpSession session = request.getSession(true);
            //Long idSessionClient = (Long) session.getAttribute("idSession");
            // Panier panierClient = (Panier) session.getAttribute("panier");

            Long idSessionClient = new Long(this.client.getIdClient());
            session.setAttribute("idSession",
                idSessionClient);
            Panier panierClient = new Panier();
            session.setAttribute("panier",
                panierClient);

            System.out.println("Panier cree");
        }

        if(this.erreurs.isEmpty()) {
            this.resultat = "Succès de la connexion.";
        } else {
            this.resultat = "Échec de la connexion";
        }

        return this.client;
    }

    private void setErreur(String champ,
        String message) {
        this.erreurs.put(champ,
            message);
    }

    private static String getValeurChamp(HttpServletRequest request,
        String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if(valeur == null
            || valeur.trim().length() == 0) {
            return null;
        }
        return valeur.trim();
    }

}
