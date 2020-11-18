package database;

import java.sql.*;
import java.util.ArrayList;
import java.time.*;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//getConnection();
		//selectEmployee();
		//insertEmployee();
		//updateEmployee();
		//ArrayList<String> working = returnWorking();
        	//ArrayList<String> floorCleaners = assignedToFloor();
		//deleteEmployee();
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
	
	public static ArrayList<String> returnWorking() throws Exception {
        //compares current time to standard shift times of 7 am to 3 pm, 3pm to 11pm, 11 pm to 7 am
        //assigns number to each shift corresponding to the database, then issues a querry to the database to find workers with the same shift as is currently.
        
        	ArrayList<String> array = new ArrayList<>();
        	try {
        		Connection con = getConnection();
            		int shift = 0;
            		LocalTime now = LocalTime.now();
            		LocalTime shift1 = LocalTime.parse("07:00");
            		LocalTime shift2 = LocalTime.parse("15:00");
            		LocalTime shift3 = LocalTime.parse("23:00");
            		if(now.isAfter(shift1)&& now.isBefore(shift2)){
            		    shift = 1;
            		}
            		else if(now.isAfter(shift2)&& now.isBefore(shift3)){
            		    shift = 2;
            		}
            		else{
            		    shift = 3;
            		}
            		System.out.println("Shift is: " + shift);
            		PreparedStatement getWorking = con.prepareStatement("SELECT e.Ename "
            			+ "FROM employee as e "
            			+ "WHERE e.Ssn = (SELECT m.Essn "
				+ "FROM manager as m "
                                + "WHERE m.shift = " + shift + " ) "
                    		+ "OR e.Ssn = (SELECT r.Essn "
				+ "FROM receptionist as r "
                                + "WHERE r.shift = " + shift + " ) "
                		+ "OR e.Ssn = (SELECT h.Essn "
				+ "FROM housekeeper as h "
                                + "WHERE h.shift = " + shift + " )");
            
            		ResultSet result = getWorking.executeQuery();
			while(result.next()) {
				array.add(result.getString("e.Ename"));
			}
			//return array; 
        	} catch (Exception e) {
        		System.out.println(e);
        	} finally {
        	    return array;
        	}
    	}
	
	public static ArrayList<String> assignedToFloor() throws Exception {
        
        	ArrayList<String> array = new ArrayList<>();
        	Scanner input = new Scanner(System.in);
		System.out.println("Enter in Floor to check: ");
		int floor = input.nextInt();
        	while(floor < 1 || floor > 5){
        	    System.out.println("Invalid Floor. Enter in a valid Floor to check: ");
        	    floor = input.nextInt();
        	}
        	try{
            		Connection con = getConnection();
            		PreparedStatement getFloorCleaners = con.prepareStatement("SELECT e.Ename FROM employee as e "
                    		+ "INNER JOIN Room as r on e.Ssn = r.k_Ssn "
                    		+ "WHERE floor = " + floor);
            		ResultSet result = getFloorCleaners.executeQuery();
			while(result.next()) {
			array.add(result.getString("e.Ename"));
			}
        	}
        	catch (Exception e) {
        	    System.out.println(e);
        	} finally {
        	    return array;
        	}
        
    	}
}
