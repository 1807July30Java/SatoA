package project1Sato;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.util.ConnectionUtil;

public class TestDB {

	private String propertiesFile = "connection.properties";

	@Test
	public void testConnectionToDB(){
		boolean works = true;
		boolean didIt = false;
		try(Connection con = ConnectionUtil.getConnection(propertiesFile)){
			didIt=true;
		} catch (SQLException | IOException e) {
			didIt=false;
		}
		assertEquals(didIt,works);
	}
	
	@Test
	public void testGetEmployee() {
		String firstEmployee = "Alan";
		EmployeeDao ed = new EmployeeDaoImp();
		String anObject = ed.getEmployee(1).getFirstName();
		assertTrue(firstEmployee.equals(anObject));
	}

}
