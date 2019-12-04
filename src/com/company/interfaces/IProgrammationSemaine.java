package com.company.interfaces;

import com.company.Horaire;
import com.company.spectacles.Film;
import com.company.spectacles.PieceTheatre;
import com.company.spectacles.Spectacle;

import java.util.Collection;
import java.util.List;

public interface IProgrammationSemaine {
    /**
     * Recherche un film existant ayant pour titre et pour réalisateur ceux passés en paramètre
     * @param titre
     * @param realisateur
     * @return le film trouvé ou null si aucun film trouvé
     */
    public Film rechercherFilm (String titre, String realisateur);

    /**
     * Recherche un film ayant pour titre et pour réalisateur ceux passés en paramètre.
     * Si aucun film trouvé, crée le film et l'ajoute sinon lève une exception
     * @param titre
     * @param realisateur
     * @param duree
     * @throws IllegalArgumentException Le film existe déjà
     */
    public void ajouterFilm (String titre, String realisateur, int duree);

    /**
     * Ajoute l'interprète passé en paramètre au spectacle correspondant au paramètre numSpectacle s'il existe sinon lève une exception
     * @param numSpectacle
     * @param interprete
     * @throws IllegalArgumentException Spectacle inexistant
     */
    public void ajouterInterprete( int numSpectacle, String interprete);
    /**
     * Recherche une pièce de théâtre existante ayant pour titre et pour metteur en scène ceux passés en paramètre
     * @param titre
     * @param metteurEnScene
     * @return la pièce de théâtre trouvée ou null si aucune pièce trouvée
     */
    public PieceTheatre rechercherPiece (String titre, String metteurEnScene);
    /**
     * Recherche une pièce de théâtre ayant pour titre et pour metteur en scène ceux passés en paramètre.
     * Si aucune pièce trouvée, crée la pièce et l'ajoute sinon lève une exception
     * @param titre
     * @param metteurEnScene
     * @param nbEntractes
     * @throws IllegalArgumentException La pièce existe déjà
     */
    public void ajouterPiece (String titre, String metteurEnScene, int nbEntractes);

    /**
     * Crée et ajoute la séance au film correspondant à idFilm s'il existe
     * et si la salle est disponible sur le créneau défini par les paramètres jour et début et la durée du film.
     * Il faut que l'heure de l'horaire de fin calculée soit compris entre 0 et 23 et les minutes entre 0 et 59.
     * Lève une exception si aucune salle ne correspond ) idSalle
     * Ajoute également le créneau à la salle correspondante
     * @param idFilm
     * @param jour
     * @param debut
     * @param idSalle
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Salle inexistante
     * @throws IllegalStateException Créneau indisponible pour dans cette salle
     */
    public void ajouterSeanceFilm(int idFilm, int jour, Horaire debut, int idSalle);

    /**
     * Teste l'existence d'une séance pour la pièce de théâtre correspondant à idPiece si elle existe.
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     */
    public boolean existeSeanceCeJour(int idPiece, int jour);
    /**
     * Crée et ajoute la séance à la pièce correspondant à idPiece s'il existe
     * et s'il n'y a pas déjà un créneau défini pour cette salle ce jour là.
     * Pour toute les pièces, on définira un créneau d'une durée de 3h. Si en ajoutant 3 heures à l'horaire de début, on passe au jour suivant (h>=24) il faut ramener l'heure entre 0 et 23.
     * Lève une exception si aucune salle ne correspond à idSalle
     * Ajoute également le créneau à la salle
     * @param idPiece
     * @param jour
     * @param debut
     * @param idSalle
     * @throws IllegalArgumentException Pièce inexistante
     * @throws IllegalArgumentException Salle inexistante
     * @throws IllegalStateException Créneau indisponible pour dans cette salle
     */
    public void ajouterSeanceTheatre(int idPiece, int jour, Horaire debut, int idSalle);

    /**
     * Retourne le chiffre d'affaires du spectacle correspondant au numéro passé en paramètre s'il existe
     * @param numSpectacle
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     */
    public double chiffreAffaires(int numSpectacle);

    /**
     * Retourne le taux de remplissage du spectacle correspondant au numéro passé en paramètre s'il existe
     * @param numSpectacle
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     */
    public double getTauxRemplissage(int numSpectacle);


    /**
     * Vend le nombre de places à tarif normal passé en paramètre pour le film correspondant à idFilm s'il existe
     * et pour la séance correspondant au jour et à l'horaire de début passés en paramètre à condition qu'il y ait assez de places disponibles
     * @param idFilm
     * @param jour
     * @param debut
     * @param nbPlacesTN
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */

