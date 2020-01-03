package com.company.spectacles;

import java.util.List;

public class PieceTheatre extends Spectacle {

    private static int numeroTheatre = 1000;

    private String metteurEnScene;
    private int nbEntractes;

    public PieceTheatre(String titre, String metteurEnScene,int nbEntractes){
        super(numeroTheatre++, titre);
        this.metteurEnScene=metteurEnScene;
        this.nbEntractes=nbEntractes;
    }

    public String getMetteurEnScene(){ return metteurEnScene; }
    public int getNbEntractes(){ return nbEntractes; }

}
