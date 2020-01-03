package com.company.seances;

import com.company.Creneau;
import com.company.salles.Salle;

public class SeanceFilm extends Seance{

    private Salle salleStandard;
    private int nbrePlaceVendueTR;

    public SeanceFilm(Creneau creneau)
    {
        super(creneau);
        nbrePlaceVendueTR = 0;
    }

    public void vendrePlacesTR(int nbre)
    {
        nbrePlaceVendueTR += nbre;
    }

    public int getPlaceDisponnible()
    {
        return salleStandard.getNbDePlace() - getNbrePlaceVendueTN() - nbrePlaceVendueTR;
    }


}