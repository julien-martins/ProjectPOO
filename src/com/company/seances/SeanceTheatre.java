package com.company.seances;

import com.company.Creneau;
import com.company.salles.SalleTheatre;

public class SeanceTheatre extends Seance{

    private SalleTheatre salleTheatre;
    private int nbreFauteuilVendue;

    public SeanceTheatre(SalleTheatre salle, Creneau creneau)
    {
        super(creneau);
        salleTheatre = salle;
        salleTheatre.ajouterCreneau(creneau);
        nbreFauteuilVendue = 0;
    }

    public void vendrePlacesFauteuil(int nbre)
    {
        this.nbreFauteuilVendue += nbre;
    }

    public int getNbreFauteuilVendue(){return nbreFauteuilVendue;}

    public int getTarifPlace(){return salleTheatre.getTarifPlace();}

    public int getPrixFauteuil(){return salleTheatre.getPrixFauteuil();}

    public int getNumeroSalle(){return salleTheatre.getNumero();}

    public int getPlaceDisponnible(){return salleTheatre.getNbDePlace() - getNbrePlaceVendueTN(); }

    public int getFauteuilDisponnible()
    {
        return salleTheatre.getNbFauteuils() - nbreFauteuilVendue;
    }

}
