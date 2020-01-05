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

    /**
     * @param nbre
     */
    public void vendrePlacesTR(int nbre)
    {
        nbrePlaceVendueTR += nbre;
    }

    /**
     * @return nombre de place vendue
     */
    public int getNbrePlaceVendueTR() { return nbrePlaceVendueTR; }

    /**
     * @return tarif des place
     */
    public int getTarifPlace() {return salleStandard.getTarifPlace();}

    /**
     * @return tarif reduit si reduction
     */
    public double getTarifReduit(){return salleStandard.getTarifReduit();}

    public int getNumeroSalle() { return salleStandard.getNumero(); }

    public int getPlaceDisponnible()
    {
        return salleStandard.getNbDePlace() - getNbrePlaceVendueTN() - nbrePlaceVendueTR;
    }


}