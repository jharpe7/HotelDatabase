package database;

import java.sql.*;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		getConnection();
		//selectEmployee();
		//insertEmployee();
		//updateEmployee();
		deleteEmployee();
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/lberna1db";
			String username = "root";
			String password = "";
			
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			
			return conn;
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return null;
	}
	
	public static ArrayList<String> selectEmployee() throws Exception{

		try {
			Connection con = getConnection();
			PreparedStatement employeeStatement = con.prepareStatement("SELECT * FROM employee");
			
			ResultSet result = employeeStatement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			
			while(result.next()) {
				
				array.add(result.getString("Fname"));
				array.add(result.getString("Minit"));
				array.add(result.getString("Lname"));
				array.add(result.getString("Ssn"));
				array.add(result.getString("Bdate"));
				array.add(result.getString("Address"));
				array.add(result.getString("Sex"));
				array.add(result.getString("Salary"));
				array.add(result.getString("Super_ssn"));
				array.add(result.getString("Dno"));
			}
			System.out.println("All records have been selected");
			return array; 
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
		
	}

	public static void insertEmployee() throws Exception{
		final String fName = "Matthew";
		final String mInit = "J";
		final String lName = "Bender";
		final String ssn = "111223344";
		final String bDate = "1998-08-17";
		final String address = "123 Main St, Towson, MD";
		final String sex = "M";
		final String salary = "50000";
		final String super_ssn = "333445555";
		final String Dno = "1";
		try {
		Connection con = getConnection();	
		PreparedStatement insert = con.prepareStatement("INSERT INTO employee(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn,"
				+ " Dno) VALUES ('"+ fName +"', '" + mInit + "',  '" + lName + "' , " + ssn + ", '" + bDate + "', '" + address + "', '" + sex + "', "  
				+ salary + ", " + super_ssn + ", " + Dno + ")");
		
		insert.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			System.out.println("Insert Completed!");
		}
	}
	
	public static void updateEmployee() throws Exception{
		final String updatedAddress = "405 Brook Ave, Baltimore, MD";
		final String ssn = "111223344";
		
		try {
		Connection con = getConnection();
		PreparedStatement update = con.prepareStatement("UPDATE employee SET Address = '" + updatedAddress + "' WHERE Ssn = " + ssn);
		
		update.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Update Completed!");
		}
	}
	
	public static void deleteEmployee() throws Exception{
		final String ssn = "111223344";
		
		try {
		Connection con = getConnection();
		PreparedStatement delete = con.prepareStatement("DELETE from employee WHERE Ssn = " + ssn);
		
		delete.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Delete Completed!");
		}
	}
}
