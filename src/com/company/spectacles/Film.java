package com.company.spectacles;

import java.util.List;

public class Film extends Spectacle{
    private static int numeroFilm = 100;
    String realisateur;
    int duree;
    public Film(String titre, String realisateur, int duree){
        super(numeroFilm++,titre);
        this.realisateur=realisateur;
        this.duree=duree;

    }


    public int getDuree() { return duree; }
    public String getRealisateur() { return realisateur; }

    @Override
    public String toString()
    {
        return super.toString() +  " | " + realisateur + " | " + duree;
    }

}
