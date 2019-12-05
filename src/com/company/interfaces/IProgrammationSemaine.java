package com.company.interfaces;

import com.company.Horaire;
import com.company.spectacles.Film;
import com.company.spectacles.PieceTheatre;
import com.company.spectacles.Spectacle;

import java.util.Collection;
import java.util.List;

public interface IProgrammationSemaine {
    /**
     * Recherche un film existant ayant pour titre et pour r�alisateur ceux pass�s en param�tre
     * @param titre
     * @param realisateur
     * @return le film trouv� ou null si aucun film trouv�
     */
    public Film rechercherFilm (String titre, String realisateur);

    /**
     * Recherche un film ayant pour titre et pour r�alisateur ceux pass�s en param�tre.
     * Si aucun film trouv�, cr�e le film et l'ajoute sinon l�ve une exception
     * @param titre
     * @param realisateur
     * @param duree
     * @throws IllegalArgumentException Le film existe d�j�
     */
    public void ajouterFilm (String titre, String realisateur, int duree);

    /**
     * Ajoute l'interpr�te pass� en param�tre au spectacle correspondant au param�tre numSpectacle s'il existe sinon l�ve une exception
     * @param numSpectacle
     * @param interprete
     * @throws IllegalArgumentException Spectacle inexistant
     */
    public void ajouterInterprete( int numSpectacle, String interprete);
    /**
     * Recherche une pi�ce de th��tre existante ayant pour titre et pour metteur en sc�ne ceux pass�s en param�tre
     * @param titre
     * @param metteurEnScene
     * @return la pi�ce de th��tre trouv�e ou null si aucune pi�ce trouv�e
     */
    public PieceTheatre rechercherPiece (String titre, String metteurEnScene);
    /**
     * Recherche une pi�ce de th��tre ayant pour titre et pour metteur en sc�ne ceux pass�s en param�tre.
     * Si aucune pi�ce trouv�e, cr�e la pi�ce et l'ajoute sinon l�ve une exception
     * @param titre
     * @param metteurEnScene
     * @param nbEntractes
     * @throws IllegalArgumentException La pi�ce existe d�j�
     */
    public void ajouterPiece (String titre, String metteurEnScene, int nbEntractes);

    /**
     * Cr�e et ajoute la s�ance au film correspondant � idFilm s'il existe
     * et si la salle est disponible sur le cr�neau d�fini par les param�tres jour et d�but et la dur�e du film.
     * Il faut que l'heure de l'horaire de fin calcul�e soit compris entre 0 et 23 et les minutes entre 0 et 59.
     * L�ve une exception si aucune salle ne correspond ) idSalle
     * Ajoute �galement le cr�neau � la salle correspondante
     * @param idFilm
     * @param jour
     * @param debut
     * @param idSalle
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException Salle inexistante
     * @throws IllegalStateException Cr�neau indisponible pour dans cette salle
     */
    public void ajouterSeanceFilm(int idFilm, int jour, Horaire debut, int idSalle);

    /**
     * Teste l'existence d'une s�ance pour la pi�ce de th��tre correspondant � idPiece si elle existe.
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pi�ce inexistante
     */
    public boolean existeSeanceCeJour(int idPiece, int jour);
    /**
     * Cr�e et ajoute la s�ance � la pi�ce correspondant � idPiece s'il existe
     * et s'il n'y a pas d�j� un cr�neau d�fini pour cette salle ce jour l�.
     * Pour toute les pi�ces, on d�finira un cr�neau d'une dur�e de 3h. Si en ajoutant 3 heures � l'horaire de d�but, on passe au jour suivant (h>=24) il faut ramener l'heure entre 0 et 23.
     * L�ve une exception si aucune salle ne correspond � idSalle
     * Ajoute �galement le cr�neau � la salle
     * @param idPiece
     * @param jour
     * @param debut
     * @param idSalle
     * @throws IllegalArgumentException Pi�ce inexistante
     * @throws IllegalArgumentException Salle inexistante
     * @throws IllegalStateException Cr�neau indisponible pour dans cette salle
     */
    public void ajouterSeanceTheatre(int idPiece, int jour, Horaire debut, int idSalle);

