package com.company;

import com.company.interfaces.IProgrammationSemaine;
import com.company.salles.Salle;
import com.company.salles.SalleTheatre;
import com.company.seances.Seance;
import com.company.seances.SeanceFilm;
import com.company.seances.SeanceTheatre;
import com.company.spectacles.Film;
import com.company.spectacles.PieceTheatre;
import com.company.spectacles.Spectacle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GestionProgrammationSemaine implements IProgrammationSemaine {

    private Map<Integer, Salle> lesSalles;
    private Map<Integer, SalleTheatre> lesSallesTheatre;
    private Map<Integer, Film> lesFilms;
    private Map<Integer, PieceTheatre> lesPieces;

    public GestionProgrammationSemaine()
    {
        lesSalles = new HashMap<>();
        lesSallesTheatre = new HashMap<>();
        lesFilms = new HashMap<>();
        lesPieces = new HashMap<>();

        // Creer 4 salles de theatres
        // Creer 4 salles standard
        Salle salle1 = new Salle( "salle 1", 20, 5 );
        Salle salle2 = new Salle( "salle 2", 20, 5 );
        Salle salle3 = new Salle( "salle 3", 20, 5 );
        Salle salle4 = new Salle( "salle 4", 20, 5 );

        SalleTheatre salleT1 = new SalleTheatre("salle Theatre 1", 10, 5, 10, 10);
        SalleTheatre salleT2 = new SalleTheatre("salle Theatre 2", 10, 5, 10, 10);
        SalleTheatre salleT3 = new SalleTheatre("salle Theatre 3", 10, 5, 10, 10);
        SalleTheatre salleT4 = new SalleTheatre("salle Theatre 4", 10, 5, 10, 10);

        lesSalles.put(salle1.getNumero(), salle1);
        lesSalles.put(salle2.getNumero(), salle2);
        lesSalles.put(salle3.getNumero(), salle3);
        lesSalles.put(salle4.getNumero(), salle4);

        lesSallesTheatre.put(salleT1.getNumero(), salleT1);
        lesSallesTheatre.put(salleT2.getNumero(), salleT2);
        lesSallesTheatre.put(salleT3.getNumero(), salleT3);
        lesSallesTheatre.put(salleT4.getNumero(), salleT4);

    }

    /**
     * Recherche un film existant ayant pour titre et pour réalisateur ceux passés en paramètre
     *
     * @param titre
     * @param realisateur
     * @return le film trouvé ou null si aucun film trouvé
     */
    @Override
    public Film rechercherFilm(String titre, String realisateur) {
        Collection<Film> collectionFilms = lesFilms.values();

        for(Film f : collectionFilms)
        {
            if(f.getTitre().equals(titre) && f.getRealisateur().equals(realisateur))
                return f;
        }

        return null;
    }

    /**
     * Recherche un film ayant pour titre et pour réalisateur ceux passés en paramètre.
     * Si aucun film trouvé, crée le film et l'ajoute sinon lève une exception
     *
     * @param titre
     * @param realisateur
     * @param duree
     * @throws IllegalArgumentException Le film existe déjà
     */
    @Override
    public void ajouterFilm(String titre, String realisateur, int duree) {
        Collection<Film> collectionFilms = lesFilms.values();

        for(Film f : collectionFilms)
        {
            if(f.getTitre().equals(titre) && f.getRealisateur().equals(realisateur))
                throw new IllegalArgumentException("Le Film existe deja");
        }
        Film film = new Film(titre, realisateur, duree);
        lesFilms.put(film.getNumero(), film);
    }

    /**
     * Ajoute l'interprète passé en paramètre au spectacle correspondant au paramètre numSpectacle s'il existe sinon lève une exception
     *
     * @param numSpectacle
     * @param interprete
     * @throws IllegalArgumentException Spectacle inexistant
     */
    @Override
    public void ajouterInterprete(int numSpectacle, String interprete) {

        if(lesFilms.containsKey(numSpectacle))
            lesFilms.get(numSpectacle).ajouterInterprete(interprete);
        else if(lesPieces.containsKey(numSpectacle))
            lesPieces.get(numSpectacle).ajouterInterprete(interprete);
        else
            throw new IllegalArgumentException("l'id ne correspond a aucun spectacle ! ");

    }

    /**
     * Recherche une pièce de théâtre existante ayant pour titre et pour metteur en scène ceux passés en paramètre
     *
     * @param titre
     * @param metteurEnScene
     * @return la pièce de théâtre trouvée ou null si aucune pièce trouvée
     */
    @Override
    public PieceTheatre rechercherPiece(String titre, String metteurEnScene) {

        Collection<PieceTheatre> collectionTheatre = lesPieces.values();

        for(PieceTheatre p : collectionTheatre)
        {
            if(p.getTitre().equals(titre) && p.getMetteurEnScene().equals(metteurEnScene))
                return p;
        }

        return null;
    }

    /**
     * Recherche une pièce de théâtre ayant pour titre et pour metteur en scène ceux passés en paramètre.
     * Si aucune pièce trouvée, crée la pièce et l'ajoute sinon lève une exception
     *
     * @param titre
     * @param metteurEnScene
     * @param nbEntractes
     * @throws IllegalArgumentException La pièce existe déjà
     */
    @Override
    public void ajouterPiece(String titre, String metteurEnScene, int nbEntractes) {
        Collection<PieceTheatre> collectionTheatre = lesPieces.values();

        for(PieceTheatre p : collectionTheatre)
        {
            if(p.getTitre().equals(titre) && p.getMetteurEnScene().equals(metteurEnScene))
                throw new IllegalArgumentException("La Piece existe deja");
        }
        PieceTheatre piece = new PieceTheatre(titre, metteurEnScene, nbEntractes);
        lesPieces.put(piece.getNumero(), piece);
    }

    /**
     * Crée et ajoute la séance au film correspondant à idFilm s'il existe
     * et si la salle est disponible sur le créneau défini par les paramètres jour et début et la durée du film.
     * Il faut que l'heure de l'horaire de fin calculée soit compris entre 0 et 23 et les minutes entre 0 et 59.
     * Lève une exception si aucune salle ne correspond ) idSalle
     * Ajoute également le créneau à la salle correspondante
     *
     * @param idFilm
     * @param jour
     * @param debut
     * @param idSalle
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Salle inexistante
     * @throws IllegalStateException    Créneau indisponible pour dans cette salle
     */
    @Override
    public void ajouterSeanceFilm(int idFilm, int jour, Horaire debut, int idSalle) {
        if(!lesFilms.containsKey(idFilm))
            throw new IllegalArgumentException("Film inexistant");
        if(!lesSalles.containsKey(idSalle))
            throw new IllegalArgumentException("Salle inexistante");
        int duree = lesFilms.get(idFilm).getDuree();
        Horaire fin = new Horaire( debut.getHeure() + duree / 60, debut.getMinute() + duree % 60);
        Creneau cr = new Creneau(jour, debut, fin);
        if(!lesSalles.get(idSalle).estDisponible(cr))
        {
            throw new IllegalStateException("Creneau indisponible");
        }
        Salle salle = lesSalles.get(idSalle);
        SeanceFilm seance = new SeanceFilm(salle, cr);
        lesFilms.get(idFilm).ajouterSeance(seance);
    }

    /**
     * Teste l'existence d'une séance pour la pièce de théâtre correspondant à idPiece si elle existe.
     *
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     */
    @Override
    public boolean existeSeanceCeJour(int idPiece, int jour) {

        if(!lesPieces.containsKey(idPiece))
            throw new IllegalArgumentException("Piece inexistante");

        return lesPieces.get(idPiece).existeSeanceCeJour(jour);
    }

    /**
     * Crée et ajoute la séance à la pièce correspondant à idPiece s'il existe
     * et s'il n'y a pas déjà un créneau défini pour cette salle ce jour là.
     * Pour toute les pièces, on définira un créneau d'une durée de 3h. Si en ajoutant 3 heures à l'horaire de début, on passe au jour suivant (h>=24) il faut ramener l'heure entre 0 et 23.
     * Lève une exception si aucune salle ne correspond à idSalle
     * Ajoute également le créneau à la salle
     *
     * @param idPiece
     * @param jour
     * @param debut
     * @param idSalle
     * @throws IllegalArgumentException Pièce inexistante
     * @throws IllegalArgumentException Salle inexistante
     * @throws IllegalStateException    Créneau indisponible pour dans cette salle
     */
    @Override
    public void ajouterSeanceTheatre(int idPiece, int jour, Horaire debut, int idSalle) {
        if(!lesPieces.containsKey(idPiece))
            throw new IllegalArgumentException("Piece inexistante");
        if(!lesSalles.containsKey(idSalle))
            throw new IllegalArgumentException("Salle inexistante");
        Horaire fin = new Horaire(debut.getHeure() + 3, debut.getMinute());
        // TODO: test horaire >= 23
        Creneau cr = new Creneau(jour, debut, fin);
        if(!lesSalles.get(idSalle).estDisponible(cr))
            throw new IllegalStateException("Creneau indisponnible pour dans cette salle");
        SalleTheatre salle = (SalleTheatre)lesSalles.get(idSalle);
        SeanceTheatre seance = new SeanceTheatre(salle, cr);
        lesPieces.get(idPiece).ajouterSeance(seance);
    }

    /**
     * Retourne le chiffre d'affaires du spectacle correspondant au numéro passé en paramètre s'il existe
     *
     * @param numSpectacle
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     */
    @Override
    public double chiffreAffaires(int numSpectacle) {

        double chiffreAffaire = 0;

        if(existeFilm(numSpectacle))
        {
            Film film = lesFilms.get(numSpectacle);

            for (Seance seance : film.lesSeances())
            {
                SeanceFilm seanceFilm = (SeanceFilm)seance;

                return seanceFilm.getNbrePlaceVendueTN() * seanceFilm.getTarifPlace()
                        + seanceFilm.getNbrePlaceVendueTR() * seanceFilm.getTarifReduit();

            }

        }
        else if(existePiece(numSpectacle))
        {
            PieceTheatre pieceTheatre = lesPieces.get(numSpectacle);

            for (Seance seance : pieceTheatre.lesSeances())
            {
                SeanceTheatre seanceTheatre = (SeanceTheatre)seance;

                return seanceTheatre.getNbrePlaceVendueTN() * seanceTheatre.getTarifPlace()
                        + seanceTheatre.getNbreFauteuilVendue() * seanceTheatre.getPrixFauteuil();

            }
        }
        else
            throw new IllegalArgumentException("Spectacle inexistant");

        return chiffreAffaire;
    }

    /**
     * Retourne le taux de remplissage du spectacle correspondant au numéro passé en paramètre s'il existe
     *
     * @param numSpectacle
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     */
    @Override
    public double getTauxRemplissage(int numSpectacle)
    {
        double totalPlace = 0;
        double totalPlaceVendue = 0;

        if(existeFilm(numSpectacle))
        {
            Film film = lesFilms.get(numSpectacle);



            for (Seance seance : film.lesSeances())
            {
                SeanceFilm seanceFilm = (SeanceFilm)seance;

                totalPlace += lesSalles.get(seanceFilm.getNumeroSalle()).getNbDePlace();
                totalPlaceVendue += seanceFilm.getNbrePlaceVendueTR();
                totalPlaceVendue += seanceFilm.getNbrePlaceVendueTN();
            }

        }
        else if(existePiece(numSpectacle))
        {
            Film film = lesFilms.get(numSpectacle);

            for(Seance seance : film.lesSeances())
            {
                SeanceTheatre seanceTheatre = (SeanceTheatre) seance;

                totalPlace += lesSalles.get(seanceTheatre.getNumeroSalle()).getNbDePlace();
                totalPlaceVendue += seanceTheatre.getNbrePlaceVendueTN();
                totalPlaceVendue += seanceTheatre.getNbreFauteuilVendue();
            }


        }
        else
            throw new IllegalArgumentException("Spectacle inexistant");

        return totalPlaceVendue / totalPlace * 100;
    }

    /**
     * Vend le nombre de places à tarif normal passé en paramètre pour le film correspondant à idFilm s'il existe
     * et pour la séance correspondant au jour et à l'horaire de début passés en paramètre à condition qu'il y ait assez de places disponibles
     *
     * @param idFilm
     * @param jour
     * @param debut
     * @param nbPlacesTN
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    @Override
    public void vendrePlaceFilmTN(int idFilm, int jour, Horaire debut, int nbPlacesTN) {
        if(!lesFilms.containsKey(idFilm))
            throw new IllegalArgumentException("Film inexistant");
        SeanceFilm seanceFilm = (SeanceFilm)lesFilms.get(idFilm).rechercherSeance(jour, debut);
        if(seanceFilm == null)
            throw new IllegalArgumentException("Seance inexistant");

        int placeDispo = seanceFilm.getPlaceDisponnible();
        if(placeDispo < nbPlacesTN)
            throw new IllegalArgumentException("Pas assez de places disponibles");

        seanceFilm.vendrePlacesTN(nbPlacesTN);
    }

    /**
     * Vend le nombre de places à tarif réduit passé en paramètre pour le film correspondant à idFilm s'il existe
     * et pour la séance correspondant au jour et à l'horaire de début passés en paramètre à condition qu'il y ait assez de places disponibles
     *
     * @param idFilm
     * @param jour
     * @param debut
     * @param nbPlacesTR
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    @Override
    public void vendrePlaceFilmTR(int idFilm, int jour, Horaire debut, int nbPlacesTR) {
        if(!lesFilms.containsKey(idFilm))
            throw new IllegalArgumentException("Film inexistant");
        SeanceFilm seance = (SeanceFilm)lesFilms.get(idFilm).rechercherSeance(jour, debut);
        if(seance == null)
            throw new IllegalArgumentException("Seance Inexistant");

        int placeDispo = seance.getPlaceDisponnible();
        if(placeDispo < nbPlacesTR)
            throw new IllegalArgumentException("Pas assez de places disponnibles");

        seance.vendrePlacesTR(nbPlacesTR);
    }

    /**
     * Vend le nombre de places à tarif normal passé en paramètre pour la pièce correspondant à idPiece s'elle existe
     * et pour la séance correspondant au jour passé en paramètre à condition qu'il y ait assez de places disponibles
     *
     * @param idPiece
     * @param jour
     * @param nbPlacesTN
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    @Override
    public void vendrePlacePieceTN(int idPiece, int jour, int nbPlacesTN) {
        if(!lesPieces.containsKey(idPiece))
            throw new IllegalArgumentException("Film inexistant");
        SeanceTheatre seance = (SeanceTheatre)lesPieces.get(idPiece).rechercherSeance(jour).get(0);
        if(seance == null)
            throw new IllegalArgumentException("Seance inexistant");

        int placeDispo = seance.getPlaceDisponnible();
        if(placeDispo < nbPlacesTN)
            throw new IllegalArgumentException("Pas assz de places disponnibles");

        seance.vendrePlacesTN(nbPlacesTN);

    }

    /**
     * Vend le nombre de places à tarif fauteuils passé en paramètre pour la pièce correspondant à idPiece s'elle existe
     * et pour la séance correspondant au jour passé en paramètre à condition qu'il y ait assez de places disponibles
     *
     * @param idPiece
     * @param jour
     * @param nbFauteuils
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    @Override
    public void vendrePlaceFauteuilPiece(int idPiece, int jour, int nbFauteuils) {
        if(!lesPieces.containsKey(idPiece))
            throw new IllegalArgumentException("Piece innexistant");
        SeanceTheatre seance = (SeanceTheatre)lesPieces.get(idPiece).rechercherSeance(jour).get(0);
        if(seance == null)
            throw new IllegalArgumentException("Seance inexistant");

        int placeDispo = seance.getFauteuilDisponnible();
        if(placeDispo < nbFauteuils)
            throw new IllegalArgumentException("Pas assez de places disponibles");

        seance.vendrePlacesFauteuil(nbFauteuils);

    }

    /**
     * @return les films sous forme d'une chaîne de caractères
     */
    @Override
    public String lesFilms()
    {
        String result = "";
        if(lesFilms.isEmpty())
            return null;
        for(Map.Entry<Integer, Film> entry : lesFilms.entrySet())
        {
            result += entry.getValue().toString();
            result += "\n";
        }

        return result;
    }


    /**
     * @return les pièces de théâtre sous forme d'une chaîne de caractères
     */
    @Override
    public String lesPieces()
    {
        String result = "";

        for(Map.Entry<Integer, PieceTheatre> entry : lesPieces.entrySet())
        {
            result += entry.getValue().toString();
            result += "\n";
        }

        return result;
    }

    /**
     * @return les salles de film sous forme d'une chaîne de caractères
     */
    @Override
    public String lesSallesFilm() {
        return null;
    }

    /**
     * @return les salles de théâtre sous forme d'une chaîne de caractères
     */
    @Override
    public String lesSallesTheatre() {
        return null;
    }

    /**
     * retourne les séances de la pièce de théâtre correspondant à idPiece si elle existe sinon lève une exception
     *
     * @param idPiece
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     */
    @Override
    public String lesSeancesTheatre(int idPiece) {

        if(!existePiece(idPiece))
            throw new IllegalArgumentException("Piece inexsistante !");

        PieceTheatre piece = lesPieces.get(idPiece);
        if(piece == null)
            return null;

        String result = "";

        for ( Seance seance : piece.lesSeances() )
        {
            SeanceTheatre seanceTheatre = (SeanceTheatre) seance;

            result += "Seance du " + seanceTheatre.getJour() + " " + seanceTheatre.getDebutCreneau().getHeure() + "h" + seanceTheatre.getDebutCreneau().getMinute()
                    + " " + seanceTheatre.getFinCreneau().getHeure() + "h" + seanceTheatre.getFinCreneau().getMinute();
            result += "\n Nombre de places vendues : " + seanceTheatre.getNbrePlaceVendueTN();
            result += "\n Nombre de places vendues au tarif fauteuil : " + seanceTheatre.getNbreFauteuilVendue();
            result += "\n En salle numero " + seanceTheatre.getNumeroSalle();
        }

        return result;
    }

    /**
     * retourne les séances du film correspondant à idFilm s'il existe sinon lève une exception
     *
     * @param idFilm
     * @return
     * @throws IllegalArgumentException Film inexistant
     */
    @Override
    public String lesSeancesFilm(int idFilm) {

        if(!existeFilm(idFilm))
            throw new IllegalArgumentException("Film inexsistant !");

        Film film = lesFilms.get(idFilm);
        if(film == null)
            return null;

        String result = "";

        for ( Seance seance : film.lesSeances() )
        {
            SeanceFilm seanceFilm = (SeanceFilm) seance;

            result += "Seance du " + seanceFilm.getJour() + " " + seanceFilm.getDebutCreneau().getHeure() + "h" + seanceFilm.getDebutCreneau().getMinute()
                    + " " + seanceFilm.getFinCreneau().getHeure() + "h" + seanceFilm.getFinCreneau().getMinute();
            result += "\n Nombre de places vendues: " + seanceFilm.getNbrePlaceVendueTN();
            result += "\n Nombre de places vendues au tarif reduit: " + seanceFilm.getNbrePlaceVendueTR();
            result += "\n En salle numero " + seanceFilm.getNumeroSalle();
        }

        return result;
    }

    /**
     * Retourne le nombre de places standard disponibles pour le spectacle correspondant au numéro passé en paramètre s'il existe
     * et à la séance correspondant au jour et à l'horaire de début passés en paramètre si elle existe
     *
     * @param numSpectacle
     * @param jour
     * @param heures
     * @param minutes
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     * @throws IllegalArgumentException Séance inexistante pour ce spectacle
     */
    @Override
    public int getNbPlacesDispo(int numSpectacle, int jour, int heures, int minutes) {
        if (lesFilms.containsKey(numSpectacle))
        {
            SeanceFilm seance = (SeanceFilm) lesFilms.get(numSpectacle).rechercherSeance(jour, new Horaire(heures, minutes));
            if (seance == null)
                throw new IllegalArgumentException("Seance inexistante pour ce spectacle");
            return seance.getPlaceDisponnible();
        }
        else if(lesPieces.containsKey(numSpectacle))
        {
            SeanceTheatre seance = (SeanceTheatre)lesPieces.get(numSpectacle).rechercherSeance(jour, new Horaire(heures, minutes));
            if(seance == null)
                throw new IllegalArgumentException("Seance inexistante pour ce spectacle");
            return seance.getPlaceDisponnible();
        }
        else
            throw new IllegalArgumentException("Spectacle inexsitant");
    }

    /**
     * @param idFilm
     * @return true si idFilm correspond à un film et false sinon
     */
    @Override
    public boolean existeFilm(int idFilm) {
        return lesFilms.containsKey(idFilm);
    }

    /**
     * @param idPiece
     * @return true si idPièce correspond à une pièce de théâtre et false sinon
     */
    @Override
    public boolean existePiece(int idPiece) {
        return lesPieces.containsKey(idPiece);
    }

    /**
     * Teste l'existance d'une séance pour le film dont l'idFilm est passé en paramètre s'il existe. Sinon lève une exception
     *
     * @param idFilm
     * @param jour
     * @param heures
     * @param minutes
     * @return true s'il existe une séance correspondant à un créneau défini par un jour et un horaire de début donné par heures et minutes et false sinon
     * @throws IllegalArgumentException Film inexistant
     */
    @Override
    public boolean existeSeanceFilm(int idFilm, int jour, int heures, int minutes) {
        if (!existeFilm(idFilm))
            throw new IllegalArgumentException("Film inexistant");
        SeanceFilm seance = (SeanceFilm) lesFilms.get(idFilm).rechercherSeance(jour, new Horaire(heures, minutes));
        if(seance == null)
            return false;
        return true;
    }

    /**
     * @param idSalle
     * @return true si idSalle correspond à une salle de film et false sinon
     */
    @Override
    public boolean existeSalleFilm(int idSalle) {

        Salle salle = lesSalles.get(idSalle);
        if(salle == null)
            return false;
        return true;
    }

    /**
     * @param idSalle
     * @return true si idSalle correspond à une salle de film et false sinon
     */
    @Override
    public boolean existeSalleTheatre(int idSalle) {
        SalleTheatre salleTheatre = (SalleTheatre)lesSalles.get(idSalle);
        if(salleTheatre == null)
            return false;
        return true;
    }

    /**
     * Retourne la durée du film correspondant au paramètre s'il existe
     *
     * @param idFilm
     * @return
     * @throws IllegalArgumentException Film inexistant
     */
    @Override
    public int dureeFilm(int idFilm) {
        if(!existeFilm(idFilm))
            throw new IllegalArgumentException("Film inexistant");
        return lesFilms.get(idFilm).getDuree();
    }

    /**
     * Teste la disponibilité de la salle dont l'idSalle est passé en paramètre si elle existe
     *
     * @param jour
     * @param debut
     * @param duree
     * @param idSalle
     * @return Retourne true si la salle dont l'idSalle est passé en paramètre est disponible au créneau passé en paramètre sinon retourne false
     * @throws IllegalArgumentException Salle inexistante
     */
    @Override
    public boolean salleStandardDisponible(int jour, Horaire debut, int duree, int idSalle) {
        Horaire fin = new Horaire(debut.getHeure() + duree / 60, debut.getMinute() + duree % 60);
        Creneau cr = new Creneau(jour, debut, fin);

        if(existeSalleFilm(idSalle))
        {
            if (lesSalles.get(idSalle).estDisponible(cr))
                return true;
            else
                return false;
        }
        else if(existeSalleTheatre(idSalle))
        {
            if(lesSallesTheatre.get(idSalle).estDisponible(cr))
                return true;
            else
                return false;
        }
        else
            throw new IllegalArgumentException("Salle inexistante");
    }

    /**
     * Supprime les films et les pièces de théâtre de la programmation en cours.
     * Il faut également supprimer les créneaux occupés de chaque salle
     */
    @Override
    public void reinitialiserProgrammation() {
        for (Map.Entry<Integer, Salle> entry : lesSalles.entrySet())
            entry.getValue().supprimerCreneauOccupes();
        for (Map.Entry<Integer, SalleTheatre> entry : lesSallesTheatre.entrySet())
            entry.getValue().supprimerCreneauOccupes();

        lesFilms.clear();
        lesPieces.clear();
    }

    /**
     * Retourne le nombre de places de type fauteuil disponibles pour la pièce correspondant à idPiece s'elle existe
     * et s'il existe une séance le jour passé en paramètre
     *
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     * @throws IllegalArgumentException Aucune séance ce jour;
     */
    @Override
    public int getNbFauteuilsDispo(int idPiece, int jour) {
        if(!existePiece(idPiece))
            throw new IllegalArgumentException("Piece inexistante");
        else if(!lesPieces.get(idPiece).existeSeanceCeJour(jour))
            throw new IllegalArgumentException("Aucune seance ce jour");
        else
        {
            SeanceTheatre seance = (SeanceTheatre)lesPieces.get(idPiece).rechercherSeance(jour).get(0);
            return seance.getFauteuilDisponnible();
        }

    }

    /**
     * Retourne le nombre de places standard disponibles pour la pièce correspondant à idPiece s'elle existe
     * et s'il existe une séance le jour passé en paramètre
     *
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     * @throws IllegalArgumentException Aucune séance ce jour;
     */
    @Override
    public int getNbPlacesDispo(int idPiece, int jour) {
        if(!existePiece(idPiece))
            throw new IllegalArgumentException("Piece inexistante");
        else if(!lesPieces.get(idPiece).existeSeanceCeJour(jour))
            throw new IllegalArgumentException("Aucune seance ce jour");
        else
        {
            SeanceTheatre seance = (SeanceTheatre)lesPieces.get(idPiece).rechercherSeance(jour).get(0);
            return seance.getPlaceDisponnible();
        }
    }

    /**
     * @param numSpectacle
     * @return true si le numéro du spectacle passé en paramètre correspond à un numéro de film et false sinon
     */
    @Override
    public boolean estUnFilm(int numSpectacle) {
        if(existeFilm(numSpectacle))
            return true;
        return false;
    }

    /**
     * @param numSpectacle
     * @return true si le numéro du spectacle passé en paramètre correspond à un numéro de pièce de théâtre et false sinon
     */
    @Override
    public boolean estUnePiece(int numSpectacle) {
        if(existePiece(numSpectacle))
            return true;
        return false;
    }

    /**
     * Retourne le spectacle correspondant au numéro passé en paramètre s'il existe et null sinon
     *
     * @param numSpectacle
     * @return
     */
    @Override
    public Spectacle getSpectacle(int numSpectacle) {
        if(estUnePiece(numSpectacle))
            return lesPieces.get(numSpectacle);
        else if(estUnFilm(numSpectacle))
            return lesFilms.get(numSpectacle);
        else
            return null;
    }
}
