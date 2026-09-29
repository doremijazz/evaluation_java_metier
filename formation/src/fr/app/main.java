package fr.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.dao.CourseDao;
import fr.model.*;
import wagu.Block;
import wagu.Board;
import wagu.Table;

public class main {
	public static void main (String[] args) throws Exception{
		CourseDao Course_dao = new CourseDao();
		
		//CREATE
		Course course_1 = new Course("name", "description", 20, true, false, 250.50);
		course_1.setIdCourse(Course_dao.create(course_1));
		System.out.println("Création d'un course dans la db avec l'id : " + course_1.getIdCourse());
		
		//READ
		Course course_2 = Course_dao.getById(course_1.getIdCourse());
		System.out.println("\nLecture d'un course de la db : " + course_2);
		
		//READ ALL
		System.out.println("\nListe des cours : ");
		List<String> headersList = Arrays.asList("ID", "NAME", "DESCRIPTION", "DURATION", "PRESENTIEL", "DISTANCIEL", "PRICE");
		List<List<String>> rowsList = new ArrayList<List<String>>(); 
		for(Course currentCourse : Course_dao.getAll()) {
			List<String> course = new ArrayList<String>();
			course.addAll(Arrays.asList(String.valueOf(currentCourse.getIdCourse()), currentCourse.getName(), currentCourse.getDescription(), String.valueOf(currentCourse.getDuration()), String.valueOf(currentCourse.isPresentiel()),  String.valueOf(currentCourse.isDistanciel()),  String.valueOf(currentCourse.getPrice())));
			rowsList.add(course);
		}
		Board board = new Board(150);
		Table table = new Table(board, 150, headersList, rowsList);
		List<Integer> colWidthsListEdited = Arrays.asList(10, 10, 30, 10, 10, 10, 10);
		table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
		Block tableBlock = table.tableToBlocks();
		board.setInitialBlock(tableBlock);
		board.build();
		String tableString = board.getPreview();
		System.out.println(tableString);
		
		//UPDATE
		Course course_3 = new Course(course_1.getIdCourse(), "java", "description2", 30, false, true, 550.50);
		boolean sucess_1 = Course_dao.update(course_3);
		System.out.println("MAJ d'un article dans la db : " + sucess_1 + "\nCourse : " + Course_dao.getById(course_3.getIdCourse()));
		
		//DELETE
		boolean sucess_2 = Course_dao.delete(course_1.getIdCourse());
		System.out.println("\nSupression du cours créer dans la db : " + sucess_2);

	}
}
