import java.io.*;
import java.util.ArrayList;


public class Student extends LoginAndRegistration implements Runnable{
	private Thread theThread;
	private boolean stopThread = false;
	private String name;
	private String emailId;
	private String bitsId;
	private Integer no_of_washes_left = 0; 
	private Plans plan;
//	private String credentials;
	private String hostel;
	private double lastLaundryWeight;
	private String lastLaundryStatus;
	public ArrayList<Laundry> laundryList = new ArrayList();
	
	public void start() {
		if (theThread == null) {
			theThread = new Thread(this);
			theThread.start();
		}	
	}
	public void run() {
		while(true) {
			if (stopThread) { 
				break;
			}
		}
	}
	public void setStopThreadToTrue() {
		stopThread = true;
	}
	
//	public Student() {
//		super(null,null);
//    }

    public Student(String name,String emailId, String bitsId, Integer no_of_washes_left, Plans plan, String hostel, String password) throws IOException{
    	super(bitsId, password);
    	this.name = name;
		this.bitsId = bitsId;
		this.emailId = emailId;
		this.no_of_washes_left = no_of_washes_left;
		this.plan = plan;
		
		this.hostel = hostel;
		start();
	}
    Student(String name,String emailId, String bitsId, Integer no_of_washes_left, Plans plan, String hostel, String password, double lastLaundryWeight, String lastlaundryStatus){
    	   super(bitsId, password);
    	   this.name = name;
    	   this.bitsId = bitsId;
    	   this.emailId = emailId;
    	   this.no_of_washes_left = no_of_washes_left;
    	   this.plan = plan;
    	   this.hostel = hostel;
    	   this.lastLaundryWeight = lastLaundryWeight;
    	   this.lastLaundryStatus = lastLaundryStatus;
    	}
    
//	public Student(String name,String emailId,String bitsId,String hostel) {
//		this.name = name;
//		this.emailId = emailId;
//		this.bitsId = bitsId;
//		this.hostel = hostel;
//	}
     
	public String getHostel() {
		return hostel;
	} 
	public String getName() {
		return name;
	}
	public String getId() {
		return emailId;
	}
	public String getBitsId() {
		return bitsId;
	}
	public Plans getPlan() {
		return plan;
	}
	public String getLastLaundryStatus() {
		return lastLaundryStatus;
	}
	public void setLastLaundryStatus(String status) {
		lastLaundryStatus = status;
		CSVStudent.editStudent(name, emailId, bitsId, no_of_washes_left, plan.name, hostel, super.getPassword(), lastLaundryWeight, lastLaundryStatus);
	}
	public void setHostel(String hostel) {
		this.hostel = hostel;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String emailId) {
		this.emailId = emailId;
	}
	public void setBitsId(String bitsId) {
		this.bitsId = bitsId;
	}
	public void selectPlan(int index) {
		this.plan = Plans.getPlans().get(index);
		no_of_washes_left += plan.no_of_washes;
		Admin.addRevenue(plan.planPrice);
	}

	public boolean clothesGiven(double weight) throws IOException{
		Laundry laundry = new Laundry(weight, this.getBitsId());
		
		if(!(Admin.getHostelDay().get(getHostel()).equals(laundry.day))) {
			System.err.println("Please drop your laundry on "+Admin.getHostelDay().get(getHostel()));
			return false;
		}
		if(weight>2)
		{
			Admin.addRevenue((weight-2)*Admin.extraChargesw);
			CSVRevenue.setRevenue(Admin.getRevenue());
		}	
		if(no_of_washes_left == 0) {
			Admin.addRevenue(200.0);
			CSVRevenue.setRevenue(Admin.getRevenue());
		}
		else {
			no_of_washes_left -= 1;
		}
		System.out.println("Order Recieved Succesfully, clothes will be returned on this "+ laundry.returnDate);
		this.lastLaundryWeight = weight;
    	this.lastLaundryStatus = "given";
		laundryList.add(laundry);
		CSVLaundry.addLaundry(weight, laundry.dateDropped, laundry.returnDate, bitsId);
		CSVStudent.editStudent(name, emailId, bitsId, no_of_washes_left, emailId, hostel, super.getPassword(), weight, lastLaundryStatus);
		
		return true;
}
}
