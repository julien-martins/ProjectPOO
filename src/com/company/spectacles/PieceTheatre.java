package com.company.spectacles;

import java.util.List;

public class PieceTheatre extends Spectacle {
    String emmetteur;
    String entractes;

    public PieceTheatre(String emmetteur,String entractes,int numero, String titre, List<String> interpretes){
        super(numero,titre,interpretes);
        this.emmetteur=emmetteur;
        this.entractes=entractes;
    }

}
