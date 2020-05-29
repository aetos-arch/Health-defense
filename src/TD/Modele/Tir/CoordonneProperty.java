package TD.Modele.Tir;

import javafx.beans.property.SimpleDoubleProperty;

public interface CoordonneProperty {
    SimpleDoubleProperty getXProperty();

    SimpleDoubleProperty getYProperty();

    double getX();

    double getY();
}
