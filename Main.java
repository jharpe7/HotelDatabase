package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;


public class Main {

	public static void main(String[] args) throws Exception{
		
		getConnection();
		
		//ArrayList<String> roomsToClean = selectRoomCleaning();
		//System.out.println("Here are the rooms to clean for today's shift: " + roomsToClean);
		
		//selectEmployee();
		//insertReservation();
		//updateEmployee();
		//deleteRoom();
		
		//insertRoom();
		//updateRoomPrice();
		
		//addEmployee();
		
		//checkRoomPrice();
		checkRoomAvailability();
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/hoteldbase";
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
	
	public static void insertReservation() throws Exception{
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		

		Scanner ans = new Scanner(System.in);
		
		System.out.println("Enter in the Reservation number: ");
		String resNum = ans.nextLine();
		
		System.out.println("Enter in your employee SSN: ");
		String ssn = ans.nextLine();
		
		System.out.println("Enter the room number you are reserving: ");
		String r_num = ans.nextLine();
		
		System.out.println("What is the check-in day? (Please adhere to YYYY-MM-DD): ");
		String check_in = ans.nextLine();
		
		//Making sure there isn't already a reservation there
		int check = 0;
		while(check == 0) {
	
				
				Connection con1 = getConnection();
				
				PreparedStatement checkinSelect = con1.prepareStatement("SELECT r.checkin FROM reservation as r WHERE r.r_num = " + r_num);
				PreparedStatement checkoutSelect = con1.prepareStatement("SELECT r.checkout FROM reservation as r WHERE r.r_num = " + r_num);
				
				ResultSet resultIN = checkinSelect.executeQuery();
				ResultSet resultOUT = checkoutSelect.executeQuery();
				
				ArrayList<String> checkinArray = new ArrayList<String>();
				while(resultIN.next()) {
					checkinArray.add(resultIN.getString("checkin"));
				}
				
				ArrayList<String> checkoutArray = new ArrayList<String>();
				while(resultOUT.next()) {
					checkoutArray.add(resultOUT.getString("checkout"));
				}
				
				boolean sameCheckFlag = false;
				Date proposedDate = sdformat.parse(check_in);
				for(int i = 0; i < checkinArray.size(); i++) {
					Date cinDate = sdformat.parse(checkinArray.get(i));
					Date coutDate = sdformat.parse(checkoutArray.get(i));
					if(proposedDate.compareTo(cinDate) >= 0 && proposedDate.compareTo(coutDate) < 0) {
							System.err.println("ERROR! There is already a reservation for this room up until " + coutDate);
					}
					
				}
				
				if(sameCheckFlag == false) {
					check = 1;
					break;
				}
				System.out.println("What is the check-in day? (Please adhere to YYYY-MM-DD): ");
				check_in = ans.nextLine();
		}
		
		System.out.println("What is the check-out day?(YYYY-MM-DD): ");
		String check_out = ans.nextLine();
		
		System.out.println("What is the guest's username?: ");
		String guest_uname = ans.nextLine();
		
		try {
		Connection con2 = getConnection();	
		PreparedStatement insertReservation = con2.prepareStatement("INSERT INTO reservation(res_num, r_Ssn, checkin, checkout, r_num, guest_uname)"
				+ " VALUES ("+ resNum +", " + ssn + ",  '" + check_in + "' , '" + check_out + "', " + r_num + ", '" + guest_uname + "')");
		
		insertReservation.executeUpdate();
		
		PreparedStatement roomCost = con2.prepareStatement("SELECT r.r_price FROM room as r WHERE r.r_num = " + r_num);
		
		ResultSet resultCost = roomCost.executeQuery();
		ArrayList<String> costArray = new ArrayList<String>();
		
		while(resultCost.next()) {
			costArray.add(resultCost.getString("r_price"));
		}
		
		PreparedStatement creditInfo = con2.prepareStatement("SELECT cc_info FROM guest WHERE guest_username = '" + guest_uname + "' ");
		
		ArrayList<String> ccArray = new ArrayList<String>();
		ResultSet resultCCard = creditInfo.executeQuery();
		
		while(resultCCard.next()) {
			ccArray.add(resultCCard.getString("cc_info"));
		}
		
		
		PreparedStatement insertBill = con2.prepareStatement("INSERT INTO bill(res_num, paid_on, payment_amount, cc_info, guest_uname) VALUES "
				+ "(" + resNum + "," + " '" + check_in + "', " + costArray.get(0) + ", " + ccArray.get(0) + ", '" + guest_uname + "')");
		
		insertBill.executeUpdate();
		
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			System.out.println("Insert for Reservation and Bill Completed!");
		}
		return;
	}	
	
	public static void selectRoomCleaning() throws Exception{
		Scanner ans = new Scanner(System.in);
		System.out.println("Enter in Housekeeper SSN: ");
		String cleaner_ssn = ans.nextLine();

		try {
			Connection con = getConnection();
			PreparedStatement roomCleanQueryStatement = con.prepareStatement("SELECT r.r_num FROM room as r INNER JOIN housekeeper h on r.k_Ssn = h.Essn where h.Essn = "+ cleaner_ssn);
			
			ResultSet result = roomCleanQueryStatement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			
			while(result.next()) {
				
				array.add(result.getString("r_num"));

			}
			System.out.println("All records have been selected");
			System.out.println("Here are the rooms to clean for today's shift: " + array);
			 
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public static void insertRoom() throws Exception{
		Scanner ans = new Scanner(System.in);
		
		System.out.println("Enter in the new room number: ");
		String newRoomNum = ans.nextLine();
		
		System.out.println("Enter in the ssn of who manages this room: ");
		String managerSSN = ans.nextLine();
		
		System.out.println("Enter in the ssn of who will clean this room: ");
		String kSSN = ans.nextLine();
		
		System.out.println("Enter in the description of this room(Suite, bed(s), perks): ");
		String desc = ans.nextLine();
		
		System.out.println("Enter in the price of the room: ");
		String price = ans.nextLine();
		
		System.out.println("Enter in the number of beds: ");
		String numBeds = ans.nextLine();
		
		System.out.println("Enter in the capacity of the room: ");
		String numCapacity = ans.nextLine();
		
		System.out.println("Enter in the name of the room: ");
		String roomName = ans.nextLine();
		
		System.out.println("What floor is this room on?: ");
		String roomFloor = ans.nextLine();
		
		System.out.println("Is this room currently available?: (1 for yes, 2 for no)");
		String isAvailable = ans.nextLine();
		
		try {
			Connection con = getConnection();
			
			PreparedStatement roomInsert = con.prepareStatement("INSERT INTO room(r_num, m_Ssn, k_Ssn, r_description, r_price, r_beds, r_capacity, r_name, r_floor, is_available)"
					+ "VALUES (" + newRoomNum + ", " + managerSSN + ", " + kSSN +  ", '" + desc + "', " + price + ", " + numBeds + ", " + numCapacity + ", '"
					+ roomName + "', " + roomFloor + ", " + isAvailable + ")");
			
			roomInsert.executeUpdate();
			
			System.out.println("Insertion successful!");
		
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void updateRoomPrice() throws Exception{
		Scanner ans = new Scanner(System.in);
		
		System.out.println("Which room would you like to update the price of? (Please give the room number): ");
		String roomNum = ans.nextLine();
		
		System.out.println("What is its updated price?: ");
		String price = ans.nextLine();
		
		try {
			Connection con = getConnection();
			
			PreparedStatement update = con.prepareStatement("UPDATE room SET r_price = " + price + " WHERE r_num =" + roomNum);
			
			update.executeUpdate();
			System.out.println("Price successfully updated!");
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return;
	}
	
	public static void deleteRoom() throws Exception{

		Scanner ans = new Scanner(System.in);
		
		System.out.println("Which room would you like to delete?(Please give the room number): ");
		String roomNum = ans.nextLine();
		
		
		try {
		Connection con = getConnection();
		
		PreparedStatement delete = con.prepareStatement("DELETE from room WHERE r_num = " + roomNum);
		
		delete.executeUpdate();
		
		System.out.println("Room deleted!");
		return;
		}
		catch(Exception e) {
			System.out.println(e);
			return;
		}
	}
	
	public static void addEmployee() throws Exception{
		Scanner ans = new Scanner(System.in);
		
		System.out.println("What is the new employee's SSN?: ");
		String ssn = ans.nextLine();
		
		System.out.println("What is the new employee's full name? (First and last)");
		String name = ans.nextLine();
		
		System.out.println("What is the Adrress of the new employee? (Street, City, State)");
		String address = ans.nextLine();
		
		try {
			Connection con = getConnection();
			
			PreparedStatement insertEmployee = con.prepareStatement("INSERT into employee(Ssn, Ename, Address) VALUES ("
					+ ssn + ", '" + name + "', '" + address + "')" );
			
			insertEmployee.executeUpdate();
			System.out.println("Employee successfully inserted!");
		}
		catch(Exception e){
			System.out.println(e);
		}
		return;
	}
	
	public static void updateRoomCleaner() {
		Scanner ans = new Scanner(System.in);
		
		System.out.println("Which room would you like to change the cleaning responsibility of?: ");
		String roomNum = ans.nextLine();
		
		System.out.println("Who would you like to have clean this room? (Please enter SSN without hyphens)");
		String ssn = ans.nextLine();
		
		try {
			Connection con = getConnection();
			
			PreparedStatement cleaningUpdate = con.prepareStatement("UPDATE room SET k_Ssn=" + ssn + "WHERE r_num=" +roomNum);
			
			cleaningUpdate.executeUpdate();
			System.out.println("Cleaning employee successfully changed for room " +roomNum);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return;
	}
	
	public static void checkRoomPrice() {
		Scanner ans = new Scanner(System.in);
		
		System.out.println("Which room would you like to check the price of? (Please enter in the room number)");
		String roomNum = ans.nextLine();
		
		try {
			Connection con = getConnection();
			
			PreparedStatement priceCheck = con.prepareStatement("SELECT r_price FROM room WHERE r_num=" + roomNum);
			
			ResultSet result = priceCheck.executeQuery();
			ArrayList<String> priceArray = new ArrayList<String>();
			
			while(result.next()) {
				priceArray.add(result.getString("r_price"));
			}
			
			System.out.println("Here is the price of Room " + roomNum + ": $" + priceArray.get(0));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void checkRoomAvailability() {
		Scanner ans = new Scanner(System.in);
		
		System.out.println("Which room would you like to check the availability of? (Please enter in the room number)");
		String roomNum = ans.nextLine();
		
		try {
			Connection con = getConnection();
			
			PreparedStatement availabilityCheck = con.prepareStatement("SELECT is_available FROM room WHERE r_num=" + roomNum);
			
			ResultSet result = availabilityCheck.executeQuery();
			ArrayList<String> availabilityArray = new ArrayList<String>();
			
			while(result.next()) {
				availabilityArray.add(result.getString("is_available"));
				
			}
			
			if(availabilityArray.get(0).equals("0")) {
				System.out.println("Sorry, this room is currently unavailable to do reservations");
			}
			else {
				System.out.println("This room is currently available to take reservations");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return;
	}
}
