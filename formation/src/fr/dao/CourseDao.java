
package fr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import fr.model.Course;

/**
 * DAO permettant de gérer les formation présente dans la base de données
 * 
 * Opération réalisées : création, lecture, modification et supression
 * 
 * @author BinetA
 */
public class CourseDao extends Dao<Course> {

	/**
	 * Récupére toutes les formations dan sla base de doannées
	 * 
	 * @return liste des formations
	 */
	@Override
	public List<Course> getAll() {
		
		//Liste qui va contenir les formations récupérées dans la BDD
		List<Course> courses = new ArrayList<>();
		
		//Requete SQL permettant de récupérer toutes les formations contenue dans la table de la BDD
		String sql = "SELECT * FROM f_course";
		
		//Connexion a la BDD
		try(Connection connection = getconnection();
				//Préparation de la requete
				PreparedStatement statement = connection.prepareStatement(sql);
				//Execution du SELECT
				ResultSet result = statement.executeQuery()){
			//Parcours de toutes les linges retrounées 
			while(result.next()) {
				//Transforme la sorti SQL en Objet Course et l'ajoute a la liste
				courses.add(createCourseFromResult(result));
			}
			
		}catch (SQLException exception){
			//Affiche l'erreur SQL si la lecture echoue
			System.err.println("Erreur lors de la lectures des cours dans la db : " + exception.getMessage());
		}
		//Retourne tous les formations trouvées
		return courses;
	}


	/**
	 * Recupére un formation d'aprés son identifiant dans la base de données
	 * 
	 * @param id identifiant de la formation
	 * @return formation récupérée
	 */
	@Override
	public Course getById(int id) {
		
		//Selection uniquement la formation possédant l'id passé en paramétre de la méthode
		String sql = "SELECT co_id_course, co_name, co_description, co_duration, co_presentiel, co_distanciel, co_price FROM f_course WHERE co_id_course = ?";
		
		//Connexion a la BDD
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			//Remplace le ? par l'id passé en paramétre
			statement.setInt(1, id);
			//Execute la requéte
			try(ResultSet result = statement.executeQuery()){
				//Verrifie si la ligne est trouvée dans la table de la BDD
				if(result.next()) {
					//Retourne  la sorti SQL en Objet Course
					return createCourseFromResult(result);
				}
			}
		}catch (SQLException exception) {
			//Affiche l'erreur SQL si la recherche echoue
			System.err.println("Erreur lors de la lecture du cours : " + exception.getMessage());
		}
		
		return null;
	}

	/**
	 * Ajoute une formation a la base de données
	 * 
	 * @param course formation a ajouter
	 * 
	 * @return l'identifiant de la formation ajoutée
	 */
	public int create(Course course) {
		//Insert dans la base de données les informations de la formation contune dans le paramétre course
		String sql = "INSERT INTO f_course (co_name, co_description, co_duration, co_presentiel, co_distanciel, co_price) VALUES (?,?,?,?,?,?)";
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)){
			//Remplace les valeur ? par les valeurs du cours en paramétre
			statement.setString(1, course.getName());
			statement.setString(2, course.getDescription());
			statement.setInt(3, course.getDuration());
			statement.setBoolean(4, course.isPresentiel());
			statement.setBoolean(5, course.isDistanciel());
			statement.setDouble(6, course.getPrice());
			
			
			//Execute la requete et récupére le nombre de ligne crées dans la table de la BDD
			int rows = statement.executeUpdate();
			
			//Verrifie qu'il y a bien qu'une seule ligne crée
			if (rows == 1){
				try (ResultSet keys = statement.getGeneratedKeys()){
					if (keys.next()) {
						//Affecte l'id génére en BDD à l'objet Course
						course.setIdCourse(keys.getInt(1));
					}
					
				}
				return course.getIdCourse();
			}
			
		}catch (SQLException exception) {
			System.err.println("Erreur lors de la creation du cours en db : " + exception.getMessage());
		}
		//Retourne 0 si la connexion échoue
		return 0;
	}

	/**
	 * Modifie les information de la formation dans la base de données
	 * 
	 * @param course informations a modifier
	 * 
	 * @return true si la MAJ est réussie
	 */
	@Override
	public boolean update(Course course) {
		//MAJ des informations d'un cours cours en BDD d'aprés les informations de la formation passé en paramétre
		String sql = "UPDATE f_course SET co_name = ?, co_description = ?, co_duration = ?, co_presentiel = ?, co_distanciel = ?, co_price = ? WHERE co_id_course = ?";
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			//Remplace les valeur ? par les valeurs du cours en paramétre
			statement.setString(1, course.getName());
			statement.setString(2, course.getDescription());
			statement.setInt(3, course.getDuration());
			statement.setBoolean(4, course.isPresentiel());
			statement.setBoolean(5, course.isDistanciel());
			statement.setDouble(6, course.getPrice());
			statement.setInt(7, course.getIdCourse());
			
			try(ResultSet result = statement.executeQuery()){
				if (result.next()) {
					//Tentative de création d'un nouveau cours qui écrase l'ancien
					createCourseFromResult(result);
					
				}
				//Retourne true si la MAJ a reussi
				return true;
			}
			
		}catch (SQLException exception) {
			System.err.println("Erreur lors de la MAJ du cours : " + exception.getMessage());
			return false;
		}
	}

	/**
	 * Supprime la formation de la base de données
	 * 
	 * @param id indentifiant de la formation a supprimer
	 * 
	 * @return true si la supression est reussie
	 */
	@Override
	public boolean delete(int id) {
		//Supprime en BDD le cours portant l'id passé en paramétre
		String sql = "DELETE FROM f_course WHERE co_id_course  = ?";

        try (
            Connection connection = getconnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
        	//Remplace ? par l'id passé en paramétre
            statement.setInt(1, id);
            
            //True si une seule linge a été supprimé
            return statement.executeUpdate() == 1;

        } catch (SQLException exception) {
            System.err.println(
                    "Erreur lors de la suppression du cours : "
                    + exception.getMessage()
            );
        }

        return false;
    }

	/**
	 * Créer un nouvel objet formation d'aprés les informations de la base de données
	 * 
	 * @param result informations de la base de données
	 * 
	 * @return formation
	 */
	private Course createCourseFromResult(ResultSet result) throws SQLException {
		/*
		 * Récupére chaque colone de la ligne SQL
		 * pour construire un nouvel objet Course.
		 */
		return new Course(
				result.getInt("co_id_course"),
				result.getString("co_name"),
				result.getString("co_description"),
				result.getInt("co_duration"),
				result.getBoolean("co_presentiel"),
				result.getBoolean("co_distanciel"),
				result.getDouble("co_price"));
	}

}
