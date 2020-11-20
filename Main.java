import java.sql.*;
import java.util.ArrayList;

import java.time.*;
import java.util.Scanner;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		getConnection();
		//selectEmployee();
		//insertEmployee();
		//updateEmployee();
		deleteEmployee();

		//checkIn();
		//checkOut();
		//insertReservation();

		//Which employees are currently working
		ArrayList<String> getWorking = returnWorking();
		System.out.println("Here are the employees currently working: " + getWorking);

		//Which rooms need to be cleaned
		ArrayList<String> roomsToClean = selectRoomCleaning();
		System.out.println("Here are the rooms to clean for today's shift: " + roomsToClean);

		//Which employees will be cleaning each floor
        ArrayList<String> floorCleaners = assignedToFloor();
        System.out.println("Here are the employees that will be cleaning the rooms: " +floorCleaners);

	}

	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/lberna1db";
			String url = "jdbc:mysql://localhost:3306/hoteldb";
			String username = "root";
			String password = "";

@@ -35,99 +50,278 @@ public static Connection getConnection() throws Exception{

		return null;
	}

	public static ArrayList<String> selectEmployee() throws Exception{
//WHICH ROOMS NEED TO BE CLEANED
		public static ArrayList<String> selectRoomCleaning() throws Exception{
			Scanner ans = new Scanner(System.in);
			System.out.println("Enter in Housekeeper SSN: ");
			String cleaner_ssn = ans.nextLine();

		try {
			Connection con = getConnection();
			PreparedStatement employeeStatement = con.prepareStatement("SELECT * FROM employee");

			ResultSet result = employeeStatement.executeQuery();

			ArrayList<String> array = new ArrayList<String>();

			while(result.next()) {
			try {
				Connection con = getConnection();
				PreparedStatement roomCleanQueryStatement = con.prepareStatement("SELECT r.r_num FROM room as r INNER JOIN housekeeper h on r.k_Ssn = h.Essn where h.Essn = "+ cleaner_ssn);

				ResultSet result = roomCleanQueryStatement.executeQuery();

				ArrayList<String> array = new ArrayList<String>();

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
				while(result.next()) {

					array.add(result.getString("r_num"));

				}
				System.out.println("All records have been selected");
				return array; 
			}
			System.out.println("All records have been selected");
			return array; 
		}
		catch(Exception e) {
			System.out.println(e);
			catch(Exception e) {
				System.out.println(e);
			}
			return null;

		}
		return null;

	}
//MAKE RESERVATION AND BILL TO ACCOMPANY IT
		public static void insertReservation() throws Exception{
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");


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

		insert.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			System.out.println("Insert Completed!");

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
		}
	}

	public static void updateEmployee() throws Exception{
		final String updatedAddress = "405 Brook Ave, Baltimore, MD";
		final String ssn = "111223344";

		try {
//CHECK IN METHOD
		public static void checkIn() throws Exception{
            int res_num;
            Scanner input = new Scanner(System.in);
            String r_num = "";
            System.out.println("Enter Reservation # of Guest You'd Like to Checkin: ");
            res_num = input.nextInt();
            try {
		Connection con = getConnection();
		PreparedStatement update = con.prepareStatement("UPDATE employee SET Address = '" + updatedAddress + "' WHERE Ssn = " + ssn);

		update.executeUpdate();
		PreparedStatement status = con.prepareStatement("UPDATE reservation SET checkedin = ?  WHERE res_num = " +res_num);
                PreparedStatement room = con.prepareStatement("SELECT r_num FROM reservation WHERE res_num = "+res_num);
                ResultSet result = room.executeQuery();
		ArrayList<String> array = new ArrayList<>();
                while(result.next()){
                    array.add(result.getString("r_num"));
                }
                r_num = array.get(0);
                PreparedStatement roomUpdate = con.prepareStatement("UPDATE room SET is_available = ? WHERE r_num = "+r_num);
                status.setBoolean(1, true);
                roomUpdate.setBoolean(1, false);
                roomUpdate.executeUpdate();
		status.executeUpdate();
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

        }

		try {
//CHECK OUT METHOD
	public static void checkOut() throws Exception{
            int res_num;
            Scanner input = new Scanner(System.in);
            String r_num;
            System.out.println("Enter Reservation # of Guest You'd Like to CheckOut: ");
            res_num = input.nextInt();
            try {
		Connection con = getConnection();
		PreparedStatement delete = con.prepareStatement("DELETE from employee WHERE Ssn = " + ssn);

		delete.executeUpdate();
                PreparedStatement status = con.prepareStatement("UPDATE reservation SET checkedin = ?, checkedout = ?  WHERE res_num = " +res_num);
                PreparedStatement room = con.prepareStatement("SELECT r_num FROM reservation WHERE res_num = "+res_num);
                ResultSet result = room.executeQuery();
		ArrayList<String> array = new ArrayList<String>();
                while(result.next()){
                    array.add(result.getString("r_num"));
                }
                r_num = array.get(0);
                PreparedStatement roomUpdate = con.prepareStatement("UPDATE room SET is_available = ? WHERE r_num = "+r_num);
                roomUpdate.setBoolean(1, true);
                status.setBoolean(1, false);
                status.setBoolean(2, true);
                roomUpdate.executeUpdate();
		status.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Delete Completed!");
			System.out.println("Update Completed!");
		}
	}
}

        }

//CHECK WHAT EMPLOYEES ARE WORKING
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

//CHECK WHO IS CLEANING EACH FLOOR
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
