/*
 * Auteurs :
 * Dany Benoit-Lafond
 * Jaskaran Dhadda
 * David Gallego
 */

package view;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import beans.Panier;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AccueilOLD extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        this.getServletContext().getRequestDispatcher("/Accueil.jsp").forward(request,
            response);
    }

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        HttpSession session = request.getSession(true);
        Panier panier = (Panier) session.getAttribute("panier");
        if(panier == null) {

            response.sendRedirect("connexion.jsp");

        }
        response.setContentType(CONTENT_TYPE);
        ServletContext cont = getServletContext();
        try(
            PrintWriter out = response.getWriter();) {

            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"shortcut icon\" href=\"livre.ico\">");
            out.println("<link href=\"librairieCSS.css\" rel=\"stylesheet\" media=\"all\" type=\"text/css\">");
            out.println("<title>Librairie du coin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>LIBRAIRIE DU COIN <img src=\"livres.png\" name=\"image\" border=\"0\"></h1>");
            out.println("<div id=\"navigation\">");
            out.println("<ul>");
            out.println("<li><a href=\"/connexion.jsp\">CONNEXION</a></li>");
            out.println("<li><a href=\"\\deconnexion\">DÉCONNEXION</a></li>");
            out.println("<li><a href=\"#\">MON COMPTE</a></li>");
            out.println("<li><a href=\"/WEB-INF/jsp/inscription.jsp\">CRÉER UN NOUVEAU COMPTE</a></li>");
            out.println("<li><a href=\"/AjoutPanier\">PANIER</a></li>");
            out.println("<li><a href=\"#\">À PROPOS DE NOUS</a></li>");
            out.println("<li><a href=\"#\">CONTACTEZ-NOUS</a></li>");
            out.println("<li><a href=\"#\">AIDE ?</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<div class=\"connexion\"><a href=\"connexion.html\">Connexion</a></div>");
            out.println("<div class=\"connexion\"><a href=\"deconnexion\">Deconnexion</a></div>");
            out.println("<div class=\"box\">");

            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                try(
                    InputStream fXmlFile = cont.getResourceAsStream("/WEB-INF/librairie.xml");) {
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);

                    doc.getDocumentElement().normalize();
                    NodeList listLivre = doc.getElementsByTagName("book");
                    for(int i = 0 ; i < listLivre.getLength() ; i++) {
                        Node node = listLivre.item(i);
                        if(node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;

                            out.println("<div class=\"livre\">");

                            out.println("<span class=\"livre-image\">");
                            out.println("<a><img src=\""
                                + element.getElementsByTagName("image").item(0).getTextContent()
                                + "\" height=\"300\" width=\"180\" style=\"width:80%\"></img></a>");
                            out.println("</span>");
                            out.println("<span class=\"text\">");
                            out.println(element.getElementsByTagName("number").item(0).getTextContent());
                            out.println("</span>");
                            out.println("<span class=\"title\">");
                            out.println(element.getElementsByTagName("title").item(0).getTextContent());
                            out.println("</span>");
                            out.println("<span class=\"author\">");
                            out.println(element.getElementsByTagName("author").item(0).getTextContent());
                            out.println("</span>");
                            out.println("<span class=\"price\">");
                            out.println(element.getElementsByTagName("price").item(0).getTextContent());
                            out.println("</span>");
                            out.println("<form action=\"AjoutPanier\" method=\"post\">");
                            out.println("<span class=\"ajouter\">");

                            out.println("<input name=\"idLivre\" type=\"hidden\" value=\""
                                + element.getElementsByTagName("number").item(0).getTextContent()
                                + "\">");
                            out.println("<input type=\"submit\" value=\"Ajouter\" id=\"btn\">");
                            out.println("</span>");
                            out.println("</form>");

                            out.println("</div>");
                        }
                    }
                }
            } catch(Exception e) {
                response.sendRedirect("http://a.dilcdn.com/bl/wp-content/uploads/sites/8/babyzone/2013/04/baby-boy-crying-photo-420x420-ts-56570356.jpg");
                out.println(e.toString());
            }
            out.println("</div>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }
    }
}