package fr.app;


import fr.model.*;

import java.util.Scanner;

import fr.buisness.formation;
import fr.ihm.Console;


public class main {
	public static void main (String[] args) throws Exception{
		
		Scanner scan = new Scanner(System.in);
		
		Course course_1 = new Course("name", "description", 20, true, false, 250.50);
		
		formation formation = new formation();
		Console console = new Console();
		
		System.out.println("################################");
		System.out.println("           TEST DAO             ");
		System.out.println("################################");
		formation.create(course_1);
		formation.read(course_1);
		formation.readAll();
		formation.update(course_1);
		formation.delete(course_1);
		
		System.out.println("################################");
		System.out.println("           TEST FILTRE            ");
		System.out.println("################################");
		formation.by_key_word("web");
		formation.by_key_word("objet");
		formation.by_modality("presentiel");
		formation.by_modality("distanciel");
		formation.by_modality("presentiel et distanciel");
		
		System.out.println("################################");
		System.out.println("           TEST INTERACTION            ");
		System.out.println("################################");
		console.display_menu();
		boolean app_run = true;
		
		while (app_run) {
			boolean menu_run = true;
			int input_app = (int) input(scan, "int");
			if ( input_app == 1) {
				formation.readAll();
				
			}else if (input_app == 2){
				while (menu_run) {
					console.display_key_word_surch();
					formation.by_key_word((String) input(scan, "string"));
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
				
			}else if (input_app == 3) {
				while (menu_run) {
					console.display_modality_surch();
					String modality = (String) input(scan, "string"); 
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
				
			}else if (input_app == 0) {
				break;
				
			}else {
				System.out.println("Erreur dans la selection du menu suivant veuillez saisir 1, 2, 3 ou 0");
			}
			
			console.display_menu();
		}

	}
	
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
