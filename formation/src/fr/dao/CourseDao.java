
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
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
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

	

}
