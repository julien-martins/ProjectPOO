package com.company.salles;

public class SalleTheatre extends Salle {

    private int nbFauteuils;
    private int prixFauteuil;

    public SalleTheatre (String nom,int nbDePlace,int tarifPlace,int nbFauteuils,int prixFauteuil){
        super(nom,nbDePlace,tarifPlace);
        this.nbFauteuils=nbFauteuils;
        this.prixFauteuil=prixFauteuil;
    }

    public int getNbFauteuils(){ return nbFauteuils; }
    public int getPrixFauteuil() { return prixFauteuil; }

}
