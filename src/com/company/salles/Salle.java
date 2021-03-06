package com.company.salles;

import com.company.Creneau;

import java.util.*;


/**
 *
 */
public class Salle {
    private int numero;
    private static int numeroSalle;
    private String nom;
    private int nbDePlace;
    private int tarifPlace;
    private double tarifReduit ;
    private Map<Integer, Set<Creneau>> lesCreneauxOcupes;


    /**
     * @param nom
     * @param nbDePlace
     * @param tarifPlace
     */
    public Salle(String nom,int nbDePlace,int tarifPlace){
        numeroSalle+=10;
        this.numero=numeroSalle;
        this.nom=nom;
        this.nbDePlace=nbDePlace;
        this.tarifPlace=tarifPlace;
        this.lesCreneauxOcupes = new TreeMap<>();
        this.tarifReduit=tarifPlace-(tarifPlace*0.60);

    }

    /**
     * @param c
     * @return
     */
    public boolean estDisponible(Creneau c){
        Set<Creneau> lesCreneaux = lesCreneauxOcupes.get(c.getJourDeLaSemaine());
        if(lesCreneaux == null)
            return true;

        for ( Creneau creneau : lesCreneaux) {

            if ( c.getDebut().compareTo(creneau.getDebut()) >= 1) // c debut  > creneau debut
            {
                if(c.getDebut().compareTo(creneau.getFin()) <= -1) // c debut > creneau fin
                    return false;
            }
            else
            {
                if (c.getFin().compareTo(creneau.getDebut()) >= 1) // c fin < c debut
                    return false;
            }
        }
        return true;

    }

    /**
     * @param c
     * @return
     */
    public boolean ajouterCreneau(Creneau c){
        if (lesCreneauxOcupes.containsKey(c.getJourDeLaSemaine())) {

            return lesCreneauxOcupes.get(c.getJourDeLaSemaine()).add(c);
        }
        else {
            Set<Creneau> lesCreneau= new HashSet<>();
            if (estDisponible(c)){
                lesCreneau.add(c);
                lesCreneauxOcupes.put(c.getJourDeLaSemaine(),lesCreneau);
                return true;
            }
            return false;
        }
    }

    /**
     * @param jour
     * @return
     */
    public boolean pasDeCreneauDisponible(int jour){
        if(lesCreneauxOcupes.containsKey(jour)){
            return lesCreneauxOcupes.get(jour).isEmpty();
        }
        return false;
    }

    /**
     * supprimer le creneau en cas de non disponibilité
     */
    public void supprimerCreneauOccupes()
    {
        lesCreneauxOcupes.clear();
    }

    /**
     * @return numero de salle
     */
    public int getNumero() { return numero; }

    /**
     * @return nombre de place disponible
     */
    public int getNbDePlace(){return nbDePlace;}

    /**
     * @return tarif de place
     */
    public int getTarifPlace() {return tarifPlace;}

    /**
     * @return tarif des place reduite en cas de reduction
     */
    public double getTarifReduit() {return tarifReduit;}

    public String getNom() { return nom; }

    @Override
    public String toString()
    {
        return numero + " | " + nom + " | " + nbDePlace + " | " + tarifPlace + " | " + tarifReduit;
    }


}