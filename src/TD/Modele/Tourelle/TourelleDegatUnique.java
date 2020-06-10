package TD.Modele.Tourelle;

import TD.Modele.Environnement;
import TD.Modele.Personnage.Personnage;
import TD.Utilitaire.Position;

import java.util.ArrayList;

public abstract class TourelleDegatUnique extends Tourelle {

    public TourelleDegatUnique(int x, int y, Environnement env, int portee, int delai, int prix) {
        super(x, y, delai, env, portee, prix);
        this.portee = portee;
    }

    public abstract void agit();

    public Personnage viser() {
        // Si pas de persos à portée return null
        if (getListePersosAPortee().isEmpty())
            return null;

        ArrayList<Personnage> listeInfecteGrave = filtrerInfectesGrave(getListePersosAPortee());
        // Infecté grave à portée on vise eux en priorité
        if (!listeInfecteGrave.isEmpty())
            return getPersoLePlusProche(listeInfecteGrave);
        // Sinon les personnages pas infecté grave
        return getPersoLePlusProche(getListePersosAPortee());
    }

    public ArrayList<Personnage> listePersosAPortee() {
        ArrayList<Personnage> persosAPortee = new ArrayList<>();

        for (Personnage p : env.getPersos()) {
            Position positionPersoActuel = new Position(p.getX(), p.getY());
            if (this.getPosition().distance(positionPersoActuel) <= portee && !p.estSain() && p.estProtege().getValue()==0) {
                persosAPortee.add(p);
            }
        }
        return persosAPortee;
    }
        
    public Personnage getPersoLePlusProche(ArrayList<Personnage> listePersos) {
        Personnage persoPlusProche = getListePersosAPortee().get(0);

        for (Personnage p : listePersos) {
            Position positionPersoProche = new Position(persoPlusProche.getX(), persoPlusProche.getY());
            Position positionPersoActuel = new Position(p.getX(), p.getY());

            if (this.getPosition().distance(positionPersoProche) > this.getPosition().distance(positionPersoActuel)) {
                persoPlusProche = p;
            }
        }
        return persoPlusProche;
    }

    public int getPortee() {
        return portee;
    }
}