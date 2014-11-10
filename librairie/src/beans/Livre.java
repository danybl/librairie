
package beans;

public class Livre {

    private int code;

    private float prix;

    private String title, author, cheminImage;

    private int quantite = 1;

    public Livre() {
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

    public int getQuantite() {
        return this.quantite;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public void setQuantite() {
        this.quantite++;
    }

    public void decreaseQte() {
        this.quantite--;
    }
}
