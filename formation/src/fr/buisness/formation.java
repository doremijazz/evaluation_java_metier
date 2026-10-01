package fr.buisness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.dao.CourseDao;
import fr.model.Course;
import wagu.Block;
import wagu.Board;
import wagu.Table;

/**
 * Classe metier permettant de gérer les oprération sur les formations
 * 
 * Elle utilise CourseDao pour communiquer avec la base de données et permet également
 * de filtrer et afficher les formations par mots clé ou modalitées d'enseignement
 */
public class formation {
	
	/**
	 * Dao utilisé pour accéder aux formation de la base de données
	 */
	CourseDao Course_dao = new CourseDao();
	
	/**
	 * Permet d'ajouter un formation a la base de données
	 * @param course_1 informations du cours a ajouter
	 */
	public void create(Course course_1) {
		//CREATE
		course_1.setIdCourse(Course_dao.create(course_1));
		System.out.println("Création d'un course dans la db avec l'id : " + course_1.getIdCourse());
	}
	
	/**
	 * Permet d'afficher un cours grace a son id dans la base de données
	 * @param course_1 informations du cours avec notament sont id pour le rechercher en db
	 */
	public void read(Course course_1) {
	
		//READ
		Course course_2 = Course_dao.getById(course_1.getIdCourse());
		System.out.println("\nLecture d'un course de la db : " + course_2);
	
	}
	
	/**
	 * Permet d'afficher sous forme de tableau toutes les formations continue en base de données
	 */
	public void readAll() {
		//READ ALL
		System.out.println("\nListe des cours : ");
		List<Course> courses = new ArrayList<>();
		for (Course currentCourse : Course_dao.getAll()) {
			courses.add(currentCourse);
		}
		display(courses);
	}
	
	/**
	 * Permet de mettre a jours les informations d'une formation en base de donées
	 * @param course_1 toutes les information de la formation dont celles a modifier
	 */
	public void update(Course course_1) {
		//UPDATE
		Course course_3 = new Course(course_1.getIdCourse(), "test", "description2", 30, false, true, 550.50);
		boolean sucess_1 = Course_dao.update(course_3);
		System.out.println("MAJ d'un article dans la db : " + sucess_1 + "\nCourse : " + Course_dao.getById(course_3.getIdCourse()));
	}
	
	/**
	 * Permet de supprimer la formation de la base de données grace a son id
	 * @param course_1informations du cours avec notament sont id pour la suppression en db
	 */
	public void delete (Course course_1) {
		//DELETE
		boolean sucess_2 = Course_dao.delete(course_1.getIdCourse());
		System.out.println("\nSupression du cours créer dans la db : " + sucess_2);
	}
	
	/**
	 * Recherche avec pour filtre des mots clés des formations dans la base de données
	 * et les affiches sous forme de tableau
	 * 
	 * @param key mots clé a rechercher dans la description de la formation
	 */
	public void by_key_word(String key) {
		List<Course> courses = new ArrayList<>();
		for (Course currentCourse : Course_dao.getAll()) {
			if (currentCourse.getDescription().contains(key)){
				courses.add(currentCourse);
			}
		}
		System.out.println("\nListe des cours avec pour mot clé " + key + " : ");
		display(courses);
		
	}
	
	/**
	 * Recherche avec pour filtre la ou les modalitées d'enseignement les formations dans la base de données
	 * et les affiche sous forme de tableau
	 * 
	 * @param modality modalitée(s) d'enseignement des formations
	 */
	public void by_modality(String modality) {
		List<Course> courses = new ArrayList<>();
		for (Course currentCourse : Course_dao.getAll()) {
			if (currentCourse == null) continue; // Skip null entries

            boolean isPresentiel = currentCourse.isPresentiel();
            boolean isDistanciel = currentCourse.isDistanciel();

            if (isPresentiel && modality.equalsIgnoreCase("presentiel")) {
            	courses.add(currentCourse);
            } else if (isDistanciel && modality.equalsIgnoreCase("distanciel")) {
            	courses.add(currentCourse);
            } else if (isPresentiel && isDistanciel && modality.equalsIgnoreCase("presentiel et distanciel")) {
            	courses.add(currentCourse);
            }
		}
		System.out.println("\nListe des cours avec en " + modality + " : ");
		display(courses);
	}
	
	/**
	 * Permet l'affichage en forme de tableau des formations : toutes (readAll) ou celles filtrées par les autres methodes
	 * (by_modality ou by_key_word)
	 * 
	 * @param courses listes des formations que l'on souhaite afficher sous forme de tableau
	 */
	public void display(List<Course> courses) {
		
		List<String> headersList = Arrays.asList("ID", "NAME", "DESCRIPTION", "DURATION", "PRESENTIEL", "DISTANCIEL", "PRICE");
		List<List<String>> rowsList = new ArrayList<List<String>>(); 
		for (Course currentCourse : courses) {
			
			List<String> course = new ArrayList<String>();
			course.addAll(Arrays.asList(String.valueOf(currentCourse.getIdCourse()), currentCourse.getName(), currentCourse.getDescription(), String.valueOf(currentCourse.getDuration()), String.valueOf(currentCourse.isPresentiel()),  String.valueOf(currentCourse.isDistanciel()),  String.valueOf(currentCourse.getPrice())));
			rowsList.add(course);

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
