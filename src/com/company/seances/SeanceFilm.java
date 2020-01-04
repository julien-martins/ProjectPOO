package com.company.seances;

import com.company.Creneau;
import com.company.salles.Salle;

public class SeanceFilm extends Seance{

    private Salle salleStandard;
    private int nbrePlaceVendueTR;

    public SeanceFilm(Salle salle, Creneau creneau)
    {
        super(creneau);
        salleStandard = salle;
        salleStandard.ajouterCreneau(creneau);
        nbrePlaceVendueTR = 0;
    }

    public void vendrePlacesTR(int nbre)
    {
        nbrePlaceVendueTR += nbre;
    }

    public int getNbrePlaceVendueTR() { return nbrePlaceVendueTR; }

    public int getTarifPlace() {return salleStandard.getTarifPlace();}
    public double getTarifReduit(){return salleStandard.getTarifReduit();}

    public int getNumeroSalle() { return salleStandard.getNumero(); }

    public int getPlaceDisponnible()
    {
        return salleStandard.getNbDePlace() - getNbrePlaceVendueTN() - nbrePlaceVendueTR;
    }


}