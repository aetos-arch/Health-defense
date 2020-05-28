package TD.Modele;

import TD.Modele.Personnage.InfecteSansSymp;
import TD.Modele.Tourelle.TourelleVitamine;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Partie {

	private static int avancement = 0;
	private static int delai = 0;

	private IntegerProperty scoreProperty;
	private IntegerProperty vagueProperty;
	private IntegerProperty moneyProperty;
	private Environnement env;
	private IntegerProperty pvProperty;

	public Partie() {
		this.scoreProperty = new SimpleIntegerProperty(0);
		this.vagueProperty = new SimpleIntegerProperty(1);
		this.moneyProperty = new SimpleIntegerProperty(0);
		this.env = new Environnement();
		this.pvProperty = new SimpleIntegerProperty(30);
	}

	public void lancerNiveau() {
		delai = 0;
        avancement = 0;
        this.setVague(this.getVague() + 1);
        int random = (int)(Math.random()*11)+11;
		this.env.ajouterPers(new InfecteSansSymp(0, random,this.env));
    }

	public void unTour() {
		if(delai%40 == 39)
			if (avancement < nombreEnnemi()) {
				int random = (int)(Math.random()*11)+11;
				this.env.ajouterPers(new InfecteSansSymp(0, random,this.env));
				avancement++;
			}
		delai ++;
		this.env.unTour();
	}
	
	public void ajouterTour(int x, int y) {
		this.env.ajouterTour(new TourelleVitamine(x, y, this.env));
	}

	public boolean estPerdu() {
		return this.getPV() <= 0;
	}
	
	public int nombreEnnemi() {
		return this.getVague()*4;
	}

	public void perdrePV(int n) {
		this.setPV(this.getPV()-n);
	}
	
	public void augmenterScore(int n) {
		this.setScore(this.getScore()+n);
	}
	
	public void augmenterMoney(int n) {
		this.setMoney(this.getMoney()+n);
	}
	
	public IntegerProperty moneyProperty() {
		return this.moneyProperty;
	}
	
	public int getMoney() {
		return this.moneyProperty.getValue();
	}
	
	public void setMoney(int n) {
		this.moneyProperty.set(n);
	}
	
	public IntegerProperty pvProperty() {
		return this.pvProperty;
	}
	
	public int getPV() {
		return this.pvProperty.getValue();
	}
	
	public void setPV(int n) {
		this.pvProperty.set(n);
	}
	
	public IntegerProperty vagueProperty() {
		return this.vagueProperty;
	}
	
	public int getVague() {
		return this.vagueProperty.getValue();
	}
	
	public void setVague(int n) {
		this.vagueProperty.set(n);
	}
	
	public IntegerProperty scoreProperty() {
		return this.scoreProperty;
	}
	
	public int getScore() {
		return this.scoreProperty.getValue();
	}
	
	public void setScore(int n) {
		this.scoreProperty.set(n);
	}

	public Environnement getEnv() {
		return this.env;
	}

	public boolean niveauFini() {
		return this.env.getPersos().isEmpty();
	}
}