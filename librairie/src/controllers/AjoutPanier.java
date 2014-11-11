
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Livre;
import beans.Panier;

public class AjoutPanier extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String VUE = "/WEB-INF/jsp/afficherPanier.jsp";

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

        Livre livre = new Livre();
        String image = request.getParameter("image");
        String number = request.getParameter("number");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");

        int numberValue = Integer.parseInt(number);
        float priceValue = Float.parseFloat(price);

        Livre livreExiste = null;

        for(Livre unLivre : panier.getListeLivres()) {
            if(unLivre.getNumber() == numberValue) {
                livreExiste = unLivre;
            }
        }
        if(livreExiste != null) {
            livreExiste.setQuantite();
        } else {

            livre.setCheminImage(image);
            livre.setNumber(numberValue);
            livre.setTitle(title);
            livre.setAuthor(author);
            livre.setPrice(priceValue);
            livre.setQuantite();

            panier.addLivre(livre);
            session.setAttribute("panier",
                panier);
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request,
            response);

    }
}
