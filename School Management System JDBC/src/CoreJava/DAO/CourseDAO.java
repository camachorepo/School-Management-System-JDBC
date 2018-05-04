package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Course;
import CoreJava.Models.Instructor;
import CoreJava.systemsInterfaces.CourseDAOI;
import CoreJava.utils.OracleQueries;

public class CourseDAO implements CourseDAOI {


	public List<Course> getAllCourses() throws SQLException {
		List <Course> courses = new ArrayList<Course>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETALLCOURSES);
			result = stmt.executeQuery();
			
			while(result != null && result.next() ) {
				
				Course current = new Course();
				
				current.setCourse_id(result.getInt(1));
				current.setCourse_name(result.getString(2));
				current.setMinimum_gpa(result.getDouble(3));
				
				
				courses.add(current);
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			result.close();
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	
		
		
		return courses;
	}


	public List<Course> getCourseByInstructor(int instructor_id) throws SQLException {
		List <Course> courses = new ArrayList<Course>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETALLCOURSESBYINSTRUCTOR);
			stmt.setInt(1, instructor_id);
			result = stmt.executeQuery();
			
			while(result != null && result.next() ) {
				
				Course current = new Course();
				
				current.setCourse_id(result.getInt(1));
				current.setCourse_name(result.getString(2));
				current.setMinimum_gpa(result.getInt(3));
				
				
				courses.add(current);
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			result.close();
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	
		
		
		return courses;
	}

}
