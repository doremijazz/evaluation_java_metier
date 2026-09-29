package fr.buisness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.dao.CourseDao;
import fr.model.Course;
import wagu.Block;
import wagu.Board;
import wagu.Table;

public class formation {
	
	CourseDao Course_dao = new CourseDao();
	
	public void create(Course course_1) {
		//CREATE
		course_1.setIdCourse(Course_dao.create(course_1));
		System.out.println("Création d'un course dans la db avec l'id : " + course_1.getIdCourse());
	}
	
	public void read(Course course_1) {
	
		//READ
		Course course_2 = Course_dao.getById(course_1.getIdCourse());
		System.out.println("\nLecture d'un course de la db : " + course_2);
	
	}
	
	public void readAll() {
		//READ ALL
		System.out.println("\nListe des cours : ");
		List<Course> courses = new ArrayList<>();
		for (Course currentCourse : Course_dao.getAll()) {
			courses.add(currentCourse);
		}
		display(courses);
	}
	
	public void update(Course course_1) {
		//UPDATE
		Course course_3 = new Course(course_1.getIdCourse(), "test", "description2", 30, false, true, 550.50);
		boolean sucess_1 = Course_dao.update(course_3);
		System.out.println("MAJ d'un article dans la db : " + sucess_1 + "\nCourse : " + Course_dao.getById(course_3.getIdCourse()));
	}
	
	public void delete (Course course_1) {
		//DELETE
		boolean sucess_2 = Course_dao.delete(course_1.getIdCourse());
		System.out.println("\nSupression du cours créer dans la db : " + sucess_2);
	}
	
	public void by_key_word(String key) {
		System.out.println("\nListe des cours avec pour mot clé " + key + " : ");
		List<String> headersList = Arrays.asList("ID", "NAME", "DESCRIPTION", "DURATION", "PRESENTIEL", "DISTANCIEL", "PRICE");
		List<List<String>> rowsList = new ArrayList<List<String>>(); 
		for (Course currentCourse : Course_dao.getAll()) {
			if (currentCourse.getDescription().contains(key)){
				List<String> course = new ArrayList<String>();
				course.addAll(Arrays.asList(String.valueOf(currentCourse.getIdCourse()), currentCourse.getName(), currentCourse.getDescription(), String.valueOf(currentCourse.getDuration()), String.valueOf(currentCourse.isPresentiel()),  String.valueOf(currentCourse.isDistanciel()),  String.valueOf(currentCourse.getPrice())));
				rowsList.add(course);
				
			}
		}
		Board board = new Board(250);
		Table table = new Table(board, 150, headersList, rowsList);
		List<Integer> colWidthsListEdited = Arrays.asList(10, 10, 60, 10, 10, 10, 10);
		table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
		List<Integer> colAlignList = Arrays.asList(
			    Block.DATA_CENTER, 
			    Block.DATA_CENTER, 
			    Block.DATA_CENTER, 
			    Block.DATA_CENTER, 
			    Block.DATA_CENTER,
			    Block.DATA_CENTER,
			    Block.DATA_CENTER);
			table.setColAlignsList(colAlignList);
			
		Block tableBlock = table.tableToBlocks();
		
		board.setInitialBlock(tableBlock);
		board.build();
		String tableString = board.getPreview();
		System.out.println(tableString);
	}
}
