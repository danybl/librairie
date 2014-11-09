
package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Panier;

public class RetraitPanier extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        response.setContentType(CONTENT_TYPE);
        try(
            PrintWriter out = response.getWriter();) {

            HttpSession session = request.getSession();
            Panier panier = (Panier) session.getAttribute("panier");
            if(panier == null) {
                panier = new Panier();
            }

            getServletContext();
            try {
                int idLivre = Integer.parseInt(request.getParameter("idLivre"));
                if(idLivre != 0) {
                    supprimer(idLivre,
                        panier);
                } else {
                    panier.removeAll();
                }
                session.setAttribute("panier",
                    panier);

                request.getRequestDispatcher("/AfficherPanier").forward(request,
                    response);
            } catch(Exception e) {
                response.sendRedirect("http://a.dilcdn.com/bl/wp-content/uploads/sites/8/babyzone/2013/04/baby-boy-crying-photo-420x420-ts-56570356.jpg");
                out.println(e.toString());
            }

        }

    }

    public static void supprimer(int idLivre,
        Panier p) {

        Livre l = p.findById(idLivre);
        if(l != null) {
            if(l.getQte() == 1) {
                p.remove(l);
            } else {
                l.decreaseQte();
            }
        }

    }

}