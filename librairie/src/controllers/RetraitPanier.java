
package controllers;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Livre;
import beans.Panier;

public class RetraitPanier extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String VUE = "/WEB-INF/jsp/afficherPanier.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request,
            response);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier == null) {
            panier = new Panier();
        }

        String number = request.getParameter("number");
        int numberValue = Integer.parseInt(number);

        supprimer(numberValue,
            panier);

        session.setAttribute("panier",
            panier);

        this.getServletContext().getRequestDispatcher(VUE).forward(request,
            response);

    }

    public static void supprimer(int number,
        Panier panier) {

        Livre livre = panier.findById(number);
        if(livre != null) {
            if(livre.getQuantite() == 1) {
                panier.remove(livre);
            } else {
                livre.decreaseQte();
            }
        }

    }

}
