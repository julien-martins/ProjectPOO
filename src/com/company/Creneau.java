package com.company;

public class Creneau {
    Horaire debut;
    Horaire fin;
    int jourDeLaSemaine;

    /**
     * @param jourDeLaSemaine
     * @param debut
     * @param fin
     */
    public Creneau(int jourDeLaSemaine, Horaire debut, Horaire fin) {
        this.debut = debut;
        this.fin = fin;
        this.jourDeLaSemaine = jourDeLaSemaine;
    }

    /**
     * @return horaire de debut
     */
    public Horaire getDebut() {
        return debut;
    }

    /**
     * @return horaire de fin
     */
    public Horaire getFin(){
        return fin;
    }

    /**
     * @return jour de la seance
     */
    public int getJourDeLaSemaine(){
        return jourDeLaSemaine;
    }

    @Override
    public boolean equals(Object o)
    {
        return jourDeLaSemaine == ((Creneau) o).getJourDeLaSemaine()
                && debut.equals(((Creneau) o).getDebut()) && fin.equals(((Creneau) o).getFin());
    }


}