package com.company.seances;

import com.company.Creneau;
import com.company.Horaire;

public class Seance implements Comparable<Seance>{

    private Creneau creneau;

    private int nbrePlaceVendueTN;

    public Seance(Creneau creneau)
    {
        nbrePlaceVendueTN = 0;
        this.creneau = creneau;
    }

    public void vendrePlacesTN(int nbre)
    {
        this.nbrePlaceVendueTN += nbre;
    }

    public int getNbrePlaceVendueTN() { return nbrePlaceVendueTN;}

    public Horaire getDebutCreneau() { return creneau.getDebut(); }
    public Horaire getFinCreneau() { return creneau.getFin(); }

    public Integer getJour(){return creneau.getJourDeLaSemaine();}

    public Creneau getCreneau() {
        return creneau;
    }

    @Override
    public boolean equals(Object o)
    {
        return creneau.equals( ((Seance)o).getCreneau() );
    }

    @Override
    public int compareTo(Seance seance) {
        if( getJour().equals(seance.getJour()) )
            return getDebutCreneau().compareTo(seance.getDebutCreneau());
        else
            return getJour().compareTo(seance.getJour());
    }
}
