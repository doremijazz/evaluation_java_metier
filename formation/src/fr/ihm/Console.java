package fr.ihm;

public class Console {
	public void display_menu() {
		System.out.println("###### MENU ######");
		System.out.println("\nChoisisez un action");
		System.out.println("\n1. Afficher toutes les formations");
		System.out.println("2. Chercher et afficher des formations par mots clés");
		System.out.println("3. Chercher et afficher des formation par modalitée d'enseignement (distanciel ou présentiel)");
		System.out.println("0. Quitter l'application");
	}
	
	public void display_key_word_surch() {
		System.out.println("------ Recherche et affichage par mots clés ------");
		System.out.println("\nSaisier un ou plusieurs mots clés a rechercher dans la description de la formation");
	}
	
	public void display_modality_surch() {
		System.out.println("------ Recherche et affichage par modalitée(s) ------");
		System.out.println("\nSaisier soit 'distanciel', soit 'presentiel', soit 'presentiel et distanciel'");
	}
	
	public void display_run_menu() {
		System.out.println("\n1. Faire une autre recherche");
		System.out.println("2. Quitter le menu");
	}
}
