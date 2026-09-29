
package fr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import fr.model.Course;

public class CourseDao extends Dao<Course> {

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
