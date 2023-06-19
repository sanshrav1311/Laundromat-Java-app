import java.io.*;
import java.util.*;
import com.opencsv.*;

public class Laundromat {
	static Admin admin = new Admin();
	static Scanner in = new Scanner(System.in);
	static Student studentId = null;
	public static void adminLogin() {
		do { 
			System.out.println("enter back to go back");
			System.out.println("enter email ID");
			String emailID = in.nextLine();
			if(emailID.equals("back")) {
				return;
			}
			System.out.println("enter password");
			String password = in.nextLine();
			if(!(password.equals("admin@123")) || !(emailID.equals("admin@bitspilani.ac.in"))) {
				System.out.println("invalid credentials");
			}
			else {
				break;
			}
		}while(true);
		
		return;
	}
	public static void login() {
		do { 
			System.out.println("enter back to go back");
			System.out.println("enter BITSID");
			String bitsId = in.nextLine();
			if(bitsId.equals("back")) {
				return;
			}
			System.out.println("enter password");
			String password = in.nextLine();
			studentId = CSVStudent.getStudent(bitsId);
			if(!password.equals(studentId.getPassword())) {
				studentId = null;
				System.out.println("invalid password");
			}
		}while(studentId == null);
		
		studentId.laundryList = CSVLaundry.getStudentLaundry(studentId.getBitsId());
		return;
	}
	public static void register() throws IOException {
		System.out.println("enter back to go back");
		System.out.println("enter Name");
		String name = in.nextLine();
		if(name.equals("back")) {
			return;
		}
		System.out.println("enter email ID");
		String emailId = in.nextLine();
		if(emailId.equals("back")) {
			return;
		}
		System.out.println("enter BITSID");
		String bitsId = in.nextLine();
		if(bitsId.equals("back")) {
			return;
		}
		if(CSVStudent.getStudent(bitsId) != null) {
			System.out.println("User already exists");
			return;
		}
		System.out.println("enter hostel");
		String hostel = in.nextLine();
		if(hostel.equals("back")) {
			return;
		}
		System.out.println("enter password");
		String password = in.nextLine();
		if(password.equals("back")) {
			return;
		}
		Plans plan = null;
		String planID = "-1";
		do {
			System.out.println("enter plan ID, 0 for first plan, 1 for second plan and so on..");
			for(Plans l : Plans.plansList){
				System.out.println(l);
			}
			try {
				planID = in.nextLine();
				if(planID.equals("back")) {
					return;
				}
				plan = Plans.plansList.get(Integer.parseInt(planID));
			}catch(ArrayIndexOutOfBoundsException e) {
				planID = "-1";
				System.out.println("invalid planID");
			}
		}while(planID == "-1");
		LoginAndRegistration.register(name,emailId,bitsId,plan,hostel,password);
		return;
	}
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);		
		String input;
		try{
		Admin.setRevenue(CSVRevenue.getTotalRevenue());
		}catch(Exception e){

		}
		// CSVLaundry laundryCSV = new CSVLaundry();
		Plans.plansList = CSVPlans.getPlansList();
		do {
		System.out.println("type exit to exit");
		System.out.println("enter 1 for Login, 2 for Register, 3 for Admin Login");
		input = in.nextLine();
		switch(input) {
		case "1":
			//call login function
			login();
			if(studentId == null){
				break;
			}
			String input2;
			do {
				System.out.println("enter 1 for drop Laundry, 2 for check Plan, 3 for check last laundry status, 4 Laundry info, 5 logout");
				input2 = in.nextLine();
				switch(input2) {
				case "1":
					System.out.println("enter weight in kg");
					try {
						String weight = in.nextLine();
						studentId.clothesGiven(Double.parseDouble(weight));
					}
					catch(Exception e) {
						e.printStackTrace();
						System.out.println("Invalid input");
					}
					break;
				case "2":
					System.out.println(studentId.getPlan());
					break;
				case "3":
					System.out.println(studentId.getLastLaundryStatus());
					break;
				case "4":
					for(Laundry l : studentId.laundryList){
						System.out.println(l);
					}
					break;
				case "5":
					studentId.setStopThreadToTrue();
					studentId = null;
					input2 = "back";
					break;
				default:
					System.out.println("Invalid input");
				}
				
			}while(!(input2.equals("back")));
			break;

		case "2":
			register();
			break;
		case "3":
			adminLogin();	
			String input3;
			do {
				System.out.println("enter 1 for addPlan, 2 for delete plan, 3 for check total revenue, 4 for set laundry status for student, 5 Registered Student List, 6 logout");
				input3 = in.nextLine();
				switch(input3) {
				case "1":
					int washes;
					boolean iron;
					double price;
					System.out.println("enter name");
					String name = in.nextLine();
					System.out.println("enter washes");
					try {
						washes = in.nextInt();
					}
					catch(Exception e) {
						System.out.println("Invalid input");
						break;
					}
					System.out.println("enter true for iron false for fold");
					try {
						iron = in.nextBoolean();
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
						System.out.println("Invalid input");
						break;
					}
					System.out.println("enter price");
					try {
						price = in.nextDouble();
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
						System.out.println("Invalid input");
						break;
					}
					
					Admin.addPlan(name, washes, iron, price);
					break;
				case "2":
					System.out.println("enter plan name");
					String planName = in.nextLine();
					Plans plan = CSVPlans.getPlan(planName);
					CSVPlans.deleteCSVPlan(planName);
					Plans.plansList = null;
					Plans.plansList = CSVPlans.getPlansList();
					break;
				case "3":
					System.out.println(Admin.getRevenue());
					break;
				case "4":
					System.out.println("enter studentId");
					String bitsId = in.nextLine();
					System.out.println("enter laundry status");
					String status = in.nextLine();
					Student student = CSVStudent.getStudent(bitsId);
					student.setLastLaundryStatus(status);
					break;
				case "5":
					for(Student s : CSVStudent.getStudentsList()){
						System.out.println(s);
					}
					break;
				case "6":
					input3 = "back";
					break;
				default:
					if(!(input.equals("exit"))){
						System.out.println("Invalid input");
					}
					break;
				}
			}while(!(input3.equals("back")));
			break;
		default:
			if(!(input.equals("exit"))){
				System.out.println("Invalid Input");
			}
		}
			
		}while(!(input.equals("exit")));

		return;

	}
	
}
