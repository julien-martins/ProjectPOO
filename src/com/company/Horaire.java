package com.company;



public class Horaire implements Comparable<Horaire> {
    private int minute;
    private int heure;
    private int total;


    /**
     * @param heure
     * @param minute
     */
    public Horaire (int heure,int minute){
        this.heure=heure;
        this.minute=minute;
        this.total= heure*60+minute;
    }
    public int getMinute(){
        return minute;
    }
    public int getHeure(){
        return heure;
    }
    public int getTotal(){
        return total;
    }
    public int compareTo(Horaire h ){
        return this. total-h.getTotal();
    }

    @Override
    public boolean equals(Object o)
    {
        return total == ((Horaire)o).getTotal();
    }

}
