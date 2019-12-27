package com.company;

public class Creneau {
    Horaire debut;
    Horaire fin;
    int jourDeLaSemaine;

    public Creneau(int jourDeLaSemaine, Horaire debut, Horaire fin) {
        this.debut = debut;
        this.fin = fin;
        this.jourDeLaSemaine = jourDeLaSemaine;
    }

    public Horaire getDebut() {
        return debut;
    }
    public Horaire getFin(){
        return fin;
    }
    public int getJourDeLaSemaine(){
        return jourDeLaSemaine;
    }

}