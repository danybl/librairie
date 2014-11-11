package beans;

import java.io.Serializable;
public class Compteur implements Serializable{
	private static final long serialVersionUID = 1L;
	static int compte = 0;

	  public Compteur() { }

	  public static int getCompte() {
	    return compte;
	  }
	  
	 public static void incrementer(){
		 compte++;
	 }
	 public static void decrementer(){
		 compte--;
	 }
	  public void setCompte(int count) {
	    compte = count;
	  }
}
