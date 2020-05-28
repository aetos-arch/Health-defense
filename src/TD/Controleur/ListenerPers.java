package TD.Controleur;

import java.util.HashMap;

import TD.Modele.Personnage.InfecteSansSymp;
import TD.Modele.Personnage.Personnage;
import TD.Vue.VuePers;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListenerPers implements ListChangeListener<Personnage>{

	Pane panePers;
	HashMap<Personnage, VuePers> correspondance;
	Controleur c;
	
	public ListenerPers(Pane p, Controleur c) {
		this.panePers = p;
		this.correspondance = new HashMap<Personnage, VuePers>();
		this.c = c;
	}
	@Override
	public void onChanged(Change<? extends Personnage> c) {
		while(c.next()) {
			if(c.wasAdded()) {
				for(Personnage p: c.getAddedSubList()) {
					VuePers vP;
					if(p instanceof InfecteSansSymp) {
						vP = new VuePers();
						vP.translateXProperty().bind(p.getXProperty());
				    	vP.translateYProperty().bind(p.getYProperty());
				    	this.c.nbTour.addListener(e -> vP.changerSprite(this.c.nbTour.getValue()));
				    	this.panePers.getChildren().add(vP);
				    	this.correspondance.put(p, vP);
					}
				}	
			}
			else if(c.wasRemoved()) {
				for(Personnage p: c.getRemoved()) {
					this.panePers.getChildren().remove(this.correspondance.get(p));
				}
					
			}
		}	
	}

}