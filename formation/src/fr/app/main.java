package fr.app;


import fr.model.*;

import java.util.Scanner;

import fr.buisness.formation;
import fr.ihm.Console;

/**
 * Classe principal de l'application de gestion des formations.
 * 
 * Cette classe permet de tester les opérations DAO et de filtrage.
 *  
 * Elle propose égelement un menu interactif permettant à un utilisateur
 * non authentifier d'afficher et de rechercher par filtre des formations.
 */
public class main {
	/**
	 * Point d'entrée principal de l'application de gestiosn des formations.
	 * 
	 * Dans une premier temps, la méthode effectue les différents test de gestions
	 * avec DAO et de filtrage par mot(s) clé ou modalitée(s), puis lancele menu interactif.
	 */
	public static void main (String[] args) throws Exception{
		
		// Scanner utilisé pour récupérer les entrée utilisateur.
		Scanner scan = new Scanner(System.in);
		
		// Formation utilisé pour les tests de DAO
		Course course_1 = new Course("name", "description", 20, true, false, 250.50);
		
		//Classe metier
		formation formation = new formation();
		
		//Gestion de l'affichage des menus utilisateur dans la console
		Console console = new Console();
		
		/*
		 * Test des différentes manipulations DAO :
		 * création, lecture, modification et suppression.
		 */
		System.out.println("################################");
		System.out.println("           TEST DAO             ");
		System.out.println("################################");
		formation.create(course_1);
		formation.read(course_1);
		formation.readAll();
		formation.update(course_1);
		formation.delete(course_1);
		
		/*
		 * Test des méthodes permettant de filtrer
		 * les formations par mot(s) clé ou par modalitée(s)
		 */
		System.out.println("################################");
		System.out.println("           TEST FILTRE            ");
		System.out.println("################################");
		formation.by_key_word("web");
		formation.by_key_word("objet");
		formation.by_modality("presentiel");
		formation.by_modality("distanciel");
		formation.by_modality("presentiel et distanciel");
		
		/*
		 * Lancement du menu interactif
		 */
		System.out.println("################################");
		System.out.println("           TEST INTERACTION            ");
		System.out.println("################################");
		console.display_menu();
		
		//Controle de l'ouverture et de la fermeture de l'application
		boolean app_run = true;
		
		while (app_run) {
			
			//Controle l'ouverture et la fermeture des sous menus
			boolean menu_run = true;
			
			//Récupération des choix utilisateur dans le menu principal
			int input_app = (int) input(scan, "int");
			
			/*
			 * Choix 1 : affichage de toutes les formations
			 */
			if ( input_app == 1) {
				formation.readAll();
				
			/*
			 * Choix 2 : filtrage et affichage par mot's) clé	
			 */
			}else if (input_app == 2){
				while (menu_run) {
					console.display_key_word_surch();
					formation.by_key_word((String) input(scan, "string"));
					console.display_run_menu();
					
					// Choix permettant de refair eun rechercher par mot(s) clé
					// ou de revenir au menu principal
					int input_menu = (int) input(scan, "int");
					if (input_menu == 2) {
						break;
					}else if (input_menu == 1) {
						continue;
					}else {
						System.out.println("Erreur dans la saisie, veuillez saisir 1 ou 2");
					}
				}
				
			/*
			 * CHoix 3 : 	filtrage et affichage par modalitée(s) d'enseignement
			 */
			}else if (input_app == 3) {
				while (menu_run) {
					console.display_modality_surch();
					String modality = (String) input(scan, "string");
					
					/*
					 * Verriffie la saisie utilisateur.
					 * Seules les trois valeurs suivantes sont autorisées :
					 * - presentiel
					 * - distanciel
					 * - presentiel et distanciel
					 */
					if (!modality.equalsIgnoreCase("presentiel")
			                && !modality.equalsIgnoreCase("distanciel")
			                && !modality.equalsIgnoreCase("presentiel et distanciel")) {
						continue;
					}
					formation.by_modality(modality);
					console.display_run_menu();
					int input_menu = (int) input(scan, "int");
					if (input_menu == 2) {
						break;
					}else if (input_menu == 1) {
						continue;
					}else {
						System.out.println("Erreur dans la saisie, veuillez saisir 1 ou 2");
					}
				}
				
			/*
			 * Choix 0 : quitter l'application	
			 */
			}else if (input_app == 0) {
				System.out.println("\nAu revoir !");
				break;
				
			}else {
				System.out.println("Erreur dans la selection du menu suivant veuillez saisir 1, 2, 3 ou 0");
			}
			
			//Réaffichage du menu principal
			console.display_menu();
		}

	}
	
	/**
	 * Récupére un la saisie utilisateur.
	 * 
	 * La méthodes permet de récupérer soit une chaine de caractére pour le filtrage par mot clé, modalitée(s) 
	 * ou en entier pour le choix des actions dans les menus en fonction du type demandé en paramétre.
	 * 
	 * Pour le filtrage par mot(s) clé, nextLine() permet de recupérer un ou plusiseurs mots. Mais aussi
	 * récupérer plusieurs mot dans le cadre de recherche par modalitée(s) si on veut trouver les formations
	 * a la fois en presentiel et en distanciel.
	 * 
	 * @param scan Scanner pour récupérer les saisie utilisateur.
	 * @param type type de données attendu : string ou int
	 * @return la valeur saisie
	 */
	public static Object input(Scanner scan, String type) {
		if (type.equalsIgnoreCase("int")){
			int value = scan.nextInt();
	        scan.nextLine(); // consomme le retour à la ligne
	        return value;
		}else if (type.equalsIgnoreCase("string")) {
			return scan.nextLine();
		}else {
			throw new IllegalArgumentException("le type doit etre 'string' ou 'int'");
		}
	}
}
