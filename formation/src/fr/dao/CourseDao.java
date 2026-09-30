
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
		List<Course> courses = new ArrayList<>();
		String sql = "SELECT * FROM f_course";
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				courses.add(createCourseFromResult(result));
			}
			
		}catch (SQLException exception){
			System.err.println("Erreur lors de la lectures des cours dans la db : " + exception.getMessage());
		}
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
		String sql = "SELECT co_id_course, co_name, co_description, co_duration, co_presentiel, co_distanciel, co_price FROM f_course WHERE co_id_course = ?";
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					return createCourseFromResult(result);
				}
			}
		}catch (SQLException exception) {
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
		String sql = "INSERT INTO f_course (co_name, co_description, co_duration, co_presentiel, co_distanciel, co_price) VALUES (?,?,?,?,?,?)";
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, course.getName());
			statement.setString(2, course.getDescription());
			statement.setInt(3, course.getDuration());
			statement.setBoolean(4, course.isPresentiel());
			statement.setBoolean(5, course.isDistanciel());
			statement.setDouble(6, course.getPrice());
			
			int rows = statement.executeUpdate();
			
			if (rows == 1){
				try (ResultSet keys = statement.getGeneratedKeys()){
					if (keys.next()) {
						course.setIdCourse(keys.getInt(1));
					}
					
				}
				return course.getIdCourse();
			}
			
		}catch (SQLException exception) {
			System.err.println("Erreur lors de la creation du cours en db : " + exception.getMessage());
		}
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
		String sql = "UPDATE f_course SET co_name = ?, co_description = ?, co_duration = ?, co_presentiel = ?, co_distanciel = ?, co_price = ? WHERE co_id_course = ?";
		try(Connection connection = getconnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, course.getName());
			statement.setString(2, course.getDescription());
			statement.setInt(3, course.getDuration());
			statement.setBoolean(4, course.isPresentiel());
			statement.setBoolean(5, course.isDistanciel());
			statement.setDouble(6, course.getPrice());
			statement.setInt(7, course.getIdCourse());
			
			try(ResultSet result = statement.executeQuery()){
				if (result.next()) {
					createCourseFromResult(result);
					
				}
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
		String sql = "DELETE FROM f_course WHERE co_id_course  = ?";

        try (
            Connection connection = getconnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            
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
