
package controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LivreSpecialite extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String message;

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        String specialite = "1000 SAUCES";

        this.message = "Ajourd'hui "
            + new Date()
            + ", notre specialite est : "
        + specialite;

        request.setAttribute("message",
            this.message);

        this.getServletContext().getRequestDispatcher("/accueil").forward(request,
            response);

    }
}
