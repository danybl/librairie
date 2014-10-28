
package view;

public class Livre {

    int code;

    float prix;

    String title, author, cheminImage;

    int qte = 1;

    public Livre(int code,
        float prix,
        String title,
        String author,
        String cheminImage) {
        super();
        this.code = code;
        this.title = title;
        this.author = author;
        this.prix = prix;
        this.cheminImage = cheminImage;
    }

    public int getCode() {
        return this.code;
    }

    public float getPrix() {
        return this.prix;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getCheminImage() {
        return this.cheminImage;
    }

    public void addQte() {
        this.qte++;
    }

    public void decreaseQte() {
        this.qte--;
    }

    public int getQte() {
        return this.qte;
    }

}
