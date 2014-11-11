
package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Panier {

    private List<Livre> listeLivres = new ArrayList<>();

    //private Iterator iterator = listeLivres.iterator();

    public Panier() {
        super();
    }

    public Livre getLivres(int i) {
        return this.listeLivres.get(i);
    }

    public void addLivre(Livre livre) {
        this.listeLivres.add(livre);
    }

    public List<Livre> getListeLivres() {
        return this.listeLivres;
    }

    public void remove(int index) {
        this.listeLivres.remove(index);
    }

    public void remove(Livre l) {
        this.listeLivres.remove(l);
    }

    public Livre findById(int id) {
        Iterator<Livre> iterator = this.listeLivres.iterator();
        boolean trouve = false;
        while(iterator.hasNext()
            && !trouve) {
            Livre l = iterator.next();
            if(l.getNumber() == id) {
                trouve = true;
                return l;
            }
        }
        return null;
    }

    public void removeAll() {
        this.listeLivres.clear();
    }
}
