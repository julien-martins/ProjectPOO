package com.company.spectacles;

import com.company.Horaire;
import com.company.seances.Seance;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Spectacle {
    private int numero;
    private String titre;
    private List <String> interpretes;

    private SortedSet<Seance> seances;

    public Spectacle(int numero,String titre){
        this.numero=numero;
        this.titre=titre;

        interpretes= new ArrayList<>();
        seances = new TreeSet<>();

    }


    public Seance rechercherSeance(int jour, Horaire debut)
    {
        for(Seance seance : seances)
        {
            if(seance.getJour() == jour && seance.getDebutCreneau().equals(debut))
                return seance;
        }
        return null;
    }

    public List<Seance> rechercherSeance(int jour)
    {
        List<Seance> result = new ArrayList<>();
        for(Seance seance : seances)
        {
            if(seance.getJour().equals(jour))
                result.add(seance);
        }
        return result;
    }

    public boolean existeSeanceCeJour(int jour)
    {
        for (Seance s : seances) {
            if (s.getJour().equals(jour))
                return true;
        }
        return false;
    }

    public boolean ajouterInterprete(String interprete)
    {
        return interpretes.add(interprete);
    }

    public boolean ajouterSeance (Seance seance)
    {
        return seances.add(seance);
    }

    public SortedSet<Seance> lesSeances()
    {
        return seances;
    }
    /*
    public int getTauxMoyenRemplissage()
    {

        return -1;
    }

    public int getChiffreAffaire()
    {


        return -1;
    }
    */

    public int getNumero() { return numero; }
    public String getTitre() { return titre; }

    public String toString() {return numero + " | " + titre;}

}
