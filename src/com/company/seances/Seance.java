package com.company.seances;

import com.company.Creneau;
import com.company.Horaire;

public class Seance implements Comparable<Seance>{

    private Creneau creneau;

    private int nbrePlaceVendueTN;

    /**
     * @param creneau
     */
    public Seance(Creneau creneau)
    {
        nbrePlaceVendueTN = 0;
        this.creneau = creneau;
    }

    /**
     * @param nbre
     */
    public void vendrePlacesTN(int nbre)
    {
        this.nbrePlaceVendueTN += nbre;
    }

    /**
     * @return nombre de place vendu par les vendeur
     */
    public int getNbrePlaceVendueTN() { return nbrePlaceVendueTN;}

    /**
     * @return debut de la seance
     */
    public Horaire getDebutCreneau() { return creneau.getDebut(); }

    /**
     * @return fin de la seance
     */
    public Horaire getFinCreneau() { return creneau.getFin(); }

    /**
     * @return jour de la seance
     */
    public Integer getJour(){return creneau.getJourDeLaSemaine();}

    /**
     * @return creneau de la seance
     */
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
