package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.CustomExceptions.StudentRegistrationException;
import CoreJava.Models.Attending;
import CoreJava.Models.Course;
import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.AttendingDAOI;
import CoreJava.utils.OracleQueries;

public class AttendingDAO implements AttendingDAOI{

	
	public List<Attending> getStudentCourse(int student_id) throws SQLException {
			List<Attending> allAttending = new ArrayList<Attending>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet result = null;
			
			
			
			try {
				conn = OracleConnection.getConnection();
				stmt = conn.prepareStatement(OracleQueries.GETALLATTENDINGBYSTUDENT);
				stmt.setInt(1, student_id);
				result = stmt.executeQuery();
				
				while(result != null && result.next() ) {
					
					Attending attending = new Attending();
					attending.setCourse_name(result.getString(1));
					attending.setFull_name(result.getString(2));
					attending.setEmail(result.getString(3));
					
					allAttending.add(attending);
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
		
			
			
		return allAttending;
	}

	public int registerStudentToCourse(Student student, Course course) throws SQLException, StudentRegistrationException { 
		
		int result = 0;
		if(student.getGpa() >= course.getMinimum_gpa()) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			
			try {
					conn = OracleConnection.getConnection();
					stmt = conn.prepareStatement(OracleQueries.REGISTERSTUDENTTOCOURSE);
					stmt.setInt(1, course.getCourse_id());
					stmt.setInt(2, student.getStudent_id());
					result = stmt.executeUpdate();
					
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			
			
		}
		else
		{
			throw new StudentRegistrationException("Did not meet the minimum GPA requirement \n Registration Denied");
		}
		
		return result;
	}
	
	

}
