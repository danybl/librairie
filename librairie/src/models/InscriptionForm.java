
package models;

import beans.Client;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public final class InscriptionForm {
    private static final String CHAMP_EMAIL = "email";

    private static final String CHAMP_PASS = "motdepasse";

    private static final String CHAMP_CONF = "confirmation";

    private static final String CHAMP_USERNAME = "username";

    private String resultat;

    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return this.resultat;
    }

    public Map<String, String> getErreurs() {
        return this.erreurs;
    }

    public Client inscrireClient(HttpServletRequest request) {
        String email = getValeurChamp(request,
            CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request,
            CHAMP_PASS);
        String confirmation = getValeurChamp(request,
            CHAMP_CONF);
        String username = getValeurChamp(request,
            CHAMP_USERNAME);

        Client client = new Client();

        /* Récupération des champs du formulaire. */

        /* Validation du champ email. */
        try {
            validationEmail(email);
        } catch(Exception e) {
            setErreur(CHAMP_EMAIL,
                e.getMessage());
        }
        client.setEmail(email);

        /* Validation du champ mot de passe et confirmation. */
        try {
            validationMotsDePasse(motDePasse,
                confirmation);
        } catch(Exception e) {
            setErreur(CHAMP_PASS,
                e.getMessage());
            setErreur(CHAMP_CONF,
                null);
        }
        client.setPassword(motDePasse);

        /* Validation du champ nom. */
        try {
            validationUsername(username);
        } catch(Exception e) {
            setErreur(CHAMP_USERNAME,
                e.getMessage());
        }
        client.setUsername(username);

        /* Initialisation du résultat global de la validation. */
        if(this.erreurs.isEmpty()) {
            this.resultat = "Succès de l'inscription.";
            Inscrits.ajouter(client);
            return client;
        }
        this.resultat = "Échec de l'inscription";
        return null;

    }

    /**
     * Valide l'adresse mail saisie.
     */
    private static void validationEmail(String email) throws Exception {
        if(email != null) {
            if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    /**
     * Valide les mots de passe saisis.
     */
    private static void validationMotsDePasse(String motDePasse,
        String confirmation) throws Exception {
        if(motDePasse != null
            && confirmation != null) {
            if(!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if(motDePasse.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /**
     * Valide le nom d'utilisateur saisi.
     */
    private static void validationUsername(String username) throws Exception {
        if(username != null
            && username.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ,
        String message) {
        this.erreurs.put(champ,
            message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
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
