package TD.Controleur;

import TD.Modele.Personnage.InfecteGrave;
import TD.Modele.Personnage.InfecteJogger;
import TD.Modele.Personnage.InfecteSansSymp;
import TD.Modele.Personnage.Personnage;
import TD.Vue.VuePers;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ListenerPers implements ListChangeListener<Personnage> {

	Pane paneEntite;
	HashMap<Personnage, VuePers> correspondance;
	Controleur c;

	public ListenerPers(Pane p, Controleur c) {
		this.paneEntite = p;
		this.correspondance = new HashMap<Personnage, VuePers>();
		this.c = c;
	}

	@Override
	public void onChanged(Change<? extends Personnage> change) {
		while(change.next()) {
			if(change.wasAdded()) {
				for(Personnage p: change.getAddedSubList()) {
					VuePers vP;
					if (p instanceof InfecteSansSymp) {
						vP = new VuePers(0);
						vP.translateXProperty().bind(p.getXProperty());
						vP.translateYProperty().bind(p.getYProperty());
						p.getSainProperty().addListener(e -> vP.changerSpriteSain());
						this.c.nbTour.addListener(e -> vP.changerSprite(this.c.nbTour.getValue()));
						this.paneEntite.getChildren().add(vP);
						this.correspondance.put(p, vP);
						p.getHotProprety().addListener(e -> vP.afficherHoT(p.getHotProprety().getValue(), paneEntite));
					}
					else if (p instanceof InfecteJogger) {
						vP = new VuePers(1);
						vP.translateXProperty().bind(p.getXProperty());
						vP.translateYProperty().bind(p.getYProperty());
						p.getSainProperty().addListener(e -> vP.changerSpriteSain());
						this.c.nbTour.addListener(e -> vP.changerSprite(this.c.nbTour.getValue()));
						this.paneEntite.getChildren().add(vP);
						this.correspondance.put(p, vP);
						p.getHotProprety().addListener(e -> vP.afficherHoT(p.getHotProprety().getValue(), paneEntite));
					}
					else if (p instanceof InfecteGrave) {
						vP = new VuePers(2);
						vP.translateXProperty().bind(p.getXProperty());
						vP.translateYProperty().bind(p.getYProperty());
						p.getSainProperty().addListener(e -> vP.changerSpriteSain());
						this.c.nbTour.addListener(e -> vP.changerSprite(this.c.nbTour.getValue()));
						this.paneEntite.getChildren().add(vP);
						this.correspondance.put(p, vP);
						p.getHotProprety().addListener(e -> vP.afficherHoT(p.getHotProprety().getValue(), paneEntite));
					}
				}	
			}
			else if(change.wasRemoved()) {
				for(Personnage p: change.getRemoved()) {
					if(p.estSain()) {
						if(p instanceof InfecteSansSymp) {
							this.c.getPartie().augmenterMoney(100);
							this.c.getPartie().augmenterScore(50);
						}
						else if(p instanceof InfecteJogger) {
							this.c.getPartie().augmenterMoney(200);
							this.c.getPartie().augmenterScore(100);
						}
						else if(p instanceof InfecteGrave) {
							this.c.getPartie().augmenterMoney(250);
							this.c.getPartie().augmenterScore(150);
						}
					}
					else {
						this.c.getPartie().perdrePV(1);
					}
					this.paneEntite.getChildren().remove(this.correspondance.get(p));
				}

			}
		}
	}

}