    public void vendrePlaceFilmTN(int idFilm, int jour, Horaire debut, int nbPlacesTN) ;
    /**
     * Vend le nombre de places à tarif réduit passé en paramètre pour le film correspondant à idFilm s'il existe
     * et pour la séance correspondant au jour et à l'horaire de début passés en paramètre à condition qu'il y ait assez de places disponibles
     * @param idFilm
     * @param jour
     * @param debut
     * @param nbPlacesTR
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    public void vendrePlaceFilmTR(int idFilm, int jour, Horaire debut, int nbPlacesTR);
    /**
     * Vend le nombre de places à tarif normal passé en paramètre pour la pièce correspondant à idPiece s'elle existe
     * et pour la séance correspondant au jour passé en paramètre à condition qu'il y ait assez de places disponibles
     * @param idPiece
     * @param jour
     * @param nbPlacesTN
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    public void vendrePlacePieceTN(int idPiece, int jour, int nbPlacesTN);
    /**
     * Vend le nombre de places à tarif fauteuils passé en paramètre pour la pièce correspondant à idPiece s'elle existe
     * et pour la séance correspondant au jour passé en paramètre à condition qu'il y ait assez de places disponibles
     * @param idPiece
     * @param jour
     * @param nbFauteuils
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Séance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    public void vendrePlaceFauteuilPiece(int idPiece, int jour, int nbFauteuils);
    /**
     *
     * @return les films sous forme d'une chaîne de caractères
     */
    public String lesFilms ();
    /**
     *
     * @return les pièces de théâtre sous forme d'une chaîne de caractères
     */
    public String lesPieces();
    /**
     *
     * @return les salles de film sous forme d'une chaîne de caractères
     */
    public String lesSallesFilm();

    /**
     *
     * @return les salles de théâtre sous forme d'une chaîne de caractères
     */
    public String lesSallesTheatre();


    /**
     * retourne les séances de la pièce de théâtre correspondant à idPiece si elle existe sinon lève une exception
     * @param idPiece
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     */

    public String lesSeancesTheatre (int idPiece);
    /**
     * retourne les séances du film correspondant à idFilm s'il existe sinon lève une exception
     * @param idFilm
     * @return
     * @throws IllegalArgumentException Film inexistant
     */

    public String lesSeancesFilm (int idFilm);
    /**
     * Retourne le nombre de places standard disponibles pour le spectacle correspondant au numéro passé en paramètre s'il existe
     * et à la séance correspondant au jour et à l'horaire de début passés en paramètre si elle existe
     * @param numSpectacle
     * @param jour
     * @param heures
     * @param minutes
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     * @throws IllegalArgumentException Séance inexistante pour ce spectacle
     */
    public int getNbPlacesDispo(int numSpectacle, int jour, int heures, int minutes);
    /**
     * @param idFilm
     * @return true si idFilm correspond à un film et false sinon
     */
    public boolean existeFilm (int idFilm);

    /**
     * @param idPiece
     * @return true si idPièce correspond à une pièce de théâtre et false sinon
     */
    public boolean existePiece (int idPiece);

    /**
     * Teste l'existance d'une séance pour le film dont l'idFilm est passé en paramètre s'il existe. Sinon lève une exception
     * @param idFilm
     * @param jour
     * @param heures
     * @param minutes
     * @return true s'il existe une séance correspondant à un créneau défini par un jour et un horaire de début donné par heures et minutes et false sinon
     * @throws IllegalArgumentException Film inexistant
     */
    public boolean existeSeanceFilm (int idFilm, int jour, int heures, int minutes );

    /**
     * @param idSalle
     * @return true si idSalle correspond à une salle de film et false sinon
     */
    public boolean existeSalleFilm (int idSalle);
    /**
     * @param idSalle
     * @return true si idSalle correspond à une salle de film et false sinon
     */
    public boolean existeSalleTheatre (int idSalle);

    /**
     * Retourne la durée du film correspondant au paramètre s'il existe
     * @param idFilm
     * @return
     * @throws IllegalArgumentException Film inexistant
     */
    public int dureeFilm(int idFilm);

    /**
     * Teste la disponibilité de la salle dont l'idSalle est passé en paramètre si elle existe
     * @param jour
     * @param debut
     * @param duree
     * @param idSalle
     * @return Retourne true si la salle dont l'idSalle est passé en paramètre est disponible au créneau passé en paramètre sinon retourne false
     * @throws IllegalArgumentException Salle inexistante
     */
    public boolean salleStandardDisponible (int jour, Horaire debut, int duree, int idSalle);

    /**
     * Supprime les films et les pièces de théâtre de la programmation en cours.
     * Il faut également supprimer les créneaux occupés de chaque salle
     */
    public void reinitialiserProgrammation();

    /**
     * Retourne le nombre de places de type fauteuil disponibles pour la pièce correspondant à idPiece s'elle existe
     * et s'il existe une séance le jour passé en paramètre
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     * @throws IllegalArgumentException Aucune séance ce jour;
     */
    public int getNbFauteuilsDispo(int idPiece, int jour);

    /**
     * Retourne le nombre de places standard disponibles pour la pièce correspondant à idPiece s'elle existe
     * et s'il existe une séance le jour passé en paramètre
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pièce inexistante
     * @throws IllegalArgumentException Aucune séance ce jour;
     */
    public int getNbPlacesDispo(int idPiece, int jour);

    /**
     *
     * @param numSpectacle
     * @return true si le numéro du spectacle passé en paramètre correspond à un numéro de film et false sinon
     */
    public boolean estUnFilm(int numSpectacle);
    /**
     *
     * @param numSpectacle
     * @return true si le numéro du spectacle passé en paramètre correspond à un numéro de pièce de théâtre et false sinon
     */
    public boolean estUnePiece(int numSpectacle);
    /**
     * Retourne le spectacle correspondant au numéro passé en paramètre s'il existe et null sinon
     * @param numSpectacle
     * @return
     */
    public Spectacle getSpectacle(int numSpectacle);
}
