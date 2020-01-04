package com.company;

import com.company.salles.Salle;
import com.company.seances.SeanceFilm;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        GestionProgrammationSemaine gestionProgrammationSemaine = new GestionProgrammationSemaine();

        // Gestion du menu

            Scanner scanner = new Scanner(System.in);

            boolean running = true;
        do {
            try {
                afficherMenu();
                System.out.print("Faites votre choix: ");
                Scanner entree = new Scanner(System.in);
                if (scanner.hasNextInt()) {

                    int choice = scanner.nextInt();
                    if (choice < 0 || choice > 9) {
                        System.out.println("le choix doit etre entre 0 et 9");
                        continue;
                    } else {
                        String titre;
                        String realisateur = "";
                        String titrePiece = "";
                        String metteurEnScene = "";
                        int nbEntractes = -1;
                        int idSpectacle = -1;
                        String interprete = "";
                        int duree = 0;
                        int idPiece = -1;
                        int jourPiece = -1;
                        int heuresPiece = -1;
                        int minutesPiece = -1;
                        int idSallePiece = -1;
                        int idFilm = -1;
                        int jour = -1;
                        int heures = -1;
                        int minutes = -1;
                        int idSalle = -1;
                        int nbPlaceTN = -1;
                        int nbPlaceTR = -1;
                        int nbPlaceFauteuil = -1;

                        switch (choice) {

                            case 0: // Reinitialiser programmation
                                gestionProgrammationSemaine.reinitialiserProgrammation();
                                System.out.println("Programmation reinitialiser ...");
                                Thread.sleep(1000);
                                break;
                            case 1: // Ajouter Film

                                System.out.print("Veuillez saisir le titre du film: ");
                                titre = entree.nextLine();

                                System.out.print("Veuillez saisir le realisateur du film: ");
                                realisateur = entree.nextLine();

                                System.out.print("Veuillez saisir la duree du film: ");
                                if(entree.hasNextInt())
                                    duree = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez entrer un entier !! ");
                                    entree.reset();
                                    continue;
                                }

                                gestionProgrammationSemaine.ajouterFilm(titre, realisateur, duree);
                                System.out.println("Ajout d'un film ... ");
                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 2: // Ajouter piece de theatre


                                System.out.print("Veuillez saisir le titre de la piece: ");
                                titrePiece = entree.nextLine();

                                System.out.print("Veuillez saisir le metteur en scene de la piece: ");
                                metteurEnScene = entree.nextLine();

                                System.out.print("Veuillez saisir le nombre d'entractes de la piece: ");
                                if(entree.hasNextInt())
                                    nbEntractes = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    continue;
                                }

                                gestionProgrammationSemaine.ajouterPiece(titrePiece, metteurEnScene, nbEntractes);
                                System.out.println("Ajout d'une piece ... ");
                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 3: // Ajouter un nom d'inteprete a un spectacle


                                System.out.println(gestionProgrammationSemaine.lesFilms());
                                System.out.println(gestionProgrammationSemaine.lesPieces());

                                System.out.print("Veuillez saisir l'id du spectacle: ");
                                if(entree.hasNextInt())
                                    idSpectacle = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir l'interpreteur du spectacle: ");
                                interprete = entree.next();

                                gestionProgrammationSemaine.ajouterInterprete(idSpectacle, interprete);

                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 4: // Ajouter une seance pour un film


                                if(gestionProgrammationSemaine.lesFilms() == null)
                                {
                                    System.out.println("Aucun film ! ");
                                    continue;
                                }

                                System.out.println(gestionProgrammationSemaine.lesFilms());

                                System.out.print("Veuillez saisir l'id d'un film: ");
                                if(entree.hasNextInt())
                                    idFilm = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir le jour du film: ");
                                if(entree.hasNextInt())
                                    jour = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir l'heure du film: ");
                                if(entree.hasNextInt())
                                    heures = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir les minutes du film: ");
                                if(entree.hasNextInt())
                                    minutes = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir l'id de la salle du film: ");
                                if(entree.hasNextInt())
                                    idSalle = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                Horaire debut = new Horaire(heures, minutes);
                                gestionProgrammationSemaine.ajouterSeanceFilm(idFilm, jour, debut, idSalle);

                                System.out.println("Ajout Seance Film ... ");

                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 5: // Ajouter une seance pour une piece de theatre

                                if(gestionProgrammationSemaine.lesFilms() == null)
                                {
                                    System.out.println("Aucun film ! ");
                                    continue;
                                }

                                System.out.println(gestionProgrammationSemaine.lesFilms());

                                System.out.print("Veuillez saisir l'id de la piece: ");
                                if(entree.hasNextInt())
                                    idPiece = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir le jour de la piece: ");
                                if(entree.hasNextInt())
                                    jourPiece = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir l'heure de la piece: ");
                                if(entree.hasNextInt())
                                    heuresPiece = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir les minutes de la piece: ");
                                if(entree.hasNextInt())
                                    minutesPiece = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir l'id de la salle de la piece: ");
                                if(entree.hasNextInt())
                                    idSallePiece = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                Horaire debutPiece = new Horaire(heuresPiece, minutesPiece);
                                gestionProgrammationSemaine.ajouterSeanceTheatre(idPiece, jourPiece, debutPiece, idSallePiece);

                                System.out.println("Ajout Seance Film ... ");

                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 6: // Vendre des places pour un film

                                System.out.println(gestionProgrammationSemaine.lesFilms());

                                System.out.print("Veuillez saisir l'id du film: ");
                                if(entree.hasNextInt())
                                    idFilm = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                if(gestionProgrammationSemaine.lesSeancesFilm(idFilm) == null)
                                {
                                    System.out.println("Aucun Film !");
                                    continue;
                                }

                                System.out.println(gestionProgrammationSemaine.lesSeancesFilm(idFilm));

                                System.out.print("Veuillez saisir le jour de la seance: ");
                                if(entree.hasNextInt())
                                    jour = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.println("Veuillez saisir l'horaire de la seance: ");
                                System.out.print("heures:");
                                if(entree.hasNextInt())
                                    heures = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }
                                System.out.print("minutes:");
                                if(entree.hasNextInt())
                                    minutes = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.println("Nombre de place disponnible " + gestionProgrammationSemaine.getNbPlacesDispo(idFilm, jour, heures, minutes) );

                                System.out.print("Veuillez saisir le nombre de place tarif normal:");
                                if(entree.hasNextInt())
                                    minutes = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir le nombre de place tarif reduit:");
                                if(entree.hasNextInt())
                                    minutes = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                Horaire debutSeance = new Horaire(heures, minutes);
                                gestionProgrammationSemaine.vendrePlaceFilmTN(idFilm, jour, debutSeance, nbPlaceTN);
                                gestionProgrammationSemaine.vendrePlaceFilmTR(idFilm, jour, debutSeance, nbPlaceTR);

                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 7: // Vendre des places pour une pieces de theatre
                                System.out.println(gestionProgrammationSemaine.lesPieces());

                                System.out.print("Veuillez saisir l'id de la piece:");
                                if(entree.hasNextInt())
                                    idPiece = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                if(gestionProgrammationSemaine.lesSeancesTheatre(idPiece) == null)
                                {
                                    System.out.println("Aucune Piece");
                                    continue;
                                }

                                System.out.println(gestionProgrammationSemaine.lesSeancesTheatre(idPiece));

                                System.out.print("Veuillez saisir le jour de la seance :");
                                if(entree.hasNextInt())
                                    jour = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.println("Nombre de place disponnibles :" + gestionProgrammationSemaine.getNbPlacesDispo(idPiece, jour));
                                System.out.println("Nombre de fauteuil disponnibles :" + gestionProgrammationSemaine.getNbFauteuilsDispo(idPiece, jour));

                                System.out.print("Veuillez saisir le nombre de place standard :");
                                if(entree.hasNextInt())
                                    nbPlaceTN = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.print("Veuillez saisir le nombre de fauteuil :");
                                if(entree.hasNextInt())
                                    nbPlaceFauteuil = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                gestionProgrammationSemaine.vendrePlacePieceTN(idPiece, jour, nbPlaceTN);
                                gestionProgrammationSemaine.vendrePlaceFauteuilPiece(idPiece, jour, nbPlaceFauteuil);

                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 8: // Consulter chiffre d'affaire et taux de remplissage
                                System.out.println(gestionProgrammationSemaine.lesFilms());
                                System.out.println(gestionProgrammationSemaine.lesPieces());

                                System.out.print("Veuillez saisir l'id du spectacle :");
                                if(entree.hasNextInt())
                                    idSpectacle = entree.nextInt();
                                else
                                {
                                    System.out.println("Veuillez saisir un chiffre !");
                                    entree.reset();
                                    continue;
                                }

                                System.out.println("Taux de remplissage : " + gestionProgrammationSemaine.getTauxRemplissage(idSpectacle));
                                System.out.println("Chiffre D'affaire : " + gestionProgrammationSemaine.chiffreAffaires(idSpectacle));

                                System.out.println("Retour au menu ... ");
                                Thread.sleep(1000);
                                break;
                            case 9:
                                running = false;
                                break;
                        }
                    }
                } else {
                    System.out.println("Votre choix doit etre un entier");
                    scanner.next();
                    continue;
                }

            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }

        } while (running);
        System.out.println("Arret en cours...");


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
