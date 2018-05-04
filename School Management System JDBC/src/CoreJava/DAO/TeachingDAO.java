package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Attending;
import CoreJava.Models.Teaching;
import CoreJava.systemsInterfaces.TeachingDAOI;
import CoreJava.utils.OracleQueries;

public class TeachingDAO implements TeachingDAOI {


	public List<Teaching> getInstructorsCourses() throws SQLException {
		List<Teaching> teachers = new ArrayList<Teaching>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETINSTRUCTORCOURSES);
			result = stmt.executeQuery();
			
			while(result != null && result.next() ) {
				
				Teaching teacher = new Teaching();
				teacher.setCourse_name(result.getString(1));
				teacher.setMinimum_gpa(result.getDouble(2));
				teacher.setFull_name(result.getString(3));
				teacher.setEmail(result.getString(4));
				
				teachers.add(teacher);
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
	
		
		return teachers;
	}

	public int assignInstructorToCourse(int course_id, int instructor_id) throws SQLException {
			int result =0;
			Connection conn = null;
			PreparedStatement stmt = null;
				
				try {
						conn = OracleConnection.getConnection();
						stmt = conn.prepareStatement(OracleQueries.ASSIGNINSTRUCTORTOCOURSE);
						stmt.setInt(1, course_id);
						stmt.setInt(2, instructor_id);
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
				
			
			
		return result;
	}

	
	
	
}
