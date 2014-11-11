
package beans;

public class Livre {

    private int number;

    private float price;

    private String title, author, cheminImage;

    private int quantite = 0;

    public Livre() {
    }

    public int getNumber() {
        return this.number;
    }

    public float getPrice() {
        return this.price;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(float price) {
        this.price = price;
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
