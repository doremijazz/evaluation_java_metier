package fr.app;


import fr.model.*;
import fr.buisness.formation;


public class main {
	public static void main (String[] args) throws Exception{
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

	}
}
