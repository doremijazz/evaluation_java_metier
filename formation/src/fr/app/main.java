package fr.app;


import fr.model.*;
import fr.buisness.formation;


public class main {
	public static void main (String[] args) throws Exception{
		Course course_1 = new Course("name", "description", 20, true, false, 250.50);
		
		formation formation = new formation();
		
		formation.create(course_1);
		formation.read(course_1);
		formation.readAll();
		formation.update(course_1);
		formation.delete(course_1);

	}
}
