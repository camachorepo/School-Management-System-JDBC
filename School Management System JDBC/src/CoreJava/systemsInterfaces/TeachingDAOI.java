package CoreJava.systemsInterfaces;

import java.sql.SQLException;
import java.util.List;

import CoreJava.Models.Teaching;

public interface TeachingDAOI {
	
	
	int  assignInstructorToCourse(int course_id, int instructor_id) throws SQLException;
	List <Teaching> getInstructorsCourses() throws SQLException;

}
