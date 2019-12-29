package com.company.spectacles;

import java.util.List;

public class Film extends Spectacle{
    String realisateur;
    int duree;
    public Film(String realisateur, int duree,int numero, String titre, List<String> interpretes){
        super(numero,titre,interpretes);
        this.realisateur=realisateur;
        this.duree=duree;

    }


}
