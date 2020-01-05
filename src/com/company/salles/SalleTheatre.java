package com.company.salles;

public class SalleTheatre extends Salle {

    private int nbFauteuils;
    private int prixFauteuil;

    /**
     * @param nom
     * @param nbDePlace
     * @param tarifPlace
     * @param nbFauteuils
     * @param prixFauteuil
     */
    public SalleTheatre (String nom,int nbDePlace,int tarifPlace,int nbFauteuils,int prixFauteuil){
        super(nom,nbDePlace,tarifPlace);
        this.nbFauteuils=nbFauteuils;
        this.prixFauteuil=prixFauteuil;
    }

    /**
     * @return nombre de fauteuil disponible
     */
    public int getNbFauteuils(){ return nbFauteuils; }
    public int getPrixFauteuil() { return prixFauteuil; }

    @Override
    public String toString()
    {
        return getNumero() + " | " + getNom() + " | " + getNbDePlace() + " | " + getTarifPlace() + " | " + nbFauteuils + " | " + prixFauteuil;
    }

}
