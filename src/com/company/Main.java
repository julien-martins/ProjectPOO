package com.company;

import com.company.salles.Salle;
import com.company.seances.SeanceFilm;

import java.awt.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static GestionProgrammationSemaine gestion;

    public static void main(String[] args)
    {
        scanner = new Scanner(System.in);
        gestion = new GestionProgrammationSemaine();

        boolean running = true;
        do {
            try {
                afficherMenu();

                int choice = inputValue("Faites votre choix:");

                if(!gestionMenu(choice))
                {
                    running = false;
                    break;
                }

                logMessage("Retour au menu ... ");
                Thread.sleep(1500);
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }

        } while (running);
        System.out.println("Arret en cours...");
    }

    private static void reinitialiserProgrammation()
    {
        gestion.reinitialiserProgrammation();
        logMessage("Programmation reinitialiser ...");
    }

    private static void ajouterFilm()
    {
        System.out.print("Veuillez saisir le titre du film: ");
        String titre = scanner.nextLine();

        System.out.print("Veuillez saisir le realisateur du film: ");
        String realisateur = scanner.nextLine();

        int duree = inputValue("Veuillez saisir la duree du film: ");

        gestion.ajouterFilm(titre, realisateur, duree);
        logMessage("Ajout d'un film ... ");
    }

    private static void ajouterPiece()
    {
        System.out.print("Veuillez saisir le titre de la piece: ");
        String titre = scanner.nextLine();

        System.out.print("Veuillez saisir le metteur en scene de la piece: ");
        String metteurEnScene = scanner.nextLine();

        int nbEntractes = inputValue("Veuillez saisir le nombre d'entractes de la piece: ");

        gestion.ajouterPiece(titre, metteurEnScene, nbEntractes);

        logMessage("Ajout d'une piece ... ");
    }

    private static void ajouterInterprete()
    {
        System.out.println(gestion.lesFilms());
        System.out.println(gestion.lesPieces());

        int id = inputValue("Veuillez saisir l'id du spectacle: ");

        System.out.print("Veuillez saisir l'interpreteur du spectacle: ");
        String interprete = scanner.next();

        gestion.ajouterInterprete(id, interprete);

        logMessage("Interprete ajoute ... ");
    }

    private static void ajouterSeanceFilm()
    {
        System.out.println(gestion.lesFilms());

        int id = inputValue("Veuillez saisir l'id d'un film:");

        int jour = inputValue("Veuillez saisir le jour du film: ");

        int heures = inputValue("Veuillez saisir le jour du film: ");

        int minutes = inputValue("Veuillez saisir les minutes du film: ");

        System.out.println(gestion.lesSallesFilm());

        int idSalle = inputValue("Veuillez saisir l'id de la salle du film: ");

        Horaire debut = new Horaire(heures, minutes);
        gestion.ajouterSeanceFilm(id, jour, debut, idSalle);

        logMessage("Ajout Seance Film ... ");
    }

    private static void ajouterSeancePiece()
    {
        System.out.println(gestion.lesPieces());

        int id = inputValue("Veuillez saisir l'id de la piece: ");

        int jour = inputValue("Veuillez saisir le jour de la piece: ");

        if(gestion.existeSeanceCeJour(id, jour))
            throw new IllegalArgumentException("Il existe deja une seance programme ce jour");

        int heures = inputValue("Veuillez saisir l'heure de la piece: ");

        int minutes = inputValue("Veuillez saisir les minutes de la piece: ");

        System.out.println(gestion.lesSallesTheatre());

        int idSalle = inputValue("Veuillez saisir l'id de la salle de la piece: ");

        Horaire debutPiece = new Horaire(heures, minutes);
        gestion.ajouterSeanceTheatre(id, jour, debutPiece, idSalle);

        logMessage("Ajout Seance Film ... ");
    }

    /**
     * @throws IllegalArgumentException pas assez de place
     */
    private static void vendrePlaceFilm()
    {
        boolean inputValid = true;
        int idFilm = -1;
        int jour = -1;
        int heures = -1;
        int minutes = -1;

        do {
            System.out.println(gestion.lesFilms());

            if(!inputValid)
                System.err.println("Seance inexistante, reselectionez une seance");
            idFilm = inputValue("Veuillez saisir l'id du film: ");

            System.out.println(gestion.lesSeancesFilm(idFilm));

            jour = inputValue("Veuillez saisir le jour de la seance: ");

            System.out.println("Veuillez saisir l'horaire de la seance: ");
            heures = inputValue("heures: ");
            minutes = inputValue("minutes: ");

            inputValid = gestion.existeSeanceFilm(idFilm, jour, heures, minutes);
        } while(!inputValid);

        int nbPlaceDispo = gestion.getNbPlacesDispo(idFilm, jour, heures, minutes);
        System.out.println("Nombre de place disponnible " +  nbPlaceDispo );

        int nbPlaceTN = inputValue("Veuillez saisir le nombre de place tarif normal:");
        int nbPlaceTR = inputValue("Veuillez saisir le nombre de place tarif reduit:");

        if(nbPlaceDispo < nbPlaceTN + nbPlaceTR)
            throw new IllegalArgumentException("Pas assez de place ! ");

        Horaire debutSeance = new Horaire(heures, minutes);
        gestion.vendrePlaceFilmTN(idFilm, jour, debutSeance, nbPlaceTN);
        gestion.vendrePlaceFilmTR(idFilm, jour, debutSeance, nbPlaceTR);

        logMessage("Vente effectue ... ");
    }

    private static void vendrePlacePiece()
    {
        System.out.println(gestion.lesPieces());

        int idPiece = inputValue("Veuillez saisir l'id de la piece:");

        System.out.println(gestion.lesSeancesTheatre(idPiece));

        int jour = inputValue("Veuillez saisir le jour de la seance : ");

        System.out.println("Nombre de place disponnibles :" + gestion.getNbPlacesDispo(idPiece, jour));
        System.out.println("Nombre de fauteuil disponnibles :" + gestion.getNbFauteuilsDispo(idPiece, jour));

        int nbPlaceTN = inputValue("Veuillez saisir le nombre de place standard : ");
        int nbPlaceFauteuil = inputValue("Veuillez saisir le nombre de fauteuil : ");

        gestion.vendrePlacePieceTN(idPiece, jour, nbPlaceTN);
        gestion.vendrePlaceFauteuilPiece(idPiece, jour, nbPlaceFauteuil);

        logMessage("Vente effectue ... ");
    }

    private static void tauxEtChiffreAffaire()
    {
        System.out.println(gestion.lesFilms());
        System.out.println(gestion.lesPieces());

        int idSpectacle = inputValue("Veuillez saisir l'id du spectacle : ");

        System.out.println("Taux de remplissage : " + gestion.getTauxRemplissage(idSpectacle) + " % ");
        System.out.println("Chiffre D'affaire : " + gestion.chiffreAffaires(idSpectacle) + " euro(s) ");
    }

    private static boolean gestionMenu(int choice)
    {
        if(choice < 0 || choice > 9)
            throw new IllegalArgumentException("Le choix doit etre entre 0 et 9");

        if(choice == 0)
            reinitialiserProgrammation();
        else if(choice == 1)
            ajouterFilm();
        else if(choice == 2)
            ajouterPiece();
        else if(choice == 3)
            ajouterInterprete();
        else if(choice == 4)
            ajouterSeanceFilm();
        else if(choice == 5)
            ajouterSeancePiece();
        else if(choice == 6)
            vendrePlaceFilm();
        else if(choice == 7)
            vendrePlacePiece();
        else if(choice == 8)
            tauxEtChiffreAffaire();
        else if(choice == 9)
            return false;
        return true;
    }

    private static Integer inputValue(String message)
    {
        System.out.print(message);
        if(!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new IllegalArgumentException("Veuillez saisir un chiffre !");
        }

        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static void logMessage(String message)
    {
        System.out.println( "\033[0;34m " + message + " \033[0m");
    }

    private static void afficherMenu()
    {
        System.out.println("reinitialiser programmation (0)");
        System.out.println("ajouter un film (1)");
        System.out.println("ajouter une piece de theatre (2)");
        System.out.println("ajouter un nom d'interprete a un spectacle (3)");
        System.out.println("ajouter une seance pour un film (4)");
        System.out.println("ajouter une seance pour une piece de theatre (5)");
        System.out.println("vendre des places pour un film (6)");
        System.out.println("vendre des places pour une piece de theatre (7)");
        System.out.println("consulter le chiffre d'affaire et le taux de remplissage d'un spectacle (8)");
        System.out.println("quitter (9)");
    }

}