    /**
     * Retourne le chiffre d'affaires du spectacle correspondant au num�ro pass� en param�tre s'il existe
     * @param numSpectacle
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     */
    public double chiffreAffaires(int numSpectacle);

    /**
     * Retourne le taux de remplissage du spectacle correspondant au num�ro pass� en param�tre s'il existe
     * @param numSpectacle
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     */
    public double getTauxRemplissage(int numSpectacle);


    /**
     * Vend le nombre de places � tarif normal pass� en param�tre pour le film correspondant � idFilm s'il existe
     * et pour la s�ance correspondant au jour et � l'horaire de d�but pass�s en param�tre � condition qu'il y ait assez de places disponibles
     * @param idFilm
     * @param jour
     * @param debut
     * @param nbPlacesTN
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException S�ance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */

    public void vendrePlaceFilmTN(int idFilm, int jour, Horaire debut, int nbPlacesTN) ;
    /**
     * Vend le nombre de places � tarif r�duit pass� en param�tre pour le film correspondant � idFilm s'il existe
     * et pour la s�ance correspondant au jour et � l'horaire de d�but pass�s en param�tre � condition qu'il y ait assez de places disponibles
     * @param idFilm
     * @param jour
     * @param debut
     * @param nbPlacesTR
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException S�ance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    public void vendrePlaceFilmTR(int idFilm, int jour, Horaire debut, int nbPlacesTR);
    /**
     * Vend le nombre de places � tarif normal pass� en param�tre pour la pi�ce correspondant � idPiece s'elle existe
     * et pour la s�ance correspondant au jour pass� en param�tre � condition qu'il y ait assez de places disponibles
     * @param idPiece
     * @param jour
     * @param nbPlacesTN
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException S�ance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    public void vendrePlacePieceTN(int idPiece, int jour, int nbPlacesTN);
    /**
     * Vend le nombre de places � tarif fauteuils pass� en param�tre pour la pi�ce correspondant � idPiece s'elle existe
     * et pour la s�ance correspondant au jour pass� en param�tre � condition qu'il y ait assez de places disponibles
     * @param idPiece
     * @param jour
     * @param nbFauteuils
     * @throws IllegalArgumentException Film inexistant
     * @throws IllegalArgumentException S�ance inexistant
     * @throws IllegalArgumentException Pas assez de places disponibles
     */
    public void vendrePlaceFauteuilPiece(int idPiece, int jour, int nbFauteuils);
    /**
     *
     * @return les films sous forme d'une cha�ne de caract�res
     */
    public String lesFilms ();
    /**
     *
     * @return les pi�ces de th��tre sous forme d'une cha�ne de caract�res
     */
    public String lesPieces();
    /**
     *
     * @return les salles de film sous forme d'une cha�ne de caract�res
     */
    public String lesSallesFilm();

    /**
     *
     * @return les salles de th��tre sous forme d'une cha�ne de caract�res
     */
    public String lesSallesTheatre();


    /**
     * retourne les s�ances de la pi�ce de th��tre correspondant � idPiece si elle existe sinon l�ve une exception
     * @param idPiece
     * @return
     * @throws IllegalArgumentException Pi�ce inexistante
     */

    public String lesSeancesTheatre (int idPiece);
    /**
     * retourne les s�ances du film correspondant � idFilm s'il existe sinon l�ve une exception
     * @param idFilm
     * @return
     * @throws IllegalArgumentException Film inexistant
     */

