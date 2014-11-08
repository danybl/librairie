
package view;

import beans.Panier;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AjoutPanier extends HttpServlet {

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

            ServletContext cont = getServletContext();
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\"librairieCSS.css\" rel=\"stylesheet\" media=\"all\" type=\"text/css\">");
            out.println("<title>Librairie du coin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>LIBRAIRIE DU COIN <img src=\"cart.png\" name=\"image\" border=\"0\"></h1>");
			out.println("<div id=\"navigation\">");
            out.println("<ul>");
                out.println("<li><a href=\"#\">CONNEXION</a></li>");
                out.println("<li><a href=\"#\">DÉCONNEXION</a></li>");
                out.println("<li><a href=\"#\">MON COMPTE</a></li>");
                out.println("<li><a href=\"#\">CRÉER UN NOUVEAU COMPTE</a></li>");
                out.println("<li><a href=\"#\">PANIER</a></li>");
                out.println("<li><a href=\"#\">À PROPOS DE NOUS</a></li>");
                out.println("<li><a href=\"#\">CONTACTEZ-NOUS</a></li>");
                out.println("<li><a href=\"#\">AIDE ?</a></li>");
            out.println("</ul>");
        out.println("</div>");
            out.println("<div class=\"box\">");
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                try(
                    InputStream fXmlFile = cont.getResourceAsStream("/WEB-INF/librairie.xml");) {
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);

                    doc.getDocumentElement().normalize();
                    NodeList listLivre = doc.getElementsByTagName("book");
                    int idLivre = Integer.parseInt(request.getParameter("idLivre"));

                    Node livreAjout = null;
                    for(int i = 0 ; i < listLivre.getLength() ; i++) {
                        int number = Integer.parseInt(doc.getElementsByTagName("number").item(i).getTextContent());
                        if(number == idLivre) {
                            livreAjout = doc.getElementsByTagName("number").item(i).getParentNode();
                        }
                    }
                    Livre lExist = null;
                    for(Livre l : panier.getListeLivres()) {
                        if(l.getCode() == idLivre) {
                            lExist = l;
                        }
                    }
                    if(lExist != null) {
                        lExist.addQte();
                    } else {

                        if(livreAjout.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) livreAjout;

                            String srcImg = element.getElementsByTagName("image").item(0).getAttributes().getNamedItem("src").getTextContent();
                            int number = Integer.parseInt(element.getElementsByTagName("number").item(0).getTextContent());
                            String title = element.getElementsByTagName("title").item(0).getTextContent();
                            String author = element.getElementsByTagName("author").item(0).getTextContent();
                            float price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
                            Livre ajoutLivre = new Livre(number,
                                price,
                                title,
                                author,
                                srcImg);
                            panier.addLivre(ajoutLivre);
                            session.setAttribute("panier",
                                panier);
                        }
                    }

                    for(Livre l : panier.getListeLivres()) {
                        out.println("<div class=\"livre\">");
                        out.println("<span class=\"livre-image\">");
                        out.println("<a><img src=\""
                            + l.getCheminImage()
                            + "\" height=\"300\" width=\"180\" style=\"width:80%\"></img></a>");
                        out.println("</span>");
                        out.println("<span class=\"text\">");
                        out.println(l.code);
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
                            + l.getQte());
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

                }
            } catch(Exception e) {
                response.sendRedirect("http://a.dilcdn.com/bl/wp-content/uploads/sites/8/babyzone/2013/04/baby-boy-crying-photo-420x420-ts-56570356.jpg");
                out.println(e.toString());
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
}
