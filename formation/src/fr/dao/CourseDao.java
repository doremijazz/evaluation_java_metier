
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
		// TODO Auto-generated method stub
		return null;
	}

	public int create(Course course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	private Course createCourseFromResult(ResultSet result) throws SQLException {
		return new Course(
				result.getInt("co_id_coourse"),
				result.getString("co_name"),
				result.getString("co_description"),
				result.getInt("co_duration"),
				result.getBoolean("co_presentiel"),
				result.getBoolean("co_distanciel"),
				result.getDouble("co_price"));
	}

}
