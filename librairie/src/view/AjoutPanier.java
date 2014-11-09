
package view;

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
import beans.Panier;
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
            try(
                InputStream fXmlFile = cont.getResourceAsStream("/WEB-INF/librairie.xml");) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
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
                request.getRequestDispatcher("/AfficherPanier").forward(request,
                    response);

            } catch(Exception e) {
                response.sendRedirect("http://a.dilcdn.com/bl/wp-content/uploads/sites/8/babyzone/2013/04/baby-boy-crying-photo-420x420-ts-56570356.jpg");
                out.println(e.toString());
            }

        }
    }
}
