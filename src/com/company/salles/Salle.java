package com.company.salles;

import com.company.Creneau;

import java.util.*;


public class Salle {
    private int numero;
    private static int numeroSalle;
    private String nom;
    private int nbDePlace;
    private double tarifPlace;
    private double tarifReduit ;
    private Map<Integer, Set<Creneau>> lesCreneauxOcupes;


    public Salle(String nom,int nbDePlace,double tarifPlace){
        numeroSalle+=10;
        this.numero=numeroSalle;
        this.nom=nom;
        this.nbDePlace=nbDePlace;
        this.tarifPlace=tarifPlace;
        this.lesCreneauxOcupes = new TreeMap<>();
        this.tarifReduit=tarifPlace-(tarifPlace*0.60);

    }
    public boolean estDisponible(Creneau c){
        Set<Creneau> lesCreneaux = lesCreneauxOcupes.get(numero);
        for ( Creneau creneau :lesCreneaux) {
            int comparator=creneau.getDebut().compareTo(c.getFin()) ;
            int comparator2= creneau.getFin().compareTo(c.getDebut());
            if (comparator>=1 && comparator2<=-1){
                continue;
            }
            else {
                return false;
            }


        }
        return true;

    }
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
    public boolean pasDeCreneauDisponible(int jour){
        if(lesCreneauxOcupes.containsKey(jour)){
            return lesCreneauxOcupes.get(jour).isEmpty();
        }
        return false;
    }

    public void supprimerCreneauOccupes()
    {
        lesCreneauxOcupes.clear();
    }

    public int getNumero() { return numero; }

    public int getNbDePlace(){return nbDePlace;}

}