package com.company.seances;

import com.company.Creneau;
import com.company.salles.SalleTheatre;

public class SeanceTheatre extends Seance{

    private SalleTheatre salleTheatre;
    private int nbreFauteuilVendue;

    public SeanceTheatre(Creneau creneau)
    {
        super(creneau);
        nbreFauteuilVendue = 0;
    }

    public void vendrePlacesFauteuil(int nbre)
    {
        this.nbreFauteuilVendue += nbre;
    }

    public int getPlaceDisponnible(){return salleTheatre.getNbDePlace() - getNbrePlaceVendueTN(); }

    public int getFauteuilDisponnible()
    {
        return salleTheatre.getNbFauteuils() - nbreFauteuilVendue;
    }

}
