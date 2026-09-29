package fr.app;

import fr.dao.CourseDao;
import fr.model.*;

public class main {
	public static void main (String[] args) throws Exception{
		CourseDao Course_dao = new CourseDao();
		
		//CREATE
		Course course_1 = new Course("name", "description", 20, true, false, 250.50);
		course_1.setIdCourse(Course_dao.create(course_1));
		System.out.println("Création d'un course dans la db avec l'id : " + course_1.getIdCourse());
		
		//READ
		Course course_2 = Course_dao.getById(course_1.getIdCourse());
		System.out.println("\n Lecture d'un course de la db : " + course_2);
		
		//READ ALL
		System.out.println("\n Liste des articles : ");
		for(Course currentCourse : Course_dao.getAll()) {
			System.out.println(currentCourse);
		}
		
		//UPDATE
		Course course_3 = new Course("name_modif", "description_modif", 30, false, true, 550.50);
		boolean sucess_1 = Course_dao.update(course_3);
		System.out.println("MAJ d'un article dans la db : " + sucess_1 + "Course : " + Course_dao.getById(course_1.getIdCourse()));
		
		//DELETE
		boolean sucess_2 = Course_dao.delete(course_1.getIdCourse());
		System.out.println("Supression du cours créer dans la db : " + sucess_2);

	}
}
