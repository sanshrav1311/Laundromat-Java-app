import java.io.IOException;
import java.util.HashMap;

public class Admin extends LoginAndRegistration{
	public static double extraChargesw;
	public static String name;
	private Plans plan;
//	private String credentials;
	private static double totalRevenue;
	private static HashMap<String, String> hostel_day;
	public Admin(){
		
		super("admin@bitspilani.ac.in", "admin@123");
		extraChargesw = 10.0; 
		this.name = "Admin";
		totalRevenue = 0;
		hostel_day = new HashMap<String,String>();
		hostel_day.put("SR","Sunday");
		hostel_day.put("VK","Monday");
		hostel_day.put("Bhag","Monday");
		hostel_day.put("Gandhi","Tuesday");
		hostel_day.put("Krishna","Tuesday");
		hostel_day.put("CVR","Wednesday");
		hostel_day.put("RP","Wednesday");
		hostel_day.put("Shankar","Thursday");
		hostel_day.put("Vyas","Thursday");
		hostel_day.put("Budh","Friday");
		hostel_day.put("Ram","Friday");
		hostel_day.put("Malviya","Saturday");
		hostel_day.put("Meera","Saturday");
	}
	public static void logout() {

	}
	public static HashMap<String,String> getHostelDay()
	{
		return hostel_day;
	}
	public void editHostelDays(String Bhawan,String day)
	{
		hostel_day.remove(Bhawan);
		hostel_day.put(Bhawan,day);
		
	}
	
	
	public static double getRevenue() {
		return totalRevenue;
	}
	public static void setRevenue(double value) {
		totalRevenue = value;
	}
	public String getName() {
		return name;
	}


	public synchronized static void addRevenue(double amount) { 
		totalRevenue += amount;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public static void addPlan(String name, int washes, boolean iron,double price) {
		CSVPlans editor = new CSVPlans();
		Plans plan = new Plans(name, washes, iron,price);
		Plans.getPlans().add(plan);
		try{
			editor.addPlan(name, washes, iron, price);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void setLaundryStatus(Student student, String status) {
		student.setLastLaundryStatus(status);
		
	}
	

}
