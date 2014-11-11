
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Panier;

public class Accueil extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String VUE = "/WEB-INF/jsp/accueil.jsp";

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        HttpSession session = request.getSession(true);
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier == null) {

            response.sendRedirect("connexion");

        }

        String message = (String) request.getAttribute("message");

        request.setAttribute("message",
            message);

        this.getServletContext().getRequestDispatcher(VUE).forward(request,
            response);
    }

}
