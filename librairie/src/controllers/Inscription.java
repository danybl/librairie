
package controllers;

import beans.Client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.InscriptionForm;

public class Inscription extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATT_USER = "utilisateur";

    public static final String ATT_FORM = "form";

    public static final String VUE_INSCRIPTION_SUCCESS = "/WEB-INF/jsp/confirmationInscription.jsp";

    public static final String VUE_INSCRIPTION_FAIL = "/WEB-INF/jsp/inscription.jsp";

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_FAIL).forward(request,
            response);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        /* Preparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.inscrireClient(request);

        /* Stockage du résultat et des messages d'erreur dans l'objet request. */
        request.setAttribute(ATT_FORM,
            form);
        request.setAttribute(ATT_USER,
            client);

        if(client == null) {
            /* Transmission de la paire d'objet request/response à notre JSP */
            this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_FAIL).forward(request,
                response);
        } else {

            this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_SUCCESS).forward(request,
                response);

        }
    }

}