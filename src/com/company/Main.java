package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        GestionProgrammationSemaine gestionProgrammationSemaine = new GestionProgrammationSemaine();

        // Gestion du menu
        try {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            do {
                afficherMenu();
                System.out.print("Faites votre choix: ");
                if (scanner.hasNextInt()) {

                    int choice = scanner.nextInt();
                    if (choice < 0 || choice > 9) {
                        System.out.println("le choix doit etre entre 0 et 9");
                        continue;
                    } else {
                        switch (choice) {
                            case 0: // Reinitialiser programmation
                                System.out.println("CHOIX 0");
                                break;
                            case 1: // Ajouter Film
                                System.out.println("CHOIX 1");
                                break;
                            case 2: // Ajouter piece de theatre
                                System.out.println("CHOIX 2");
                                break;
                            case 3: // Ajouter un nom d'inteprete a un spectacle
                                System.out.println("CHOIX 3");
                                break;
                            case 4: // Ajouter une seance pour un film
                                System.out.println("CHOIX 4");
                                break;
                            case 5: // Ajouter une seance pour une piece de theatre
                                System.out.println("CHOIX 5");
                                break;
                            case 6: // Vendre des places pour un film
                                System.out.println("CHOIX 6");
                                break;
                            case 7: // Vendre des places pour une pieces de theatre
                                System.out.println("CHOIX 7");
                                break;
                            case 8: // Consulter chiffre d'affaire et taux de remplissage
                                System.out.println("CHOIX 8");
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

            } while (running);
            System.out.println("Arret en cours...");
        } catch (Exception e)
        {
            System.err.println(e);
        }

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
