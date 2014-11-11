
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Client;
import models.ConnexionForm;

public class Connexion extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String ATT_CLIENT = "client";

    private static final String ATT_FORM = "form";

    private static final String VUE_CONNEXION_FAIL = "/WEB-INF/jsp/connexion.jsp";

    private static final String VUE_CONNEXION_SUCCESS = "/livreSpecialite";

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher(VUE_CONNEXION_FAIL).forward(request,
            response);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        /* Preparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.connecterClient(request);

        /* Stockage du résultat et des messages d'erreur dans l'objet request. */
        request.setAttribute(ATT_FORM,
            form);
        request.setAttribute(ATT_CLIENT,
            client);
        if(client == null) {
            /* Transmission de la paire d'objet request/response à notre JSP */
            this.getServletContext().getRequestDispatcher(VUE_CONNEXION_FAIL).forward(request,
                response);
        } else {

            this.getServletContext().getRequestDispatcher(VUE_CONNEXION_SUCCESS).forward(request,
                response);

        }
    }

}
