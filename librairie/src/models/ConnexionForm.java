
package models;

import beans.Client;
import beans.Panier;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

        try {
            validationMotsDePasse(motDePasse,
                confirmation);
        } catch(Exception e) {
            setErreur(CHAMP_PASS,
                e.getMessage());
            setErreur(CHAMP_CONF,
                null);
        }

        try {
            validationUsername(username);
        } catch(Exception e) {
            setErreur(CHAMP_USERNAME,
                e.getMessage());
        }

        try {
            this.client = Inscrits.connexion(username,
                password);
        } catch(Exception e) {
            setErreur("client",
                "Ce client n'existe pas");

            HttpSession session = request.getSession();
            Long idSessionClient = (Long) session.getAttribute("idSession");
            Panier panierClient = (Panier) session.getAttribute("panier");

            if(idSessionClient == null
                || idSessionClient.longValue() != this.client.getIdClient()) {
                idSessionClient = new Long(this.client.getIdClient());
                session.setAttribute("idSession",
                    idSessionClient);
                panierClient = new Panier();
                session.setAttribute("panier",
                    panierClient);

            }
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
