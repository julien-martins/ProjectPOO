package com.company.seances;
import com.company.salles.Salle;

public class SalleTheatre extends Salle {
    int nbFauteuils;
    int prixFauteuil;
    public SalleTheatre (int numero,String nom,int nbDePlace,int tarifPlace,int nbFauteuils,int prixFauteuil){
        super(numero,nom,nbDePlace,tarifPlace);
        this.nbFauteuils=nbFauteuils;
        this.prixFauteuil=prixFauteuil;
    }


}
