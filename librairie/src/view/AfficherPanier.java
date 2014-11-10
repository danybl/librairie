
package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Livre;
import beans.Panier;

public class AfficherPanier extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier == null) {
            panier = new Panier();
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<link href=\"librairieCSS.css\" rel=\"stylesheet\" media=\"all\" type=\"text/css\">");
        out.println("<title>Librairie du coin</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>LIBRAIRIE DU COIN <img src=\"cart.png\" name=\"image\" border=\"0\"></h1>");
        out.println("<div class=\"box\">");
        for(Livre l : panier.getListeLivres()) {
            out.println("<div class=\"livre\">");
            out.println("<span class=\"livre-image\">");
            out.println("<a><img src=\""
                + l.getCheminImage()
                + "\" height=\"300\" width=\"180\" style=\"width:80%\"></img></a>");
            out.println("</span>");
            out.println("<span class=\"text\">");
            out.println(l.getCode());
            out.println("</span>");
            out.println("<span class=\"title\">");
            out.println(l.getTitle());
            out.println("</span>");
            out.println("<span class=\"author\">");
            out.println(l.getAuthor());
            out.println("</span>");
            out.println("<span class=\"price\">");
            out.println(l.getPrix());
            out.println("</span>");
            out.println("<span class=\"qte\">");
            out.println("Quantit&eacute: "
                + l.getQuantite());
            out.println("</span>");
            out.println("<form action=\"RetraitPanier\" method=\"post\">");
            out.println("<span class=\"ajouter\">");
            out.println("<input name=\"idLivre\" type=\"hidden\" value=\""
                + l.getCode()
                + "\">");
            out.println("<input type=\"submit\" value=\"Retirer\" id=\"btn\">");
            out.println("</span>");
            out.println("</form>");

            out.println("</div>");
        }
        out.println("</div>");
        out.println("<div class=\"bottom\">");
        out.println("<form action=\"RetraitPanier\" method=\"post\">");
        out.println("<span class=\"RemoveAll\">");
        out.println("<input name=\"idLivre\" type=\"hidden\" value=\""
            + 0
            + "\">");
        out.println("<input type=\"submit\" value=\"Vider panier\" id=\"btn\">");
        out.println("</span>");
        out.println("</form>");

        out.println("<form action=\"Accueil\" method=\"get\">");
        out.println("<input type=\"submit\" value=\"Accueil\" id=\"btn\">");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}
