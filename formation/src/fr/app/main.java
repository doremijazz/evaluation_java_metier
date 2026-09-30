package fr.app;


import fr.model.*;

import java.util.Scanner;

import fr.buisness.formation;


public class main {
	public static void main (String[] args) throws Exception{
		
		Scanner scan = new Scanner(System.in);
		
		Course course_1 = new Course("name", "description", 20, true, false, 250.50);
		
		formation formation = new formation();
		
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
		dispaly_menu();

	}
	
	public Object input (Scanner scan, String type) {
		if (type.equalsIgnoreCase("int")){
			return scan.nextInt();
		}else if (type.equalsIgnoreCase("string")) {
			return scan.next();
		}else {
			throw new IllegalArgumentException("le type doit etre 'string' ou 'int'");
		}
	}
}