    public String lesSeancesFilm (int idFilm);
    /**
     * Retourne le nombre de places standard disponibles pour le spectacle correspondant au num�ro pass� en param�tre s'il existe
     * et � la s�ance correspondant au jour et � l'horaire de d�but pass�s en param�tre si elle existe
     * @param numSpectacle
     * @param jour
     * @param heures
     * @param minutes
     * @return
     * @throws IllegalArgumentException Spectacle inexistant
     * @throws IllegalArgumentException S�ance inexistante pour ce spectacle
     */
    public int getNbPlacesDispo(int numSpectacle, int jour, int heures, int minutes);
    /**
     * @param idFilm
     * @return true si idFilm correspond � un film et false sinon
     */
    public boolean existeFilm (int idFilm);

    /**
     * @param idPiece
     * @return true si idPi�ce correspond � une pi�ce de th��tre et false sinon
     */
    public boolean existePiece (int idPiece);

    /**
     * Teste l'existance d'une s�ance pour le film dont l'idFilm est pass� en param�tre s'il existe. Sinon l�ve une exception
     * @param idFilm
     * @param jour
     * @param heures
     * @param minutes
     * @return true s'il existe une s�ance correspondant � un cr�neau d�fini par un jour et un horaire de d�but donn� par heures et minutes et false sinon
     * @throws IllegalArgumentException Film inexistant
     */
    public boolean existeSeanceFilm (int idFilm, int jour, int heures, int minutes );

    /**
     * @param idSalle
     * @return true si idSalle correspond � une salle de film et false sinon
     */
    public boolean existeSalleFilm (int idSalle);
    /**
     * @param idSalle
     * @return true si idSalle correspond � une salle de film et false sinon
     */
    public boolean existeSalleTheatre (int idSalle);

    /**
     * Retourne la dur�e du film correspondant au param�tre s'il existe
     * @param idFilm
     * @return
     * @throws IllegalArgumentException Film inexistant
     */
    public int dureeFilm(int idFilm);

    /**
     * Teste la disponibilit� de la salle dont l'idSalle est pass� en param�tre si elle existe
     * @param jour
     * @param debut
     * @param duree
     * @param idSalle
     * @return Retourne true si la salle dont l'idSalle est pass� en param�tre est disponible au cr�neau pass� en param�tre sinon retourne false
     * @throws IllegalArgumentException Salle inexistante
     */
    public boolean salleStandardDisponible (int jour, Horaire debut, int duree, int idSalle);

    /**
     * Supprime les films et les pi�ces de th��tre de la programmation en cours.
     * Il faut �galement supprimer les cr�neaux occup�s de chaque salle
     */
    public void reinitialiserProgrammation();

    /**
     * Retourne le nombre de places de type fauteuil disponibles pour la pi�ce correspondant � idPiece s'elle existe
     * et s'il existe une s�ance le jour pass� en param�tre
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pi�ce inexistante
     * @throws IllegalArgumentException Aucune s�ance ce jour;
     */
    public int getNbFauteuilsDispo(int idPiece, int jour);

    /**
     * Retourne le nombre de places standard disponibles pour la pi�ce correspondant � idPiece s'elle existe
     * et s'il existe une s�ance le jour pass� en param�tre
     * @param idPiece
     * @param jour
     * @return
     * @throws IllegalArgumentException Pi�ce inexistante
     * @throws IllegalArgumentException Aucune s�ance ce jour;
     */
    public int getNbPlacesDispo(int idPiece, int jour);

    /**
     *
     * @param numSpectacle
     * @return true si le num�ro du spectacle pass� en param�tre correspond � un num�ro de film et false sinon
     */
    public boolean estUnFilm(int numSpectacle);
    /**
     *
     * @param numSpectacle
     * @return true si le num�ro du spectacle pass� en param�tre correspond � un num�ro de pi�ce de th��tre et false sinon
     */
    public boolean estUnePiece(int numSpectacle);
    /**
     * Retourne le spectacle correspondant au num�ro pass� en param�tre s'il existe et null sinon
     * @param numSpectacle
     * @return
     */
    public Spectacle getSpectacle(int numSpectacle);
}
